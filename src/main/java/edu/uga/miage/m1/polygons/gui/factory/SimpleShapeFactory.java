package edu.uga.miage.m1.polygons.gui.factory;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

public interface SimpleShapeFactory {
    SimpleShape createSimpleShape(String type, int x, int y);
}
