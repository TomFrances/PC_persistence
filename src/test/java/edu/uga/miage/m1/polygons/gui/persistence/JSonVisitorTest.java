package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Star;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

@Log
class JSonVisitorTest {

    @Test
    void testVisit() {
        assertTrue(true);
        JSonVisitor visitor = new JSonVisitor();
        assertNotNull(visitor);
        Star star = new Star(98, 45);
        assertNotNull(star);
        visitor.visit(star);
        String expectedRepresentation = "{\"type\":\"star\",\"x\":98,\"y\":45}";
        String representation = visitor.getRepresentation();
        assertEquals(expectedRepresentation, representation);
    }

    @Test
    void testGetRepresentation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Circle circle = new Circle(43, 12);
        assertNotNull(circle);
        String expectedRepresentation = "{\"type\":\"circle\",\"x\":43,\"y\":12}";
        JSonVisitor visitor = new JSonVisitor();
        assertNotNull(visitor);
        Method createRepresentationMethod = JSonVisitor.class.getDeclaredMethod("createRepresentation", SimpleShape.class);
        createRepresentationMethod.setAccessible(true);
        String representation = (String) createRepresentationMethod.invoke(visitor, circle);
        assertEquals(expectedRepresentation, representation);
    }

}