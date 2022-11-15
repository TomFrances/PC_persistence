package edu.uga.miage.m1.polygons.gui.file_management;

import edu.uga.miage.m1.polygons.gui.shapes.Shape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import edu.uga.miage.m1.polygons.gui.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ImportTest {


    @Test
    void importShapesFileJson() {
        File file = new File("./src/test/ressources/jsonSuccess.json");
        MockedStatic<FileUtils> mocked = Mockito.mockStatic(FileUtils.class);
        mocked.when(FileUtils::chooseFile).thenReturn(file);
        mocked.when(() -> FileUtils.getExtension(file)).thenReturn("json");
        List<Shape> shapesList = Import.importShapesFile();
        assertEquals(3, shapesList.size());
        Triangle triangle = new Triangle(257, 58);
        assertEquals(triangle.getClass(), shapesList.get(1).getClass());
        assertEquals(triangle.getY(), shapesList.get(1).getY());
        assertEquals(triangle.getX(), shapesList.get(1).getX());
        mocked.close();
    }

    @Test
    void importShapesFileXml() {
        MockedStatic<FileUtils> mocked = Mockito.mockStatic(FileUtils.class);
        File file = new File("./src/test/ressources/file1.xml");
        mocked.when(FileUtils::chooseFile).thenReturn(file);
        mocked.when(() -> FileUtils.getExtension(file)).thenReturn("xml");
        List<Shape> shapesList = Import.importShapesFile();
        assertEquals(2, shapesList.size());
        Square square = new Square(101, 105);
        assertEquals(square.getClass(), shapesList.get(0).getClass());
        assertEquals(square.getY(), shapesList.get(0).getY());
        assertEquals(square.getX(), shapesList.get(0).getX());
        mocked.close();
    }

}