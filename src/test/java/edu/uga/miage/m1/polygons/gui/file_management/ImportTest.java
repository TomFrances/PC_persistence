package edu.uga.miage.m1.polygons.gui.file_management;

import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImportTest {

    @Test
    void testImportShapesFile() throws NoSuchMethodException {
        int x = 1;
        assertEquals(1, x);
        Import importTool = new Import();
        Method m  = Import.class.getDeclaredMethod("openFile",null);
        when(m).then(null);
        importTool.importShapesFile();
    }
}