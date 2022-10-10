package edu.uga.miage.m1.polygons.gui.utils;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

public class ShapeUtils {

    private ShapeUtils(){}

    public static String getType(SimpleShape shape){
        String shapeClass = shape.getClass().toString();
        return shapeClass.substring(shapeClass.lastIndexOf(".") + 1).toLowerCase();
    }

}
