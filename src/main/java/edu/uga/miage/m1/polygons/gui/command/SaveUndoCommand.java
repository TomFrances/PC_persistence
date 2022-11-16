package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;

public class SaveUndoCommand extends Command {

    public SaveUndoCommand(JDrawingFrame jDrawingFrame) {
        super(jDrawingFrame);
    }

    public boolean execute() {
        jDrawingFrame.getUndoStack().push(copyShapeList());
        return true;
    }

}
