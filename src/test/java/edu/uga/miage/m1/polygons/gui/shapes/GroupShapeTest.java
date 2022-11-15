package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupShapeTest {

    @Test
    void setCoordinate() {
        GroupShape group = new GroupShape();
        assertEquals(0, group.getX());
        assertEquals(0, group.getY());
        group.setCoordinate(30, 35);
        assertEquals(30, group.getX());
        assertEquals(35, group.getY());
    }

    @Test
    void getShapes() {
        GroupShape group = new GroupShape();
        group.addShape(new Circle(32, 71));
        group.addShape(new Triangle(2, 45));
        List<Shape> shapes = group.getShapes();
        assertEquals(2, shapes.size());
        assertTrue(shapes.get(0) instanceof Circle);
        assertTrue(shapes.get(1) instanceof Triangle);
    }

    @Test
    void addShape() {
        //TODO
        GroupShape group = new GroupShape();
        group.addShape(new Circle(32, 71));
        assertEquals(1, group.getShapes().size());
        assertTrue(group.getShapes().get(0) instanceof Circle);
    }

    @Test
    void isInside() {
        GroupShape group = new GroupShape();
        group.addShape(new Circle(32, 71));
        group.addShape(new Triangle(2, 45));
        assertTrue(group.isInside(32, 79));
        assertTrue(group.isInside(15, 39));
        assertFalse(group.isInside(100, 100));
    }

    @Test
    void moveTo() {
        GroupShape group = new GroupShape();
        group.addShape(new Circle(32, 71));
        group.addShape(new Triangle(2, 45));
        group.moveTo(10, 10);
        assertEquals(42, group.getShapes().get(0).getX());
        assertEquals(81, group.getShapes().get(0).getY());
        assertEquals(12, group.getShapes().get(1).getX());
        assertEquals(55, group.getShapes().get(1).getY());
    }
}