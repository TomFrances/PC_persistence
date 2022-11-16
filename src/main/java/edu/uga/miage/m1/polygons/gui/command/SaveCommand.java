package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.copy_factory.ShapeCopyFactory;
import edu.uga.miage.m1.polygons.gui.shapes.*;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Log
public class SaveCommand extends Command {

    ShapeCopyFactory shapeCopy = new ShapeCopyFactory();
    public SaveCommand(JDrawingFrame jDrawingFrame) {
        super(jDrawingFrame);
    }

    public boolean execute() {
        jDrawingFrame.getUndoStack().push(copyShapeList());
        log.log(Level.INFO, "size : " + jDrawingFrame.getUndoStack().size());
        return true;
    }

    private List<Shape> copyShapeList(){
        List<Shape> shapes = new ArrayList<>();
        for(Shape shape : jDrawingFrame.getShapesList()){
            shapes.add(shapeCopy.copyShape(shape));
        }
        return shapes;
    }
}
