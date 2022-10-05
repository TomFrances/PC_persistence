package edu.uga.miage.m1.polygons.gui.utils;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileExtenstionTest {

    @Test
    void testGetXMLFile() {
        File file = new File("./src/ressources/file1.xml");
        assertNotNull(file);
        assertEquals("file1.xml", file.getName());
        assertEquals("xml", FileExtenstion.get(file));
    }

    @Test
    void testGetJsonFile() {
        File file = new File("./src/ressources/file1.json");
        assertNotNull(file);
        assertEquals("file1.json", file.getName());
        assertEquals("json", FileExtenstion.get(file));
    }
}