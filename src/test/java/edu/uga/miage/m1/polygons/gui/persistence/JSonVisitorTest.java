package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
class JSonVisitorTest {

    @Test
    void testVisit() {
        JSonVisitor visitor = new JSonVisitor();
        assertNotNull(visitor);
        Triangle triangle = new Triangle(98, 45);
        assertNotNull(triangle);
        visitor.visit(triangle);
        String expectedRepresentation = "{\"type\":\"triangle\",\"x\":98,\"y\":45}";
        String representation = visitor.getRepresentation();
        assertEquals(expectedRepresentation, representation);
    }

    @Test
    void testGetRepresentation(){
        Square square = new Square(22, 90);
        assertNotNull(square);
        String expectedRepresentation = "{\"type\":\"square\",\"x\":22,\"y\":90}";
        JSonVisitor visitor = new JSonVisitor();
        assertNotNull(visitor);
        visitor.visit(square);
        assertEquals(expectedRepresentation, visitor.getRepresentation());
    }
}