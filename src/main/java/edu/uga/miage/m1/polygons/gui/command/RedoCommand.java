package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;
import lombok.extern.java.Log;

import java.util.List;

public class RedoCommand extends Command {

    public RedoCommand(JDrawingFrame jDrawingFrame) {
        super(jDrawingFrame);
    }

    public boolean execute() {
        if (!jDrawingFrame.getRedoStack().isEmpty()) {
            List<Shape> shapes = jDrawingFrame.getRedoStack().pop();
            jDrawingFrame.setShapesList(shapes);
            jDrawingFrame.drawALlShapes();
            return true;
        }
        return false;
    }
}
