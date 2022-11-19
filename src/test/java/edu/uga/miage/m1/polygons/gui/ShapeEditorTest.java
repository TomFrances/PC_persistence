package edu.uga.miage.m1.polygons.gui;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ShapeEditorTest {

    ShapeEditor editor = new ShapeEditor();
    GroupShape group;
    GroupShape group2;
    Circle circle1;

    @BeforeEach
    void setUp() {
        editor.reset();
        group = new GroupShape();
        circle1 = new Circle(0, 100);
        group.addShape(circle1);
        group2 = new GroupShape();
        group2.addShape(new Square(0, 0));
        group2.addShape(new Triangle(100, 100));
        group.addShape(group2);
        group.addShape(new Circle(100, 0));
        editor.setShapesList(group);
    }

    @Test
    void createShape() {
        assertEquals(3,editor.getShapesList().getShapes().size());
        JPanel pan = new JPanel();
        editor.createShape(new MouseEvent(pan,0,0,0,0,100,0,false,0), Shapes.TRIANGLE);
        assertEquals(4,editor.getShapesList().getShapes().size());
    }

    @Test
    void disassembleGroupShape() {
        assertEquals(3,editor.getShapesList().getShapes().size());
        JPanel pan = new JPanel();
        editor.disassembleGroupShape(new MouseEvent(pan,0,0,0,100,100,0,false,0));
        assertEquals(4,editor.getShapesList().getShapes().size());
    }

    @ParameterizedTest
    @MethodSource("intListOk")
    void disassembleGroupShapeNotGroup(int x, int y) {
        assertEquals(3,editor.getShapesList().getShapes().size());
        JPanel pan = new JPanel();
        editor.disassembleGroupShape(new MouseEvent(pan,0,0,0,x,y,0,false,0));
        assertEquals(3,editor.getShapesList().getShapes().size());
    }
    static Stream<Arguments> intListOk() {
        return Stream.of(
                arguments(0, 100),
                arguments(200, 200)
        );
    }

    @Test
    void groupShape() {
        assertEquals(3,editor.getShapesList().getShapes().size());
        editor.startGrouping();
        JPanel pan = new JPanel();
        editor.groupShape(new MouseEvent(pan,0,0,0,0,100,0,false,0));
        assertEquals(2,editor.getShapesList().getShapes().size());
    }
    @Test
    void groupShapeNotFound() {
        assertEquals(3,editor.getShapesList().getShapes().size());
        editor.startGrouping();
        JPanel pan = new JPanel();
        editor.groupShape(new MouseEvent(pan,0,0,0,200,100,0,false,0));
        assertEquals(3,editor.getShapesList().getShapes().size());
    }

    @Test
    void startDraggingShape() {
        assertEquals(0,editor.getShapesList().getShapes().indexOf(circle1));
        JPanel pan = new JPanel();
        editor.startDraggingShape(new MouseEvent(pan,0,0,0,0,100,0,false,0));
        assertEquals(2,editor.getShapesList().getShapes().indexOf(circle1));
    }
    @Test
    void startDraggingGroupShape() {
        assertEquals(1,editor.getShapesList().getShapes().indexOf(group2));
        JPanel pan = new JPanel();
        editor.startDraggingShape(new MouseEvent(pan,0,0,0,100,100,0,false,0));
        assertEquals(2,editor.getShapesList().getShapes().indexOf(group2));
    }
    @Test
    void startDraggingShapeNotFound() {
        assertEquals(0,editor.getShapesList().getShapes().indexOf(circle1));
        JPanel pan = new JPanel();
        editor.startDraggingShape(new MouseEvent(pan,0,0,0,200,100,0,false,0));
        assertEquals(0,editor.getShapesList().getShapes().indexOf(circle1));
    }

    @Test
    void endGrouping() {
        assertEquals(3,editor.getShapesList().getShapes().size());
        GroupShape end = new GroupShape();
        end.addShape(new Triangle(50,50));
        editor.setGroupShape(end);
        editor.endGrouping();
        assertEquals(4,editor.getShapesList().getShapes().size());
    }

    @Test
    void endGrouping2Shape() {
        assertEquals(3,editor.getShapesList().getShapes().size());
        GroupShape end = new GroupShape();
        end.addShape(new Triangle(50,50));
        end.addShape(new Square(50,50));
        editor.setGroupShape(end);
        editor.endGrouping();
        assertEquals(4,editor.getShapesList().getShapes().size());
        assertEquals(GroupShape.class,editor.getShapesList().getShapes().get(3).getClass());
    }
    @Test
    void endGroupingEmpty() {
        assertEquals(3,editor.getShapesList().getShapes().size());
        GroupShape end = new GroupShape();
        editor.setGroupShape(end);
        editor.endGrouping();
        assertEquals(3,editor.getShapesList().getShapes().size());
    }

    @Test
    void dragShape() {
        editor.setDragged(circle1);
        int x = circle1.getX();
        int y = circle1.getY();
        JPanel pan = new JPanel();
        editor.dragShape(new MouseEvent(pan,0,0,0,50,50,0,false,0));
        assertNotEquals(x,circle1.getX());
        assertNotEquals(y,circle1.getY());
    }
    @Test
    void dragShapeNull() {
        editor.setDragged(null);
        int x = circle1.getX();
        int y = circle1.getY();
        JPanel pan = new JPanel();
        editor.dragShape(new MouseEvent(pan,0,0,0,50,50,0,false,0));
        assertEquals(x,circle1.getX());
        assertEquals(y,circle1.getY());
    }

    @Test
    void saveStateForUndo() {
        assertEquals(0,editor.undoStack.size());
        editor.saveStateForUndo();
        assertEquals(1,editor.undoStack.size());
    }

    @Test
    void saveStateForRedo() {
        assertEquals(0,editor.redoStack.size());
        editor.saveStateForRedo();
        assertEquals(1,editor.redoStack.size());
    }

    @Test
    void reset() {
        editor.reset();
        assertEquals(0, editor.getShapesList().getShapes().size());
        assertEquals(0, editor.redoStack.size());
        assertEquals(0, editor.undoStack.size());
    }
}