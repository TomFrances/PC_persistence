package edu.uga.miage.m1.polygons.gui.utils;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Drawer {

    public void drawShape(Graphics2D g2,Shape shape, Color color){
        String type = ShapeUtils.getType(shape);
        switch (type){
            case "triangle" -> drawShape(g2,"src/main/resources/images/drawableTriangle.png", shape.getX(),shape.getY(),color);
            case "square" -> drawShape(g2,"src/main/resources/images/drawableSquare.png", shape.getX(),shape.getY(),color);
            case "circle" -> drawShape(g2,"src/main/resources/images/drawableCircle.png", shape.getX(),shape.getY(),color);
            case "groupshape" -> drawGroup(g2,(GroupShape) shape);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
    private void drawShape(Graphics2D g2, String source,int x,int y,Color color) {
        try {
            File fileImage = new File(source);
            BufferedImage image = ImageIO.read(fileImage);
            g2.drawImage(image, x-25, y-25, null);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(2));
            g2.drawRect(x-25, y-25, 50, 50);
            g2.setStroke(g2.getStroke());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawGroup(Graphics2D g2,GroupShape group) {
        for (Shape shape : group.getShapes()) {
            drawShape(g2,shape, group.getColor());
        }
    }

    public void drawAllShapes(GroupShape shapes,Graphics2D g2) {
        JDrawingFrame.panel.repaint();
        if(!shapes.getShapes().isEmpty()) {
            SwingUtilities
                    .invokeLater(() -> shapes.getShapes()
                            .forEach(shape -> drawShape(g2,shape, Color.BLACK)));
        }
    }

}
