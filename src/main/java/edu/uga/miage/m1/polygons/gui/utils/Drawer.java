package edu.uga.miage.m1.polygons.gui.utils;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Drawer {

    public void drawShape(Shape shape, Graphics2D g2, Color color){
        String type = ShapeUtils.getType(shape);
        switch (type){
            case "triangle" -> this.drawTriangle((Triangle) shape, g2, color);
            case "square" -> this.drawSquare((Square) shape, g2, color);
            case "circle" -> this.drawCircle((Circle) shape, g2, color);
            case "groupshape" -> this.drawGroup((GroupShape) shape, g2);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
    
    public void drawCircle(Circle circle, Graphics2D g2, Color color) {
        int x = circle.getX();
        int y = circle.getY();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x - 25f, y - 25f, Color.RED, x + 25f, y - 25f, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(new Ellipse2D.Double(x - 25f, y - 25f, 50, 50));
        BasicStroke wideStroke = new BasicStroke(3.0f);
        g2.setColor(color);
        g2.setStroke(wideStroke);
        g2.draw(new Ellipse2D.Double(x - 25f, y - 25f, 50, 50));
    }

    public void drawTriangle(Triangle triangle, Graphics2D g2, Color color){
        int x = triangle.getX();
        int y = triangle.getY();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.GREEN, x + 50f, y, Color.WHITE);
        g2.setPaint(gradient);
        int[] xcoords = { x, x-25, x + 25 };
        int[] ycoords = { y-25, y + 25, y + 25 };
        GeneralPath polygon = new GeneralPath(Path2D.WIND_EVEN_ODD, xcoords.length);
        polygon.moveTo(x, y - 25f);
        for (int i = 0; i < xcoords.length; i++) {
            polygon.lineTo(xcoords[i], ycoords[i]);
        }
        polygon.closePath();
        g2.fill(polygon);
        BasicStroke wideStroke = new BasicStroke(3.0f);
        g2.setColor(color);
        g2.setStroke(wideStroke);
        g2.draw(polygon);
    }

    public void drawSquare(Square square, Graphics2D g2, Color color) {
        int x = square.getX();
        int y = square.getY();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.BLUE, x + 50f, y, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(new Rectangle2D.Double(x - 25d, y - 25d, 50, 50));
        BasicStroke wideStroke = new BasicStroke(3.0f);
        g2.setColor(color);
        g2.setStroke(wideStroke);
        g2.draw(new Rectangle2D.Double(x - 25d, y - 25d, 50, 50));
    }

    public void drawGroup(GroupShape group, Graphics2D g2) {
        for (Shape shape : group.getShapes()) {
            this.drawShape(shape, g2, group.getColor());
        }
    }

}
