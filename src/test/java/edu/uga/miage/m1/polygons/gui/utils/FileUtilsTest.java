package edu.uga.miage.m1.polygons.gui.utils;

import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FileUtilsTest {

    @Test
    void testGetXMLFileExtension() {
        File file = new File("./src/test/ressources/file1.xml");
        assertNotNull(file);
        assertEquals("file1.xml", file.getName());
        assertEquals("xml", FileUtils.getExtension(file));
    }

    @Test
    void testGetJsonFileExtension() {
        File file = new File("./src/test/ressources/file1.json");
        assertNotNull(file);
        assertEquals("file1.json", file.getName());
        assertEquals("json", FileUtils.getExtension(file));
    }

}