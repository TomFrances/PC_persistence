package edu.uga.miage.m1.polygons.gui.strategy;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log
class JsonImportStrategyTest {

    @ParameterizedTest
    @ValueSource(strings = {"./src/test/ressources/jsonFail.json", "./src/test/ressources/inknownFile.json"})
    void importShapesFail(String args) {
        File file = new File(args);
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