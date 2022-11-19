package edu.uga.miage.m1.polygons.gui.file_management;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import edu.uga.miage.m1.polygons.gui.shapes.GroupShape;
import lombok.extern.java.Log;
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(export)))
        {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(export,shapesList);
            return export;
        } catch (IOException e) {
            log.log(Level.WARNING,e.getMessage());
        }
        return null;
    }

}
