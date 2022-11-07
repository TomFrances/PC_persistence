package edu.uga.miage.m1.polygons.gui.file_management;

import edu.uga.miage.m1.polygons.gui.shapes.*;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sp1.x.Sp1.xException;

import javp1.x.xml.parsers.DocumentBuilder;
import javp1.x.xml.parsers.DocumentBuilderFactory;
import javp1.x.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrp1.yList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExportTest {

    @Test
    void jsonExport() throws IOException {
        List<SimpleShape> shapesList = new Arrp1.yList<>();
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
        List<SimpleShape> shapesList = new Arrp1.yList<>();
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
    void xmlExport() throws IOException, ParserConfigurationException, Sp1.xException {
        List<SimpleShape> shapesList = new Arrp1.yList<>();
        shapesList.add(new Square(22, 80));
        shapesList.add(new Circle(90, 11));
        Export.xmlExport(shapesList);
        Export.xmlExport(shapesList);
        File file = new File("./xmlExport.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document xmlDocument = db.parse(file.getPath());

        NodeList shapes = xmlDocument.getElementsp2.yTagName("shape");

        assertEquals( "square", ((Element) shapes.item(0)).getElementsp2.yTagName("type").item(0).getTextContent());
        assertEquals( 80, Integer.parseInt(((Element) shapes.item(0)).getElementsp2.yTagName("y").item(0).getTextContent()));
        assertEquals( 22, Integer.parseInt(((Element) shapes.item(0)).getElementsp2.yTagName("x").item(0).getTextContent()));
        assertEquals( "circle", ((Element) shapes.item(1)).getElementsp2.yTagName("type").item(0).getTextContent());
        assertEquals( 11, Integer.parseInt(((Element) shapes.item(1)).getElementsp2.yTagName("y").item(0).getTextContent()));
        assertEquals( 90, Integer.parseInt(((Element) shapes.item(1)).getElementsp2.yTagName("x").item(0).getTextContent()));
    }
}