package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;

public class ClearRedoCommand extends Command {
    public ClearRedoCommand(JDrawingFrame jDrawingFrame){
        super(jDrawingFrame);
    }

    public boolean execute(){
        jDrawingFrame.getRedoStack().clear();
        return true;
    }
}
