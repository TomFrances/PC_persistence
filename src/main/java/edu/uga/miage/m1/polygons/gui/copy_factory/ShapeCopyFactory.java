package edu.uga.miage.m1.polygons.gui.copy_factory;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import edu.uga.miage.m1.polygons.gui.utils.ShapeUtils;

public class ShapeCopyFactory implements CopyFactory {

    public Shape copyShape(Shape shape){
        String type = ShapeUtils.getType(shape);
        return switch (type){
            case "triangle" -> new Triangle((Triangle) shape);
            case "square" -> new Square((Square) shape);
            case "circle" -> new Circle((Circle) shape);
            case "groupshape" -> new GroupShape((GroupShape) shape);
            default -> null;
        };
    }
}
