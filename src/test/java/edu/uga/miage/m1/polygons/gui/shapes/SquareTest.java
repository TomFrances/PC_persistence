package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    @Test
    void getX() {
        Square square = new Square(23, 43);
        assertNotNull(square);
        assertEquals(23, square.getX());
    }

    @Test
    void getY() {
        Square square = new Square(23, 43);
        assertNotNull(square);
        assertEquals(43, square.getY());
    }
}