package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;

public abstract class Command {
    protected JDrawingFrame jDrawingFrame;

    Command(JDrawingFrame jDrawingFrame){
        this.jDrawingFrame = jDrawingFrame;
    }

    public abstract boolean execute();
}
