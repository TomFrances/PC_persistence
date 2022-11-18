package edu.uga.miage.m1.polygons.gui.copy_factory;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCopyFactoryTest {

    ShapeCopyFactory factory = new ShapeCopyFactory();

    @Test
    void ShapeCopyFactoryCircle() {
        Shape start = new Circle(50, 30);
        Shape end = factory.copyShape(start);
        assertNotEquals(start, end);
        assertEquals(start.getX(), end.getX());
        assertEquals(start.getY(), end.getY());
        assertEquals(start.getClass(), Circle.class);
    }
    @Test
    void ShapeCopyFactoryTriangle() {
        Shape start = new Triangle(50, 30);
        Shape end = factory.copyShape(start);
        assertNotEquals(start, end);
        assertEquals(start.getX(), end.getX());
        assertEquals(start.getY(), end.getY());
        assertEquals(start.getClass(), Triangle.class);
    }
    @Test
    void ShapeCopyFactorySquare() {
        Shape start = new Square(50, 30);
        Shape end = factory.copyShape(start);
        assertNotEquals(start, end);
        assertEquals(start.getX(), end.getX());
        assertEquals(start.getY(), end.getY());
        assertEquals(start.getClass(), Square.class);
    }@Test
    void ShapeCopyFactoryGroupShape() {
        Shape start = new GroupShape(50, 30);
        Shape end = factory.copyShape(start);
        assertNotEquals(start, end);
        assertEquals(start.getX(), end.getX());
        assertEquals(start.getY(), end.getY());
        assertEquals(start.getClass(), GroupShape.class);
    }@Test
    void ShapeCopyFactoryFail() {
        Shape end = factory.copyShape(null);
        assertNull(end);
    }
}