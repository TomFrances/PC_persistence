package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;

import java.util.List;

public class UndoCommand extends Command {

    public UndoCommand(JDrawingFrame jDrawingFrame) {
        super(jDrawingFrame);
    }

    public boolean execute() {
        if (!jDrawingFrame.getUndoStack().isEmpty()) {
            List<Shape> shapes = jDrawingFrame.getUndoStack().pop();
            jDrawingFrame.setShapesList(shapes);
            jDrawingFrame.drawALlShapes();
            return true;
        }
        return false;
    }
}
