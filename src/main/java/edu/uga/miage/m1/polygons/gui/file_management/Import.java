package edu.uga.miage.m1.polygons.gui.file_management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import lombok.extern.java.Log;
import java.io.*;
import java.util.logging.Level;

@Log
public class Import {
    private Import(){}
    public static GroupShape importXML(File file){
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(file, GroupShape.class);
        } catch (IOException e) {
            log.log(Level.INFO,e.getMessage());
        }
        return null;
    }


    public static GroupShape importJSON(File file){
        try {
            return new ObjectMapper().readValue(new FileReader(file), GroupShape.class);
        } catch (IOException e) {
            log.log(Level.INFO,e.getMessage());
        }
        return null;
    }
}
