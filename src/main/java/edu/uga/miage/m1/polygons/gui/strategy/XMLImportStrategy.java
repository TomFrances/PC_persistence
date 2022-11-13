package edu.uga.miage.m1.polygons.gui.strategy;

import edu.uga.miage.m1.polygons.gui.factory.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;
import lombok.extern.java.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.logging.Level;

@Log
public class XMLImportStrategy implements ImportStrategy {

    @Override
    public List<Shape> importShapes(File file) {
        try {
            List<Shape> shapesList = new ArrayList<>();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmlDocument = db.parse(file.getPath());

            ShapeFactory shapeFactory = new ShapeFactory();
            NodeList shapes = xmlDocument.getElementsByTagName("shape");
            int nbShape = shapes.getLength();
            for (int i = 0; i < nbShape; i++) {
                Element shapeElement = (Element) shapes.item(i);
                shapesList.add(createShape(shapeElement, shapeFactory));
            }

            return shapesList;
        } catch (ParserConfigurationException | IOException |SAXException e) {
            log.log(Level.WARNING, "Parsing error: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    private Shape createShape(Element shapeElement, ShapeFactory shapeFactory){
        String type = shapeElement.getElementsByTagName("type").item(0).getTextContent();
        if(type.equals("groupshape")){
            GroupShape group = new GroupShape();
            group.setColor(new Color(Integer.parseInt(shapeElement.getElementsByTagName("color").item(0).getTextContent())));
            NodeList shapes = shapeElement.getElementsByTagName("groupShape");
            int nbShape = shapes.getLength();
            for (int i = 0; i < nbShape; i++) {
                Element el = (Element) shapes.item(i);
                group.addShape(createShape(el, shapeFactory));
            }
            return group;
        }
        int x = Integer.parseInt(shapeElement.getElementsByTagName("x").item(0).getTextContent());
        int y = Integer.parseInt(shapeElement.getElementsByTagName("y").item(0).getTextContent());
        return shapeFactory.createSimpleShape(type, x, y);
    }
}
