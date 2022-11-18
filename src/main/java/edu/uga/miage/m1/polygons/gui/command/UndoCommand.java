package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.ShapeEditor;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;

public class UndoCommand extends Command {

    public UndoCommand(ShapeEditor shapeEditor) {
        super(shapeEditor);
    }

    public boolean execute() {
        if (!shapeEditor.undoStack.isEmpty()) {
            GroupShape shapes = shapeEditor.undoStack.pop();
            shapeEditor.saveStateForRedo();
            shapeEditor.setShapesList(shapes);
            return true;
        }
        return false;
    }
}
