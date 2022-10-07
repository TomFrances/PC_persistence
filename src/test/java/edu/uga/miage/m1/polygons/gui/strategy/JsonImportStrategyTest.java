package edu.uga.miage.m1.polygons.gui.strategy;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

@Log
class JsonImportStrategyTest {

    @Test
    void importShapesFail() {
        File file = new File("./src/test/ressources/jsonFail.json");
        JsonImportStrategy importStrategy = new JsonImportStrategy();
        List<SimpleShape> shapes = importStrategy.importShapes(file);
        assertEquals(0, shapes.size());
    }
    @Test
    void importShapesSuccess() {
        File file = new File("./src/test/ressources/jsonSuccess.json");
        JsonImportStrategy importStrategy = new JsonImportStrategy();
        List<SimpleShape> shapes = importStrategy.importShapes(file);
        assertEquals(4, shapes.size());
    }
}