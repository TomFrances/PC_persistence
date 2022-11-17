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
import edu.uga.miage.m1.polygons.gui.utils.Drawer;
import lombok.extern.java.Log;

import javax.swing.*;


/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
@Log
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
    private final Drawer drawer;//NOSONAR
    private final ShapeEditor shapeEditor = new ShapeEditor();//NOSONAR

    /**
     * Tracks buttons to manage the background.
     */
    private final Map<Shapes, JButton> buttons = new EnumMap<>(Shapes.class);

    /**
     * Default constructor that populates the main window.
     *
     * @param frameName the frame
     **/
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

        drawer = new Drawer(panel, shapeEditor);

        setPreferredSize(new Dimension(1100, 700));
    }


    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     *
     * @param shape The name of the injected <tt>SimpleShape</tt>.
     * @param icon  The icon associated with the injected <tt>SimpleShape</tt>.
     **/
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
        groupCheck.addItemListener(e -> {
            groupMode = e.getStateChange() == ItemEvent.SELECTED;
            disbandGroup.setSelected(false);
            disassemble = false;
            if (groupMode) {
                shapeEditor.startGrouping();
            } else {
                shapeEditor.endGrouping();
            }
        });
        menu.add(groupCheck);

        disbandGroup.addItemListener(e -> {
            disassemble = e.getStateChange() == ItemEvent.SELECTED && !groupMode;
            if (!disassemble) {
                disbandGroup.setSelected(false);
            }
        });
        menu.add(disbandGroup);
        JMenu exportMenu = new JMenu("Export");

        fileMenu.add(exportMenu);

        JMenuItem itemXmlExport = new JMenuItem();
        itemXmlExport.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeEditor.exportXML();
            }
        });
        itemXmlExport.setText("XML");
        exportMenu.add(itemXmlExport);

        JMenu importMenu = new JMenu("Import");

        fileMenu.add(importMenu);

        JMenuItem itemXmlImport = new JMenuItem();
        itemXmlImport.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeEditor.importXML();
                drawAllShapes();
            }
        });
        itemXmlImport.setText("XML");
        importMenu.add(itemXmlImport);

        JMenuItem itemJsonExport = new JMenuItem();
        itemJsonExport.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeEditor.exportJSON();
            }
        });
        itemJsonExport.setText("JSON");
        exportMenu.add(itemJsonExport);

        JMenuItem itemJsonImport = new JMenuItem();
        itemJsonImport.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeEditor.importJSON();
                drawAllShapes();
            }
        });
        itemJsonImport.setText("JSON");
        importMenu.add(itemJsonImport);

        JButton buttonUndo = new JButton("Undo");
        buttonUndo.addActionListener(e -> executeCommand(new UndoCommand(shapeEditor)));
        menu.add(buttonUndo);

        JButton buttonRedo = new JButton("Redo");
        buttonRedo.addActionListener(e -> {

        });
        menu.add(buttonRedo);

        return menu;
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     *
     * @param evt The associated mouse event.
     **/
    public void mouseClicked(MouseEvent evt) {
        if (!groupMode && !disassemble && panel.contains(evt.getX(), evt.getY())) {
            createShape(evt);
        } else if (groupMode && panel.contains(evt.getX(), evt.getY())) {
            groupShape(evt);
        } else if (disassemble && panel.contains(evt.getX(), evt.getY())) {
            disassembleGroupShape(evt);
        }
    }

    private void disassembleGroupShape(MouseEvent evt) {
        shapeEditor.disassembleGroupShape(evt);
        drawAllShapes();
    }

    private void groupShape(MouseEvent evt) {
        shapeEditor.groupShape(evt);
    }

    private void createShape(MouseEvent evt) {
        drawer.drawShape(shapeEditor.createShape(evt, selected), Color.BLACK);
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     *
     * @param evt The associated mouse event.
     **/
    public void mouseEntered(MouseEvent evt) {
        /* Unused */
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     *
     * @param evt The associated mouse event.
     **/
    public void mouseExited(MouseEvent evt) {
        label.setText(" ");
        label.repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     *
     * @param evt The associated mouse event.
     **/
    public void mousePressed(MouseEvent evt) {
        if (!groupMode) {
            shapeEditor.startDraggingShape(evt);
        }
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     *
     * @param evt The associated mouse event.
     **/
    public void mouseReleased(MouseEvent evt) {
        shapeEditor.setDragged(null);
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     *
     * @param evt The associated mouse event.
     **/
    public void mouseDragged(MouseEvent evt) {
        shapeEditor.dragShape(evt);
        if (Objects.nonNull(shapeEditor.getDragged())) {
            drawer.drawALlShapes();
        }
    }

    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     *
     * @param evt The associated mouse event.
     **/
    public void mouseMoved(MouseEvent evt) {
        modifyLabel(evt);
    }

    private void modifyLabel(MouseEvent evt) {
        label.setText("(" + evt.getX() + "," + evt.getY() + ")");
    }

    /**
     * Simple action listener for shape tool bar buttons that sets
     * the drawing frame's currently selected shape when receiving
     * an action event.
     **/
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

    public void drawAllShapes() {
        panel.repaint();
        SwingUtilities.invokeLater(() -> shapeEditor.getShapesList().forEach(shape -> drawer.drawShape(shape, Color.BLACK)));
    }

    private void executeCommand(Command command) {
        command.execute();
        drawAllShapes();
    }
}