package edu.uga.miage.m1.polygons.gui.utils;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Drawer {

    public void drawShape(Shape shape, Color color){
        String type = ShapeUtils.getType(shape);
        switch (type){
            case "triangle" -> drawTriangle((Triangle) shape, color);
            case "square" -> drawSquare((Square) shape, color);
            case "circle" -> drawCircle((Circle) shape, color);
            case "groupshape" -> drawGroup((GroupShape) shape);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
    
    private void drawCircle(Circle circle, Color color) {
        Graphics2D graphics2D = (Graphics2D) JDrawingFrame.panel.getGraphics();
        if(Objects.isNull(graphics2D)){
            return;
        }
        int x = circle.getX();
        int y = circle.getY();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x - 25f, y - 25f, Color.RED, x + 25f, y - 25f, Color.WHITE);
        graphics2D.setPaint(gradient);
        graphics2D.fill(circle.getShapeDraw());
        BasicStroke wideStroke = new BasicStroke(3.0f);
        graphics2D.setColor(color);
        graphics2D.setStroke(wideStroke);
        graphics2D.draw(circle.getShapeDraw());
    }

    private void drawTriangle(Triangle triangle, Color color){
        Graphics2D graphics2D = (Graphics2D) JDrawingFrame.panel.getGraphics();
        if(Objects.isNull(graphics2D)){
            return;
        }
        int x = triangle.getX();
        int y = triangle.getY();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.GREEN, x + 50f, y, Color.WHITE);
        graphics2D.setPaint(gradient);
        graphics2D.fill(triangle.getShapeDraw());
        BasicStroke wideStroke = new BasicStroke(3.0f);
        graphics2D.setColor(color);
        graphics2D.setStroke(wideStroke);
        graphics2D.draw(triangle.getShapeDraw());
    }

    private void drawSquare(Square square, Color color) {
        Graphics2D graphics2D = (Graphics2D) JDrawingFrame.panel.getGraphics();
        if(Objects.isNull(graphics2D)){
            return;
        }
        int x = square.getX();
        int y = square.getY();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.BLUE, x + 50f, y, Color.WHITE);
        graphics2D.setPaint(gradient);
        graphics2D.fill(square.getShapeDraw());
        BasicStroke wideStroke = new BasicStroke(3.0f);
        graphics2D.setColor(color);
        graphics2D.setStroke(wideStroke);
        graphics2D.draw(square.getShapeDraw());
    }

    public void drawGroup(GroupShape group) {
        for (Shape shape : group.getShapes()) {
            drawShape(shape, group.getColor());
        }
    }

    public void drawAllShapes(GroupShape shapes) {
        JDrawingFrame.panel.repaint();
        if(!shapes.getShapes().isEmpty()) {
            SwingUtilities
                    .invokeLater(() -> shapes.getShapes()
                            .forEach(shape -> drawShape(shape, Color.BLACK)));
        }
    }

}
