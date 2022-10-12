package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class StarTest {

    @Test
    void getX() {
        Star star = new Star(34, 89);
        assertEquals(34, star.getX());
    }

    @Test
    void getY() {
        Star star = new Star(34, 89);
        assertEquals(89, star.getY());
    }

    @Test
    void createStar() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Star star = new Star(50, 90);
        assertNotNull(star);
        Method method = Star.class.getDeclaredMethod("createStar", double.class, double.class);
        method.setAccessible(true);
        Shape shape = (Shape) method.invoke(star, star.getX(), star.getY());
        assertEquals(26, shape.getBounds().getX());
        assertEquals(65, shape.getBounds().getY());
    }
}