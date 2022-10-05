package edu.uga.miage.m1.polygons.gui.file_management;

import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Export {

    private static final String JSON_FILE_NAME = "jsonExport.json";
    private static final String XML_FILE_NAME = "xmlExport.xml";

    private static Visitor visitor;

    private Export() {
    }

    /**
     * Export all the drawn figure to a json format
     */
    public static void jsonExport(List<SimpleShape> shapesList) {
        try (FileWriter fileWriter = new FileWriter(JSON_FILE_NAME, false)) {
            if(!(visitor instanceof JSonVisitor)){
                visitor = new JSonVisitor();
            }

            String res = (shapesList
                    .stream()
                    .map(Export::createRepresentation)
                    .reduce("{\"shapes\": [", String::concat)
                    .replace("}{", "},{")
                    .concat("]}"));

            fileWriter.write(res);
        } catch (IOException ioe) {
            Logger.getLogger(ioe.getMessage());
        }
    }

    /**
     * Export all the drawn figure to a json format
     */
    public static void xmlExport(List<SimpleShape> shapesList) {
        try (FileWriter fileWriter = new FileWriter(XML_FILE_NAME, false)) {
            if(!(visitor instanceof XMLVisitor)){
                visitor = new XMLVisitor();
            }

            String res = (shapesList
                    .stream()
                    .map(Export::createRepresentation)
                    .reduce("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><root><shapes>", String::concat)
                    .concat("</shapes></root>"));

            fileWriter.write(res);
        } catch (IOException ioe) {
            Logger.getLogger(ioe.getMessage());
        }
    }

    private static String createRepresentation(SimpleShape shape) {
        shape.accept(visitor);
        return visitor.getRepresentation();
    }

}
