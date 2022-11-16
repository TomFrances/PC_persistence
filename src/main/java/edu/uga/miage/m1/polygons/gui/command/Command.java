package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;
import edu.uga.miage.m1.polygons.gui.copy_factory.ShapeCopyFactory;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    protected JDrawingFrame jDrawingFrame;

    Command(JDrawingFrame jDrawingFrame){
        this.jDrawingFrame = jDrawingFrame;
    }

    public abstract boolean execute();

    protected List<Shape> copyShapeList(){
        List<Shape> shapes = new ArrayList<>();
        for(Shape shape : jDrawingFrame.getShapesList()){
            shapes.add((new ShapeCopyFactory()).copyShape(shape));
        }
        return shapes;
    }
}
