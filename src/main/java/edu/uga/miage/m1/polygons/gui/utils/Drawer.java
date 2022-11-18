package edu.uga.miage.m1.polygons.gui.utils;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.ShapeEditor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Drawer {

    public static void drawShape(Shape shape, Color color){
        String type = ShapeUtils.getType(shape);
        switch (type){
            case "triangle" -> drawTriangle((Triangle) shape, color);
            case "square" -> drawSquare((Square) shape, color);
            case "circle" -> drawCircle((Circle) shape, color);
            case "groupshape" -> drawGroup((GroupShape) shape);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
    
    private static void drawCircle(Circle circle, Color color) {
        Graphics2D graphics2D = (Graphics2D) JDrawingFrame.panel.getGraphics();
        int x = circle.getX();
        int y = circle.getY();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x - 25f, y - 25f, Color.RED, x + 25f, y - 25f, Color.WHITE);
        graphics2D.setPaint(gradient);
        graphics2D.fill(new Ellipse2D.Double(x - 25f, y - 25f, 50, 50));
        BasicStroke wideStroke = new BasicStroke(3.0f);
        graphics2D.setColor(color);
        graphics2D.setStroke(wideStroke);
        graphics2D.draw(new Ellipse2D.Double(x - 25f, y - 25f, 50, 50));
    }

    private static void drawTriangle(Triangle triangle, Color color){
        Graphics2D graphics2D = (Graphics2D) JDrawingFrame.panel.getGraphics();
        int x = triangle.getX();
        int y = triangle.getY();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.GREEN, x + 50f, y, Color.WHITE);
        graphics2D.setPaint(gradient);
        int[] xCoords = { x, x-25, x + 25 };
        int[] yCoords = { y-25, y + 25, y + 25 };
        GeneralPath polygon = new GeneralPath(Path2D.WIND_EVEN_ODD, xCoords.length);
        polygon.moveTo(x, y - 25f);
        for (int i = 0; i < xCoords.length; i++) {
            polygon.lineTo(xCoords[i], yCoords[i]);
        }
        polygon.closePath();
        graphics2D.fill(polygon);
        BasicStroke wideStroke = new BasicStroke(3.0f);
        graphics2D.setColor(color);
        graphics2D.setStroke(wideStroke);
        graphics2D.draw(polygon);
    }

    private static void drawSquare(Square square, Color color) {
        Graphics2D graphics2D = (Graphics2D) JDrawingFrame.panel.getGraphics();
        int x = square.getX();
        int y = square.getY();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.BLUE, x + 50f, y, Color.WHITE);
        graphics2D.setPaint(gradient);
        graphics2D.fill(new Rectangle2D.Double(x - 25d, y - 25d, 50, 50));
        BasicStroke wideStroke = new BasicStroke(3.0f);
        graphics2D.setColor(color);
        graphics2D.setStroke(wideStroke);
        graphics2D.draw(new Rectangle2D.Double(x - 25d, y - 25d, 50, 50));
    }

    public static void drawGroup(GroupShape group) {
        for (Shape shape : group.getShapes()) {
            drawShape(shape, group.getColor());
        }
    }

    public static void drawAllShapes(GroupShape shapes) {
        JDrawingFrame.panel.repaint();
        SwingUtilities
                .invokeLater(() -> shapes.getShapes()
                        .forEach(shape -> drawShape(shape, Color.BLACK)));
    }

}
