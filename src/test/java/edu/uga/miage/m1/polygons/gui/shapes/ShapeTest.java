package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {

    @Test
    void getX() {
        Circle circle = new Circle(45, 23);
        int x = circle.getX();
        assertEquals(45, x);
    }

    @Test
    void setX() {
        Circle circle = new Circle(34,1);
        assertEquals(34, circle.getX());
        circle.setX(78);
        assertEquals(78, circle.getX());
    }

    @Test
    void getY() {
        Triangle triangle = new Triangle(45, 23);
        int y = triangle.getY();
        assertEquals(23, y);
    }

    @Test
    void setY() {
        Square square = new Square(34,89);
        assertEquals(89, square.getY());
        square.setY(56);
        assertEquals(56, square.getY());
    }

    @Test
    void moveTo() {
        Triangle triangle = new Triangle(67, 89);
        assertEquals(67, triangle.getX());
        assertEquals(89, triangle.getY());
        triangle.moveTo(100, 54);
        assertEquals(100, triangle.getX());
        assertEquals(54, triangle.getY());
    }
}