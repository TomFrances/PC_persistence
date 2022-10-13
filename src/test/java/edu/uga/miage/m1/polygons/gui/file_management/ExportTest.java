package edu.uga.miage.m1.polygons.gui.file_management;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExportTest {

    @Test
    void jsonExport() throws IOException {
        List<SimpleShape> shapesList = new ArrayList<>();
        shapesList.add(new Triangle(55, 89));
        shapesList.add(new Star(34, 1));
        shapesList.add(new Triangle(34, 229));
        Export.jsonExport(shapesList);
        Export.jsonExport(shapesList);
        File file = new File("./jsonExport.json");
        String fileContent = Files.readString(Path.of(file.getPath()));
        String expectedContent = "{\"shapes\": [{\"type\":\"triangle\",\"x\":55,\"y\":89},{\"type\":\"star\",\"x\":34,\"y\":1},{\"type\":\"triangle\",\"x\":34,\"y\":229}]}";
        assertEquals(expectedContent, fileContent);
    }

    @Test
    void jsonExportFail() throws IOException {
        List<SimpleShape> shapesList = new ArrayList<>();
        shapesList.add(new Triangle(55, 89));
        shapesList.add(new Star(34, 1));
        shapesList.add(new Triangle(34, 229));
        Export.jsonExport(shapesList);
        Export.jsonExport(shapesList);
        File file = new File("./jsonExport.json");
        String fileContent = Files.readString(Path.of(file.getPath()));
        String expectedContent = "{\"shapes\": [{\"type\":\"triangle\",\"x\":55,\"y\":89},{\"type\":\"star\",\"x\":34,\"y\":1},{\"type\":\"triangle\",\"x\":34,\"y\":229}]}";
        assertEquals(expectedContent, fileContent);
    }

    @Test
    void xmlExport() throws IOException, ParserConfigurationException, SAXException {
        List<SimpleShape> shapesList = new ArrayList<>();
        shapesList.add(new Square(22, 80));
        shapesList.add(new Circle(90, 11));
        Export.xmlExport(shapesList);
        Export.xmlExport(shapesList);
        File file = new File("./xmlExport.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document xmlDocument = db.parse(file.getPath());

        NodeList shapes = xmlDocument.getElementsByTagName("shape");

        assertEquals( "square", ((Element) shapes.item(0)).getElementsByTagName("type").item(0).getTextContent());
        assertEquals( 80, Integer.parseInt(((Element) shapes.item(0)).getElementsByTagName("y").item(0).getTextContent()));
        assertEquals( 22, Integer.parseInt(((Element) shapes.item(0)).getElementsByTagName("x").item(0).getTextContent()));
        assertEquals( "circle", ((Element) shapes.item(1)).getElementsByTagName("type").item(0).getTextContent());
        assertEquals( 11, Integer.parseInt(((Element) shapes.item(1)).getElementsByTagName("y").item(0).getTextContent()));
        assertEquals( 90, Integer.parseInt(((Element) shapes.item(1)).getElementsByTagName("x").item(0).getTextContent()));
    }
}