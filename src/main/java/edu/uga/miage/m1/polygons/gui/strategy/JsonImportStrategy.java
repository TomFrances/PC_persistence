package edu.uga.miage.m1.polygons.gui.strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uga.miage.m1.polygons.gui.shapes.*;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

@Log
public class JsonImportStrategy implements ImportStrategy {
    @Override
    public List<SimpleShape> importShapes(File file) {

        try {
            String fileContent = Files.readString(Path.of(file.getPath()));
            ObjectMapper mapper = new ObjectMapper();
            String[] strings = fileContent
                    .substring(fileContent.indexOf('[') + 1, fileContent.indexOf(']'))
                    .replace("},", "}#")
                    .split("#");
            
            List<SimpleShape> shapeList = new ArrayList<>();
            for (int i = 0; i < strings.length; i++) {
                strings[i] = strings[i].trim();
                String type = mapper.readTree(strings[i]).get("type").asText();
                strings[i] = strings[i].replace("{\"type\":\"" + type + "\",", "{");
                shapeList.add(toShape(strings[i], type));
            }
            return shapeList;
        } catch (IOException e) {
            log.log(Level.WARNING, e.getMessage());
        }
        return Collections.emptyList();
    }

    public SimpleShape toShape(String stringShape, String type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String typeMaj = type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
            return (SimpleShape) mapper.readValue(stringShape, Class.forName("edu.uga.miage.m1.polygons.gui.shapes." + typeMaj));
        } catch (JsonProcessingException | ClassNotFoundException e) {
            log.log(Level.WARNING, e.getMessage());
        }
        return null;
    }

}
