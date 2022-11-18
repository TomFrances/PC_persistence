package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.ShapeEditor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResetCommandTest {

    @Test
    void execute() {
        ShapeEditor shapeEditor = new ShapeEditor();
        shapeEditor.setShapesList(new GroupShape());
        shapeEditor.getShapesList().addShape(new Circle(4, 4));
        assertNotNull(shapeEditor.getShapesList());
        assertEquals(4, shapeEditor.getShapesList().getShape(0).getX());

        (new ResetCommand(shapeEditor)).execute();
        assertEquals(0, shapeEditor.getShapesList().getShapes().size());
    }
}