package edu.uga.miage.m1.polygons.gui.factory;


import edu.uga.miage.m1.polygons.gui.shapes.*;
import lombok.extern.java.Log;

import java.util.logging.Level;

@Log
public class ShapeFactory implements SimpleShapeFactory {
    @Override
    public SimpleShape createSimpleShape(String type, int x, int y) {

        switch (type) {
            case "triangle":
                return new Triangle(x, y);
            case "circle":
                return new Circle(x, y);
            case "square":
                return new Square(x, y);
            case "star":
                return new Star(x, y);
            default:
                log.log(Level.WARNING, "Incorrect shape found");
                return null;
        }
    }
}
