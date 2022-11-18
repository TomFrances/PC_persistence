package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.ShapeEditor;

public class ResetCommand extends Command {

    public ResetCommand(ShapeEditor shapeEditor) {
        super(shapeEditor);
    }

    @Override
    public boolean execute() {
        shapeEditor.reset();
        return true;
    }
}
