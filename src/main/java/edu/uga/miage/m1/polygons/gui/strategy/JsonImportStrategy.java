package edu.uga.miage.m1.polygons.gui.strategy;

import edu.uga.miage.m1.polygons.gui.factory.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.*;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;
import lombok.extern.java.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;
import java.util.logging.Level;

@Log
public class JsonImportStrategy implements ImportStrategy {
    @Override
    public List<Shape> importShapes(File file) {
        ArrayList<Shape> shapes = new ArrayList<>();
        try {
            ShapeFactory shapeFactory = new ShapeFactory();
            String fileContent = Files.readString(Path.of(file.getPath()));
            JSONObject jsonObject = new JSONObject(fileContent);
            JSONArray list = jsonObject.getJSONArray("shapes");
            list.forEach(object -> {
                JSONObject shape = (JSONObject) object;
                String type = shape.getString("type");
                if(type.equals("groupshape")){
                    GroupShape group = new GroupShape();
                    group.setColor(new Color(shape.getInt("color")));
                    JSONArray groupShapes = shape.getJSONArray("shapes");
                    groupShapes.forEach(s -> {
                        JSONObject shapeObject = (JSONObject) s;

                        String sType = shapeObject.getString("type");
                        int x = shapeObject.getInt("x");
                        int y = shapeObject.getInt("y");
                        group.addShape(shapeFactory.createSimpleShape(sType, x, y));
                    });
                    shapes.add(group);
                }else{
                    int x = shape.getInt("x");
                    int y = shape.getInt("y");
                    shapes.add(shapeFactory.createSimpleShape(type, x, y));
                }
            });
        } catch (IOException e) {
            log.log(Level.WARNING, String.format("JSON Import error : %s, file %s", e.getMessage(), file.getPath()));
        } catch (JSONException e) {
            log.log(Level.WARNING, String.format("JSON Import error : %s", e.getMessage()));
        }
        return shapes;
    }
}
