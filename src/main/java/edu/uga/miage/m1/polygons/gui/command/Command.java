package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.ShapeEditor;

public abstract class Command {
    protected ShapeEditor shapeEditor;

    Command(ShapeEditor shapeEditorz){
        this.shapeEditor = shapeEditor;
    }

    public abstract boolean execute();

}
