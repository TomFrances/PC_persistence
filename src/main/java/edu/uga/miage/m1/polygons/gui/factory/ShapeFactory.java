package edu.uga.miage.m1.polygons.gui.factory;


import edu.uga.miage.m1.polygons.gui.shapes.*;
import lombok.extern.java.Log;


@Log
public class ShapeFactory implements SimpleShapeFactory {
    @Override
    public Shape createShape(String type, int x, int y) {

        return switch (type) {
            case "triangle" -> new Triangle(x, y);
            case "circle" -> new Circle(x, y);
            case "square" -> new Square(x, y);
            case "groupshape" -> new GroupShape(x, y);
            default -> null;
        };
    }
}
