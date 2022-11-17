package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.ShapeEditor;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;

public class RedoCommand extends Command {

    public RedoCommand(ShapeEditor shapeEditor) {
        super(shapeEditor);
    }

    public boolean execute() {
        if (!shapeEditor.getRedoStack().isEmpty()) {
            GroupShape shapes = shapeEditor.getRedoStack().pop();
            shapeEditor.setShapesList(shapes);
            return true;
        }
        return false;
    }
}
