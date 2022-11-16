package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.ShapeEditor;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;

import java.util.List;

public class UndoCommand extends Command {

    public UndoCommand(ShapeEditor shapeEditor) {
        super(shapeEditor);
    }

    public boolean execute() {
        if (!shapeEditor.getUndoStack().isEmpty()) {
            List<Shape> shapes = shapeEditor.getUndoStack().pop();
            shapeEditor.setShapesList(shapes);
            return true;
        }
        return false;
    }
}
