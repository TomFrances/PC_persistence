package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GroupShape implements Shape, Drawable{
    int x;
    int y;
    private List<Shape> shapes = new ArrayList<>();
    private Color color;
    public GroupShape(){
        this.color = new Color((int)(Math.random() * 0x1000000));
    }

    public void setCoordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public Color getColor(){
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }

    public List<Shape> getShapes(){
        return shapes;
    }
    public void addShape(Shape shape){
        if(shape instanceof GroupShape){
            shapes.addAll(((GroupShape)shape).getShapes());
        }else{
            shapes.add(shape);
        }
    }
    @Override
    public boolean isInside(int x, int y) {
        return shapes.stream().anyMatch(shape -> shape.isInside(x,y));
    }

    @Override
    public void moveTo(int x, int y) {
        int distX = x-this.x;
        int distY = y-this.y;
        for (Shape shape : shapes) {
            shape.moveTo(shape.getX()+distX, shape.getY()+distY);
        }

        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g2) {
        for (Shape shape : shapes) {
            shape.draw(g2,this.color);
        }
    }
    public void draw(Graphics2D g2, Color color){
        for (Shape shape : shapes) {
            shape.draw(g2,color);
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
