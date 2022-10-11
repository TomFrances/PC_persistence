package edu.uga.miage.m1.polygons.gui.utils;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

class ShapeUtilsTest {


    @Test
    void testGetTypeTriangle(){
        Triangle triangle = new Triangle(3,67);
        assertNotNull(triangle);
        String type = ShapeUtils.getType(triangle);
        assertEquals("triangle", type);
    }

    @Test
    void testGetTypeCircle(){
        Circle circle = new Circle(3,67);
        assertNotNull(circle);
        String type = ShapeUtils.getType(circle);
        assertEquals("circle", type);
    }

}