package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GroupShape extends Shape {

    private final List<Shape> shapes = new ArrayList<>();
    private final Color color;

    public GroupShape(){
        super(0, 0);
        this.color = new Color((new Random()).nextInt() * 0x1000000);//NOSONAR
    }

    public GroupShape(int x, int y) {
        super(x, y);
        this.color = new Color((new Random()).nextInt() * 0x1000000);//NOSONAR
    }

    public void setCoordinate(int x, int y) {
        setX(x);
        setY(y);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void addShape(Shape shape) {
        if (shape instanceof GroupShape s) {
            shapes.addAll(s.getShapes());
        } else {
            shapes.add(shape);
        }
    }

    public boolean isInside(int x, int y) {
        return shapes.stream().anyMatch(shape -> shape.isInside(x, y));
    }

    @Override
    public void moveTo(int x, int y) {
        int distX = x - getX();
        int distY = y - getY();
        for (Shape shape : shapes) {
            shape.moveTo(shape.getX() + distX, shape.getY() + distY);
        }

        setCoordinate(x, y);
    }

    public Color getColor() {
        return color;
    }
}
