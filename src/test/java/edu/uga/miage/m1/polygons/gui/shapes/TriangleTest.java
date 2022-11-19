package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TriangleTest {


    @ParameterizedTest
    @MethodSource("intListOk")
    void isInsideOk(int x, int y) {
        Triangle triangle = new Triangle(35, 35);
        boolean res = triangle.isInside(x, y);
        assertTrue(res);
    }
    @ParameterizedTest
    @MethodSource("intListKo")
    void isInsideKo(int x, int y) {
        Triangle triangle = new Triangle(35, 35);
        boolean res = triangle.isInside(x, y);
        assertFalse(res);
    }


    static Stream<Arguments> intListOk() {
        return Stream.of(
                arguments(35, 35),
                arguments(41, 48),
                arguments(35, 50)
        );
    }

    static Stream<Arguments> intListKo() {
        return Stream.of(
                arguments(35, 61),
                arguments(89, 2),
                arguments(9, 9)
        );
    }
}