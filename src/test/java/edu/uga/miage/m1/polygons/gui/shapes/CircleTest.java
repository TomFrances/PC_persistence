package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {
    @Test
    void getX() {
        Circle circle = new Circle(23, 43);
        assertNotNull(circle);
        assertEquals(23, circle.getX());
    }

    @Test
    void getY() {
        Circle circle = new Circle(23, 43);
        assertNotNull(circle);
        assertEquals(43, circle.getY());
    }
}