package edu.uga.miage.m1.polygons.gui.persistence;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Star;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class XMLVisitorTest {

    @Test
    void testVisit() {
        XMLVisitor visitor = new XMLVisitor();
        assertNotNull(visitor);
        Triangle triangle = new Triangle(51, 99);
        assertNotNull(triangle);
        visitor.visit(triangle);
        String expectedRepresentation = "<shape><type>triangle</type><x>51</x><y>99</y></shape>";
        String representation = visitor.getRepresentation();
        assertEquals(expectedRepresentation, representation);
    }

    @Test
    void testGetRepresentation(){
        Circle circle = new Circle(71, 34);
        assertNotNull(circle);
        String expectedRepresentation = "<shape><type>circle</type><x>71</x><y>34</y></shape>";
        XMLVisitor visitor = new XMLVisitor();
        assertNotNull(visitor);
        visitor.visit(circle);
        assertEquals(expectedRepresentation, visitor.getRepresentation());
    }

}