package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.ShapeEditor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UndoCommandTest {

    @Test
    void executeOK() {
        ShapeEditor shapeEditor = new ShapeEditor();
        GroupShape group = new GroupShape();
        group.addShape(new Circle(4, 1));
        shapeEditor.setShapesList(group);
        shapeEditor.saveStateForUndo();
        group.addShape(new Square(33, 90));
        shapeEditor.saveStateForUndo();
        assertEquals(2, shapeEditor.undoStack.size());
        boolean res = (new UndoCommand(shapeEditor)).execute();
        assertTrue(res);
        System.out.println(shapeEditor.undoStack);
        assert shapeEditor.undoStack.peek() != null;
        assertEquals(4, shapeEditor.undoStack.peek().getShapes().get(0).getX());
    }

    @Test
    void executeKO() {
        ShapeEditor shapeEditor = new ShapeEditor();
        boolean res = (new UndoCommand(shapeEditor)).execute();
        assertFalse(res);
    }
}