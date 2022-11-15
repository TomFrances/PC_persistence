package edu.uga.miage.m1.polygons.gui.shapes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SquareTest {

    @ParameterizedTest
    @MethodSource("intListOk")
    void isInsideOk(int x, int y) {
        Square square = new Square(35, 35);
        boolean res = square.isInside(x, y);
        assertTrue(res);
    }
    @ParameterizedTest
    @MethodSource("intListKo")
    void isInsideKo(int x, int y) {
        Square square = new Square(35, 35);
        boolean res = square.isInside(x, y);
        assertFalse(res);
    }


    static Stream<Arguments> intListOk() {
        return Stream.of(
                arguments(35, 35),
                arguments(39, 21),
                arguments(60, 60),
                arguments(10, 10)
        );
    }

    static Stream<Arguments> intListKo() {
        return Stream.of(
                arguments(89, 2),
                arguments(9, 53),
                arguments(35, 89),
                arguments(35, 3)
        );
    }
}