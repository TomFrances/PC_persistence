package edu.uga.miage.m1.polygons.gui;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you mp1.y not use this file except in compliance
 * with the License.  You mp1.y obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required p2.y applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.Serial;
import java.util.*;

import edu.uga.miage.m1.polygons.gui.command.*;
import edu.uga.miage.m1.polygons.gui.file_management.Export;
import edu.uga.miage.m1.polygons.gui.file_management.Import;
import edu.uga.miage.m1.polygons.gui.shapes.Shapes;
import edu.uga.miage.m1.polygons.gui.utils.FileUtils;

import javax.swing.*;

/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JDrawingFrame extends JFrame
        implements MouseListener, MouseMotionListener {
    private boolean groupMode = false;
    private boolean disassemble = false;
    @Serial
    private static final long serialVersionUID = 1L;
    private final JToolBar toolbar;
    private Shapes selected;
    public static final JPanel panel = new JPanel();
    private final JLabel label;
    private final ActionListener reusableActionListener = new ShapeActionListener();//NOSONAR
    private final ShapeEditor shapeEditor = new ShapeEditor();//NOSONAR
    private final Map<Shapes, JButton> buttons = new EnumMap<>(Shapes.class);

    public JDrawingFrame(String frameName) {
        super(frameName);

        // Instantiates components
        toolbar = new JToolBar("Toolbar", SwingConstants.VERTICAL);
        JMenuBar menuBar = createToolsMenu();
        panel.setBackground(Color.WHITE);
        panel.setName("drawingPanel");
        panel.setLayout(null);
        panel.setMinimumSize(new Dimension(600, 400));
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        label = new JLabel(" ", SwingConstants.LEFT);

        // Add shapes in the menu
        addShape(Shapes.SQUARE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/square.png"))));
        addShape(Shapes.TRIANGLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/triangle.png"))));
        addShape(Shapes.CIRCLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/circle.png"))));

        // Fills the panel
        setLayout(new BorderLayout());
        add(menuBar, BorderLayout.NORTH);
        add(toolbar, BorderLayout.WEST);
        add(panel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(1100, 700));
    }

    private void addShape(Shapes shape, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setName(shape.name());
        button.setBorderPainted(false);
        buttons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(reusableActionListener);

        if (selected == null) {
            button.doClick();
        }

        toolbar.add(button);
        toolbar.validate();
        repaint();
    }

    private JMenuBar createToolsMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menu.add(fileMenu);
        JCheckBox groupCheck = new JCheckBox("Group mode");
        JCheckBox disbandGroup = new JCheckBox("Disassemble group");
        groupCheck.addItemListener(e -> groupActionListener(disbandGroup, e));
        menu.add(groupCheck);

        disbandGroup.addItemListener(e -> disbandActionListener(disbandGroup, e));
        menu.add(disbandGroup);

        fileMenu.add(createExportMenu());
        fileMenu.add(createImportMenu());

        JButton buttonUndo = new JButton("Undo");
        buttonUndo.addActionListener(e -> (new UndoCommand(shapeEditor)).execute());
        menu.add(buttonUndo);

        JButton buttonRedo = new JButton("Redo");
        buttonRedo.addActionListener(e -> (new RedoCommand(shapeEditor)).execute());
        menu.add(buttonRedo);

        JButton buttonReset = new JButton("Reset");
        buttonReset.addActionListener(e -> (new ResetCommand(shapeEditor)).execute());
        menu.add(buttonReset);
        return menu;
    }

    private void disbandActionListener(JCheckBox disbandGroup, ItemEvent e) {
        disassemble = e.getStateChange() == ItemEvent.SELECTED && !groupMode;
        if (!disassemble) {
            disbandGroup.setSelected(false);
        }
    }

    private void groupActionListener(JCheckBox disbandGroup, ItemEvent e) {
        shapeEditor.setGraphics2D((Graphics2D) panel.getGraphics());
        groupMode = e.getStateChange() == ItemEvent.SELECTED;
        disbandGroup.setSelected(false);
        disassemble = false;
        if (groupMode) {
            shapeEditor.startGrouping();
        } else {
            shapeEditor.endGrouping();
        }
    }

    private JMenu createImportMenu() {

        JMenu importMenu = new JMenu("Import");
        JMenuItem itemXmlImport = new JMenuItem();
        itemXmlImport.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                shapeEditor.setGraphics2D((Graphics2D) panel.getGraphics());
                shapeEditor.setShapesList(Import.importXML(FileUtils.chooseFile()));
            }
        });
        itemXmlImport.setText("XML");
        importMenu.add(itemXmlImport);
        JMenuItem itemJsonImport = new JMenuItem();
        itemJsonImport.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                shapeEditor.setGraphics2D((Graphics2D) panel.getGraphics());
                shapeEditor.setShapesList(Import.importJSON(FileUtils.chooseFile()));
            }
        });
        itemJsonImport.setText("JSON");
        importMenu.add(itemJsonImport);
        return importMenu;
    }

    private JMenu createExportMenu() {
        JMenu exportMenu = new JMenu("Export");
        JMenuItem itemXmlExport = new JMenuItem();
        itemXmlExport.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Export.exportXML(shapeEditor.getShapesList(), FileUtils.chooseFile());
            }
        });
        itemXmlExport.setText("XML");
        exportMenu.add(itemXmlExport);

        JMenuItem itemJsonExport = new JMenuItem();
        itemJsonExport.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Export.exportJSON(shapeEditor.getShapesList(), FileUtils.chooseFile());
            }
        });
        itemJsonExport.setText("JSON");
        exportMenu.add(itemJsonExport);
        return exportMenu;
    }

    public void mouseClicked(MouseEvent evt) {
        shapeEditor.setGraphics2D((Graphics2D) panel.getGraphics());
        if (!groupMode && !disassemble && panel.contains(evt.getX(), evt.getY())) {
            shapeEditor.createShape(evt, selected);
        } else if (groupMode && panel.contains(evt.getX(), evt.getY())) {
            shapeEditor.groupShape(evt);
        } else if (disassemble && panel.contains(evt.getX(), evt.getY())) {
            shapeEditor.disassembleGroupShape(evt);
        }
    }

    public void mouseEntered(MouseEvent evt) {
        /* Unused */
    }

    public void mouseExited(MouseEvent evt) {
        label.setText(" ");
        label.repaint();
    }

    public void mousePressed(MouseEvent evt) {
        if (!groupMode) {
            shapeEditor.startDraggingShape(evt);
        }
    }

    public void mouseReleased(MouseEvent evt) {
        shapeEditor.setDragged(null);
    }

    public void mouseDragged(MouseEvent evt) {
        shapeEditor.setGraphics2D((Graphics2D) panel.getGraphics());
        shapeEditor.dragShape(evt);
    }

    public void mouseMoved(MouseEvent evt) {
        modifyLabel(evt);
    }

    private void modifyLabel(MouseEvent evt) {
        label.setText("(" + evt.getX() + "," + evt.getY() + ")");
    }

    private class ShapeActionListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            for (Map.Entry<Shapes, JButton> entry : buttons.entrySet()) {
                JButton btn = entry.getValue();
                Shapes shape = entry.getKey();
                if (evt.getActionCommand().equals(shape.toString())) {
                    btn.setBorderPainted(true);
                    selected = shape;
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }
        }
    }
}