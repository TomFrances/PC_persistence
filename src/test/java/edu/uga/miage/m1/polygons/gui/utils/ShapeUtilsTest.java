package edu.uga.miage.m1.polygons.gui.utils;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeUtilsTest {

    @Test
    void testConstructor(){
        ShapeUtils shapeUtils = new ShapeUtils();
        assertNotNull(shapeUtils);
    }

    @Test
    void testGetTypeTriangle(){
        ShapeUtils shapeUtils = new ShapeUtils();
        assertNotNull(shapeUtils);
        Triangle triangle = new Triangle(3,67);
        assertNotNull(triangle);
        String type = shapeUtils.getType(triangle);
        assertEquals("triangle", type);
    }

    @Test
    void testGetTypeCircle(){
        ShapeUtils shapeUtils = new ShapeUtils();
        assertNotNull(shapeUtils);
        Circle circle = new Circle(3,67);
        assertNotNull(circle);
        String type = shapeUtils.getType(circle);
        assertEquals("circle", type);
    }

}