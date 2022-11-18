package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.ShapeEditor;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;

public class RedoCommand extends Command {

    public RedoCommand(ShapeEditor shapeEditor) {
        super(shapeEditor);
    }

    public boolean execute() {
        if (!shapeEditor.redoStack.isEmpty()) {
            GroupShape shapes = shapeEditor.redoStack.pop();
            shapeEditor.saveStateForUndo();
            shapeEditor.setShapesList(shapes);
            return true;
        }
        return false;
    }
}
