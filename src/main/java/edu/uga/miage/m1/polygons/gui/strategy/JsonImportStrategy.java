package edu.uga.miage.m1.polygons.gui.strategy;

import edu.uga.miage.m1.polygons.gui.factory.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.*;
import lombok.extern.java.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Level;

@Log
public class JsonImportStrategy implements ImportStrategy {
    @Override
    public List<SimpleShape> importShapes(File file) {
        ArrayList<SimpleShape> shapes = new ArrayList<>();
        try {
            ShapeFactory shapeFactory = new ShapeFactory();
            String fileContent = Files.readString(Path.of(file.getPath()));
            JSONObject jsonObject = new JSONObject(fileContent);
            JSONArray list = jsonObject.getJSONArray("shapes");
            list.forEach(object -> {
                JSONObject shape = (JSONObject) object;
                String type = shape.getString("type");
                int x = shape.getInt("x");
                int y = shape.getInt("y");
                shapes.add(shapeFactory.createSimpleShape(type, x, y));
            });
        } catch (IOException e) {
            log.log(Level.WARNING, e.getMessage(), file.getPath());
        }
        return shapes;
    }
}
