package edu.uga.miage.m1.polygons.gui.command;

import edu.uga.miage.m1.polygons.gui.ShapeEditor;
import edu.uga.miage.m1.polygons.gui.copy_factory.ShapeCopyFactory;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    protected ShapeEditor shapeEditor;

    Command(ShapeEditor shapeEditor){
        this.shapeEditor = shapeEditor;
    }

    public abstract boolean execute();

    protected List<Shape> copyShapeList(){
        List<Shape> shapes = new ArrayList<>();
        for(Shape shape : shapeEditor.getShapesList().getShapes()){
            shapes.add((new ShapeCopyFactory()).copyShape(shape));
        }
        return shapes;
    }
}
