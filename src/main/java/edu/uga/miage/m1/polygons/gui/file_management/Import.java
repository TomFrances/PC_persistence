package edu.uga.miage.m1.polygons.gui.file_management;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import lombok.Getter;
import lombok.extern.java.Log;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.logging.Logger;

@Log
@Getter
public class Import {

    public static GroupShape importXML(File file){
        try {
            JAXBContext context = JAXBContext.newInstance(GroupShape.class);
            return ((GroupShape) context.createUnmarshaller().unmarshal(new FileReader(file)));
        } catch (FileNotFoundException | JAXBException e) {
            Logger.getLogger(e.getMessage());
        }
        return null;
    }


    public static GroupShape importJSON(File file){
        try {
            return new ObjectMapper().readValue(new FileReader(file), GroupShape.class);
        } catch (IOException e) {
            Logger.getLogger(e.getMessage());
        }
        return null;
    }
}
