package edu.uga.miage.m1.polygons.gui.strategy;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonImportStrategyTest {

    @Test
    void importShapesFail() {
        File file = new File("./src/ressources/jsonFail.json");
        JsonImportStrategy importStrategy = new JsonImportStrategy();
        List<SimpleShape> shapes = importStrategy.importShapes(file);
        assertEquals(0, shapes.size());
    }
    @Test
    void importShapesSuccess() {
        File file = new File("./src/ressources/jsonSuccess.json");
        JsonImportStrategy importStrategy = new JsonImportStrategy();
        List<SimpleShape> shapes = importStrategy.importShapes(file);
        assertEquals(4, shapes.size());
    }
}