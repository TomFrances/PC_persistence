package edu.uga.miage.m1.polygons.gui.strategy;

import edu.uga.miage.m1.polygons.gui.shapes.Shape;

import java.io.File;
import java.util.List;

public interface ImportStrategy {
    List<Shape> importShapes(File file);
}
