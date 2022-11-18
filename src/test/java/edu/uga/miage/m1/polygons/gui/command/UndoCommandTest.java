package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.ShapeEditor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
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
        boolean res = (new UndoCommand(shapeEditor)).execute();
        assertTrue(res);
    }

    @Test
    void executeKO() {
        ShapeEditor shapeEditor = new ShapeEditor();
        boolean res = (new UndoCommand(shapeEditor)).execute();
        assertFalse(res);
    }
}