package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CircleTest {

    @ParameterizedTest
    @MethodSource("intListOk")
    void isInsideOK(int x, int y) {
        Circle circle = new Circle(34, 73);
        boolean resInside = circle.isInside(x, y);
        assertTrue(resInside);
    }

    @ParameterizedTest
    @MethodSource("intListKo")
    void isInsideKO(int x, int y) {
        Circle circle = new Circle(100, 100);
        boolean resInside = circle.isInside(x, y);
        assertFalse(resInside);
    }

    static Stream<Arguments> intListOk() {
        return Stream.of(
                arguments(23, 68),
                arguments(34, 73),
                arguments(59, 73)
        );
    }

    static Stream<Arguments> intListKo() {
        return Stream.of(
                arguments(100, 126),
                arguments(126, 100),
                arguments(119, 123)
        );
    }

}