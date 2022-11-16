package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
public class SaveRedoCommand extends Command {
    public SaveRedoCommand(JDrawingFrame jDrawingFrame){
        super(jDrawingFrame);
    }

    public boolean execute(){
        jDrawingFrame.getRedoStack().push(copyShapeList());
        return true;
    }
}
