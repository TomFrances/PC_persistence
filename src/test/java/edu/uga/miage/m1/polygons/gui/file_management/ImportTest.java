package edu.uga.miage.m1.polygons.gui.file_management;

import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
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
        List<SimpleShape> shapesList = Import.importShapesFile();
        assertEquals(4, shapesList.size());
        Triangle triangle = new Triangle(257, 58);
        assertEquals(triangle.getClass(), shapesList.get(2).getClass());
        assertEquals(triangle.getY(), shapesList.get(2).getY());
        assertEquals(triangle.getX(), shapesList.get(2).getX());
        mocked.close();
    }

    @Test
    void importShapesFileXml() {
        MockedStatic<FileUtils> mocked = Mockito.mockStatic(FileUtils.class);
        File file = new File("./src/test/ressources/file1.xml");
        mocked.when(FileUtils::chooseFile).thenReturn(file);
        mocked.when(() -> FileUtils.getExtension(file)).thenReturn("xml");
        List<SimpleShape> shapesList = Import.importShapesFile();
        assertEquals(2, shapesList.size());
        Square square = new Square(101, 105);
        assertEquals(square.getClass(), shapesList.get(0).getClass());
        assertEquals(square.getY(), shapesList.get(0).getY());
        assertEquals(square.getX(), shapesList.get(0).getX());
        mocked.close();
    }

    @Test
    void importShapesFileYaml() {
        File file = new File("./src/test/ressources/file.yaml");
        MockedStatic<FileUtils> mocked = Mockito.mockStatic(FileUtils.class);
        mocked.when(FileUtils::chooseFile).thenReturn(file);
        mocked.when(() -> FileUtils.getExtension(file)).thenReturn("yaml");
        List<SimpleShape> shapesList = Import.importShapesFile();
        assertEquals(0, shapesList.size());
        mocked.close();
    }
}