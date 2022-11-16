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
import java.util.List;
import java.util.logging.Level;


import edu.uga.miage.m1.polygons.gui.command.Command;
import edu.uga.miage.m1.polygons.gui.command.SaveCommand;
import edu.uga.miage.m1.polygons.gui.command.UndoCommand;
import edu.uga.miage.m1.polygons.gui.factory.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.file_management.Export;
import edu.uga.miage.m1.polygons.gui.file_management.Import;
import edu.uga.miage.m1.polygons.gui.shapes.*;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;
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
    private transient Shape dragged;
    private transient GroupShape groupShape;

    private enum Shapes {SQUARE, TRIANGLE, CIRCLE}

    @Serial
    private static final long serialVersionUID = 1L;
    private final JToolBar toolbar;
    private Shapes selected;
    public static final JPanel panel = new JPanel();
    private final JLabel label;

    public void setShapesList(List<Shape> shapesList) {
        this.shapesList = shapesList;
    }

    private List<Shape> shapesList = new ArrayList<>();//NOSONAR
    private final ActionListener reusableActionListener = new ShapeActionListener();//NOSONAR
    private final Drawer drawer = new Drawer();//NOSONAR

    private final ShapeFactory shapeFactory = new ShapeFactory();//NOSONAR

    public Deque<List<Shape>> getUndoStack() {
        return undoStack;
    }

    public Deque<List<Shape>> getRedoStackStack() {
        return redoStackStack;
    }

    private Deque<List<Shape>> undoStack = new ArrayDeque<>();
    private Deque<List<Shape>> redoStackStack = new ArrayDeque<>();

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

        setPreferredSize(new Dimension(1100, 700));
    }

    public List<Shape> getShapesList() {
        return this.shapesList;
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

            if (disassemble) {
                executeCommand(new SaveCommand(this));
                disbandGroup.setSelected(false);
                disassemble = false;
            }
            if (groupMode) {
                executeCommand(new SaveCommand(this));
                groupShape = new GroupShape();
            } else {
                if (groupShape.getShapes().size() > 1) {
                    shapesList.add(groupShape);
                    drawALlShapes();
                } else if (groupShape.getShapes().size() == 1) {
                    shapesList.add(groupShape.getShapes().get(0));
                }
            }
        });
        menu.add(groupCheck);

        disbandGroup.addItemListener(e -> {
            disassemble = e.getStateChange() == ItemEvent.SELECTED && !groupMode;
            if (!disassemble) disbandGroup.setSelected(false);
        });
        menu.add(disbandGroup);
        JMenu exportMenu = new JMenu("Export");

        fileMenu.add(exportMenu);
        JMenuItem itemJsonExport = new JMenuItem();
        itemJsonExport.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Export.jsonExport(shapesList);
            }
        });
        itemJsonExport.setText("JSON");
        exportMenu.add(itemJsonExport);

        JMenuItem itemXmlExport = new JMenuItem();
        itemXmlExport.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Export.xmlExport(shapesList);
            }
        });
        itemXmlExport.setText("XML");
        exportMenu.add(itemXmlExport);

        JMenuItem importItem = new JMenuItem("Import");
        importItem.setText("Import");
        importItem.addActionListener(e -> {
            shapesList = Import.importShapesFile();
            drawALlShapes();
        });

        fileMenu.add(importItem);

        JButton buttonUndo = new JButton("Undo");
        buttonUndo.addActionListener(e -> {
            executeCommand(new UndoCommand(this));
        });
        menu.add(buttonUndo);
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
            int i = findShapeIndex(evt);
            if (i >= 0) {
                Shape shape = shapesList.remove(i);
                if (shape instanceof GroupShape sl) {
                    shapesList.addAll(sl.getShapes());
                    drawALlShapes();
                }
            }
        }
    }

    private int findShapeIndex(MouseEvent evt) {
        int i = shapesList.size() - 1;
        while (i >= 0 && !shapesList.get(i).isInside(evt.getX(), evt.getY())) {
            i--;
        }
        return i;
    }

    private void groupShape(MouseEvent evt) {
        int i = findShapeIndex(evt);
        if (i >= 0) {
            Shape shape = shapesList.remove(i);
            groupShape.addShape(shape);
        }
    }

    private void createShape(MouseEvent evt) {
        Graphics2D g2 = (Graphics2D) panel.getGraphics();

        executeCommand(new SaveCommand(this));

        switch (selected) {
            case CIRCLE -> {
                Circle circle = (Circle) shapeFactory.createShape("circle", evt.getX(), evt.getY());
                shapesList.add(circle);
                drawer.drawCircle(circle, g2, Color.BLACK);
            }
            case TRIANGLE -> {
                Triangle triangle = (Triangle) shapeFactory.createShape("triangle", evt.getX(), evt.getY());
                shapesList.add(triangle);
                drawer.drawTriangle(triangle, g2, Color.BLACK);
            }
            case SQUARE -> {
                Square square = (Square) shapeFactory.createShape("square", evt.getX(), evt.getY());
                shapesList.add(square);
                drawer.drawSquare(square, g2, Color.BLACK);
            }
        }
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
            int i = shapesList.size() - 1;
            while (i >= 0 && !shapesList.get(i).isInside(evt.getX(), evt.getY())) {
                i--;
            }
            if (i >= 0) {
                executeCommand(new SaveCommand(this));

                Shape shape = shapesList.remove(i);
                dragged = shape;
                if (dragged instanceof GroupShape s) {
                    s.setCoordinate(evt.getX(), evt.getY());
                }
                shapesList.add(shape);
            }
        }
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     *
     * @param evt The associated mouse event.
     **/
    public void mouseReleased(MouseEvent evt) {
        dragged = null;
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     *
     * @param evt The associated mouse event.
     **/
    public void mouseDragged(MouseEvent evt) {
        if (dragged != null) {
            dragged.moveTo(evt.getX(), evt.getY());
            drawALlShapes();
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

    public void drawALlShapes() {
        Graphics2D g2 = (Graphics2D) panel.getGraphics();
        panel.repaint();
        SwingUtilities.invokeLater(() -> shapesList.forEach(shape -> drawer.drawShape(shape, g2, Color.BLACK)));
    }

    private void executeCommand(Command command) {
        command.execute();
    }
}