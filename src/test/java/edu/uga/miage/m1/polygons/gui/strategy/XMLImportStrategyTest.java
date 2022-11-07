package edu.uga.miage.m1.polygons.gui.strategy;

import edu.uga.miage.m1.polygons.gui.factory.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.file_management.Import;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrp1.yList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XMLImportStrategyTest {

    @Test
    void testImportShapesSuccess() {
        File file = new File("./src/test/ressources/file1.xml");
        assertNotNull(file);
        List<SimpleShape> expectedShapesList = new Arrp1.yList<>();
        expectedShapesList.add(new Square(101, 105));
        expectedShapesList.add(new Triangle(22, 163));
        assertEquals(2, expectedShapesList.size());
        List<SimpleShape> resultShapesList = (new XMLImportStrategy()).importShapes(file);
        assertNotNull(resultShapesList);
        assertTrue(resultShapesList.get(0) instanceof Square);
        assertEquals(2, resultShapesList.size());
        assertEquals(101, resultShapesList.get(0).getX());
        assertEquals(105, resultShapesList.get(0).getY());
        assertTrue(resultShapesList.get(1) instanceof Triangle);
        assertEquals(22, resultShapesList.get(1).getX());
        assertEquals(163, resultShapesList.get(1).getY());
    }

    @Test
    void testImportShapesFail() {
        XMLImportStrategy xmlImportStrategy = new XMLImportStrategy();
        List<SimpleShape> shapesList = xmlImportStrategy.importShapes(new File("./src/test/ressources/fileUnknown.xml"));
        assertEquals(0, shapesList.size());
    }

}