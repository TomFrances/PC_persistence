package edu.uga.miage.m1.polygons.gui.utils;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

public class SimpleShapeType {

    private SimpleShapeType() {}

    public static String get(SimpleShape shape){
        String shapeClass = shape.getClass().toString();
        return shapeClass.substring(shapeClass.lastIndexOf(".") + 1).toLowerCase();
    }


}
