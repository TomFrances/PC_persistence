package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupShapeTest {

    @Test
    void copyConstructorNotNull(){
        GroupShape groupShapeOriginal = new GroupShape();
        groupShapeOriginal.addShape(new Circle(43, 12));
        groupShapeOriginal.addShape(new Triangle(88, 66));
        GroupShape groupShapeCopy = new GroupShape(groupShapeOriginal);
        assertEquals(2, groupShapeCopy.getShapes().size());
        assertEquals(12, groupShapeCopy.getShapes().get(0).getY());
        assertEquals(88, groupShapeCopy.getShapes().get(1).getX());
    }
    @Test
    void copyConstructorNull(){
        GroupShape groupShapeCopy = new GroupShape(null);
        assertEquals(0, groupShapeCopy.getShapes().size());
        assertEquals(0, groupShapeCopy.getY());
        assertEquals(0, groupShapeCopy.getX());
    }

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
        GroupShape group = new GroupShape();
        group.addShape(new Circle(32, 71));
        assertEquals(1, group.getShapes().size());
        assertTrue(group.getShapes().get(0) instanceof Circle);
    }

    @Test
    void addAll(){
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Triangle(43, 12));
        shapes.add(new Circle(89,87));
        GroupShape group = new GroupShape();
        assertEquals(0, group.getShapes().size());
        group.addAllShapes(shapes);
        assertEquals(2, group.getShapes().size());
        assertEquals(87, group.getShapes().get(1).getY());
    }
    @Test
    void remove(){
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Triangle(43, 12));
        shapes.add(new Circle(89,87));
        GroupShape group = new GroupShape();
        assertEquals(0, group.getShapes().size());
        group.addAllShapes(shapes);
        assertEquals(2, group.getShapes().size());
        assertEquals(87, group.getShapes().get(1).getY());
        group.remove(1);
        assertEquals(43, group.getShape(0).getX());
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