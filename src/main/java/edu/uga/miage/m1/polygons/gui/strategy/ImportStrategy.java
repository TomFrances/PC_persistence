package edu.uga.miage.m1.polygons.gui.strategy;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

import java.io.File;
import java.util.List;

public interface ImportStrategy {
    List<SimpleShape> importShapes(File file);
}
