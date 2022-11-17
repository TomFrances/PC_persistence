package edu.uga.miage.m1.polygons.gui.file_management;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;


class ImportTest {

    GroupShape expected = new GroupShape();

    @BeforeEach
    public void setUp() {
        expected.addShape(new Circle(32, 71));
        expected.addShape(new Circle(32, 72));
        GroupShape group2 = new GroupShape();
        group2.addShape(new Square(32, 73));
        group2.addShape(new Triangle(32, 74));
        expected.addShape(group2);
    }

    @Test
    void importXMLTest() {
        File file = new File("src/test/ressources/xmlExportExpected.xml");
        GroupShape group = Import.importXML(file);
        assertNotNull(group);
        assertEquals(expected.getShapes().size(), group.getShapes().size());
    }

    @Test
    void importJSONTest() {
        File file = new File("src/test/ressources/jsonExportExpected.json");
        GroupShape group = Import.importJSON(file);
        assertNotNull(group);
        assertEquals(expected.getShapes().size(), group.getShapes().size());
    }
}