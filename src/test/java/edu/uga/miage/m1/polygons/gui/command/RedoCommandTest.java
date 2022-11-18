package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.ShapeEditor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RedoCommandTest {

    ShapeEditor shapeEditor = new ShapeEditor();

    @Test
    void testExecuteOk() {
        GroupShape group = new GroupShape();
        group.addShape(new Circle(3, 8));
        group.addShape(new Triangle(87, 31));
        shapeEditor.setShapesList(group);
        shapeEditor.saveStateForRedo();
        group.addShape(new Circle(78,78));

        RedoCommand command = new RedoCommand(shapeEditor);

        boolean res = command.execute();
        assertTrue(res);
    }

    @Test
    void testExecuteKO() {
        RedoCommand command = new RedoCommand(shapeEditor);

        boolean res = command.execute();
        assertFalse(res);
    }
}