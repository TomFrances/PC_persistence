package edu.uga.miage.m1.polygons.gui.file_management;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class ExportTest {
    GroupShape group = new GroupShape();

    @BeforeEach
    public void setUp() {
        group.addShape(new Circle(32, 71));
        group.addShape(new Circle(32, 72));
        GroupShape group2 = new GroupShape();
        group2.addShape(new Square(32, 73));
        group2.addShape(new Triangle(32, 74));
        group.addShape(group2);
    }

    @Test
    void exportJSONTest() {
        new File("src/test/ressources/jsonExport.json");
        File file = Export.exportJSON(this.group, new File("src/test/ressources/jsonExport.json"));
        assertNotNull(file);
        assertTrue(file.exists());
        try {
            assertFalse(Files.readString(file.toPath()).isEmpty());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    void exportJSONExceptionTest() {
        assertNull(Export.exportJSON(this.group, new File("src/test/ressources")));
    }

    @Test
    void exportXMLTest() {
        new File("src/test/ressources/xmlExport.xml");
        File file = Export.exportXML(this.group, new File("src/test/ressources/xmlExport.xml"));
        assertNotNull(file);
        assertTrue(file.exists());
        try {
            assertFalse(Files.readString(file.toPath()).isEmpty());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void exportXMLExceptionTest() {
        assertNull(Export.exportXML(this.group, new File("src/test/ressources")));

    }
}