package edu.uga.miage.m1.polygons.gui.utils;

import edu.uga.miage.m1.polygons.gui.shapes.Shape;

public class ShapeUtils {

    private ShapeUtils(){}

    public static String getType(Shape shape){
        if(shape == null){
            return "";
        }
        String shapeClass = shape.getClass().toString();
        return shapeClass.substring(shapeClass.lastIndexOf(".") + 1).toLowerCase();
    }

}
