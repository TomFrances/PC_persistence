package edu.uga.miage.m1.polygons.gui.factory;

import edu.uga.miage.m1.polygons.gui.shapes.Shape;

public interface SimpleShapeFactory {
    Shape createShape(String type, int x, int y);
}
