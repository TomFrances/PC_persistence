package edu.uga.miage.m1.polygons.gui.utils;

import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FileUtilsTest {

    @Test
    void testConstructor() {
        FileUtils fileUtils = new FileUtils();
        assertNotNull(fileUtils);
    }

    @Test
    void testGetXMLFileExtension() {
        File file = new File("./src/ressources/file1.xml");
        FileUtils fileUtils = new FileUtils();
        assertNotNull(file);
        assertNotNull(fileUtils);
        assertEquals("file1.xml", file.getName());
        assertEquals("xml", fileUtils.getExtension(file));
    }

    @Test
    void testGetJsonFileExtension() {
        File file = new File("./src/ressources/file1.json");
        FileUtils fileUtils = new FileUtils();
        assertNotNull(file);
        assertNotNull(fileUtils);
        assertEquals("file1.json", file.getName());
        assertEquals("json", fileUtils.getExtension(file));
    }

    @Test
    void testOpenFile() {   //useless test
        FileUtils fileUtils = mock(FileUtils.class);
        assertNotNull(fileUtils);
        File expectedFile = new File("./src/ressources/file1.json");
        when(fileUtils.openFile()).thenReturn(expectedFile);
        File openedFile = fileUtils.openFile();
        assertEquals(expectedFile, openedFile);
    }
}