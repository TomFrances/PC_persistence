package edu.uga.miage.m1.polygons.gui.strategy;

import edu.uga.miage.m1.polygons.gui.factory.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;
import lombok.extern.java.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;
import java.util.logging.Level;

@Log
public class XMLImportStrategy implements ImportStrategy {

    @Override
    public List<SimpleShape> importShapes(File file) {
        try {
            List<SimpleShape> shapesList = new ArrayList<>();

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
        }catch(Exception e){
            log.log(Level.WARNING, String.format("Parsing error : %s", e.getMessage()));
        }

        return Collections.emptyList();
    }

    private SimpleShape createShape(Element shapeElement, ShapeFactory shapeFactory){
        String type = shapeElement.getElementsByTagName("type").item(0).getTextContent();
        int x = Integer.parseInt(shapeElement.getElementsByTagName("x").item(0).getTextContent());
        int y = Integer.parseInt(shapeElement.getElementsByTagName("y").item(0).getTextContent());
        return shapeFactory.createSimpleShape(type, x, y);
    }
}
