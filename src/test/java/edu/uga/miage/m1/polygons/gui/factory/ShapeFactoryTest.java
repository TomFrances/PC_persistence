package edu.uga.miage.m1.polygons.gui.factory;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeFactoryTest {

    @Test
    void testConstructor() {
        ShapeFactory shapeFactory = new ShapeFactory();
        assertNotNull(shapeFactory);
    }

    @Test
    void testCreateSimpleShapeSquare() {
        String type = "square";
        int x = 11;
        int y = 1;
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape square = shapeFactory.createShape(type, x, y);
        assertNotNull(square);
        assert(square instanceof Square);
        assertEquals(11, square.getX());
        assertEquals(1, square.getY());
    }

    @Test
    void testCreateSimpleShapeTriangle() {
        String type = "triangle";
        int x = 23;
        int y = 182;
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape triangle = shapeFactory.createShape(type, x, y);
        assertNotNull(triangle);
        assert(triangle instanceof Triangle);
        assertEquals(23, triangle.getX());
        assertEquals(182, triangle.getY());
    }

    @Test
    void testCreateGroupShape() {
        String type = "groupshape";
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape group = shapeFactory.createShape(type, 0, 0);
        assertNotNull(group);
        assert(group instanceof GroupShape);
        assertEquals(0, group.getX());
        assertEquals(0, group.getY());
    }@Test
    void testCreateCircle() {
        String type = "circle";
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape group = shapeFactory.createShape(type, 0, 0);
        assertNotNull(group);
        assert(group instanceof Circle);
        assertEquals(0, group.getX());
        assertEquals(0, group.getY());
    }

    @Test
    void testCreateSimpleShapeDefault() {
        String type = "rectangle";
        int x = 23;
        int y = 182;
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.createShape(type, x, y);
        assertNull(shape);
    }
}