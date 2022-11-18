package edu.uga.miage.m1.polygons.gui.file_management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import lombok.extern.java.Log;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

@Log
public class Export {
    private Export(){}

    public static File exportJSON(GroupShape shapesList,File export){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(export)))
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            writer.write(mapper.writeValueAsString(shapesList));
            return export;
        } catch (IOException e) {
            log.log(Level.WARNING,e.getMessage());
        }
        return null;
    }

    public static File exportXML(GroupShape shapesList,File export){
        try {
            JAXBContext context = JAXBContext.newInstance(GroupShape.class);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(shapesList, export);
            return new File(export.getPath());
        } catch (JAXBException e) {
            log.log(Level.WARNING,e.getMessage());
        }
        return null;
    }

}
