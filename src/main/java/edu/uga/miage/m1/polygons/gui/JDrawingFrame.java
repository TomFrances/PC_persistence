package edu.uga.miage.m1.polygons.gui;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serial;
import java.util.*;
import java.util.logging.Logger;

import javax.swing.*;

import edu.uga.miage.m1.polygons.gui.file_management.Export;
import edu.uga.miage.m1.polygons.gui.file_management.Import;
import edu.uga.miage.m1.polygons.gui.shapes.*;
import lombok.extern.java.Log;

import static java.lang.Thread.sleep;

/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
@Log
public class JDrawingFrame extends JFrame
        implements MouseListener, MouseMotionListener {

    private enum Shapes {SQUARE, TRIANGLE, CIRCLE, STAR}

    @Serial
    private static final long serialVersionUID = 1L;
    private final JToolBar toolbar;
    private Shapes selected;
    public static JPanel panel = new JPanel();
    private final JLabel label;
    private transient List<SimpleShape> shapesList = new ArrayList<>();
    private final transient ActionListener reusableActionListener = new ShapeActionListener();

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
        panel.setLayout(null);
        panel.setMinimumSize(new Dimension(400, 400));
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        label = new JLabel(" ", SwingConstants.LEFT);

        // Add shapes in the menu
        addShape(Shapes.SQUARE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/square.png"))));
        addShape(Shapes.TRIANGLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/triangle.png"))));
        addShape(Shapes.CIRCLE, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/circle.png"))));
        addShape(Shapes.STAR, new ImageIcon(Objects.requireNonNull(getClass().getResource("images/star.png"))));


        // Fills the panel
        setLayout(new BorderLayout());
        add(menuBar, BorderLayout.NORTH);
        add(toolbar, BorderLayout.WEST);
        add(panel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(400, 400));
    }


    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     *
     * @param shape The name of the injected <tt>SimpleShape</tt>.
     * @param icon  The icon associated with the injected <tt>SimpleShape</tt>.
     **/
    private void addShape(Shapes shape, ImageIcon icon) {
        JButton button = new JButton(icon);
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

        return menu;
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     *
     * @param evt The associated mouse event.
     **/
    public void mouseClicked(MouseEvent evt) {

        if (panel.contains(evt.getX(), evt.getY())) {
            Graphics2D g2 = (Graphics2D) panel.getGraphics();
            switch (selected) {
                case CIRCLE -> {
                    Circle circle = new Circle(evt.getX(), evt.getY());
                    shapesList.add(circle);
                    circle.draw(g2);
                }
                case TRIANGLE -> {
                    Triangle triangle = new Triangle(evt.getX(), evt.getY());
                    shapesList.add(triangle);
                    triangle.draw(g2);
                }
                case SQUARE -> {
                    Square square = new Square(evt.getX(), evt.getY());
                    shapesList.add(square);
                    square.draw(g2);
                }
                case STAR -> {
                    Star star = new Star(evt.getX(), evt.getY());
                    shapesList.add(star);
                    star.draw(g2);
                }
                default -> Logger.getLogger("No shape named " + selected);
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
        /* Unused */
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     *
     * @param evt The associated mouse event.
     **/
    public void mouseReleased(MouseEvent evt) {
        /* Unused */
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     *
     * @param evt The associated mouse event.
     **/
    public void mouseDragged(MouseEvent evt) {
        /* Unused */
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

    private void drawALlShapes() {
        panel.repaint();
        SwingUtilities.invokeLater(() -> {
            Graphics2D g2 = (Graphics2D) panel.getGraphics();
            shapesList.forEach(shape -> shape.draw(g2));
        });
    }
}