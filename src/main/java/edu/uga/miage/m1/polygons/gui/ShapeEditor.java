package edu.uga.miage.m1.polygons.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.uga.miage.m1.polygons.gui.copy_factory.ShapeCopyFactory;
import edu.uga.miage.m1.polygons.gui.factory.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class ShapeEditor {
    private Shape dragged;
    private GroupShape groupShape;
    private GroupShape shapesList = new GroupShape();//NOSONAR
    private final ShapeFactory shapeFactory = new ShapeFactory();//NOSONAR
    private final Deque<GroupShape> undoStack = new ArrayDeque<>();
    private final Deque<GroupShape> redoStack = new ArrayDeque<>();

    public Shape getDragged() {
        return dragged;
    }

    public void setDragged(Shape dragged) {
        this.dragged = dragged;
    }

    public void setGroupShape(GroupShape groupShape) {
        this.groupShape = groupShape;
    }

    public List<Shape> getShapesList() {
        return shapesList.getShapes();
    }

    public void setShapesList(GroupShape shapesList) {
        this.shapesList = shapesList;
    }

    public Deque<GroupShape> getUndoStack() {
        return undoStack;
    }

    public Deque<GroupShape> getRedoStack() {
        return redoStack;
    }

    public Shape createShape(MouseEvent evt, Shapes type) {
        saveStateForUndo();
        Shape shape = shapeFactory.createShape(type.value, evt.getX(), evt.getY());
        shapesList.addShape(shape);
        return shape;
    }
    public void exportXML(){

        try {
            JAXBContext context = JAXBContext.newInstance(GroupShape.class);
            Marshaller mar= context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(shapesList, new File("./export.xml"));
        } catch (JAXBException e) {
            Logger.getLogger(e.getMessage());
        }

    }
    public void importXML(){
        try {
            JAXBContext context = JAXBContext.newInstance(GroupShape.class);
            setShapesList((GroupShape) context.createUnmarshaller().unmarshal(new FileReader("./export.xml")));
        } catch (FileNotFoundException | JAXBException e) {
            Logger.getLogger(e.getMessage());
        }

    }

    public void exportJSON(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./exportJson.json")))
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            writer.write(mapper.writeValueAsString(shapesList));
        } catch (IOException e) {
            Logger.getLogger(e.getMessage());
        }

    }
    public void importJSON(){
        try {
            setShapesList(new ObjectMapper().readValue(new FileReader("./exportJson.json"), GroupShape.class));
        } catch (IOException e) {
            Logger.getLogger(e.getMessage());
        }


    }
    public void disassembleGroupShape(MouseEvent evt) {
        int i = findShapeIndex(evt);
        if (i >= 0) {
            Shape shape = shapesList.getShape(i);
            if (shape instanceof GroupShape sl) {
                List<Shape> s = sl.getShapes();
                shapesList.remove(i);
                shapesList.addAllShapes(s);
            }
        }
    }

    public void groupShape(MouseEvent evt) {
        int i = findShapeIndex(evt);
        if (i >= 0) {
            Shape shape = shapesList.remove(i);
            groupShape.addShape(shape);
        }
    }

    public void startDraggingShape(MouseEvent evt) {
        int i = findShapeIndex(evt);
        if (i >= 0) {
            saveStateForUndo();
            Shape shape = shapesList.remove(i);
            setDragged(shape);
            if (dragged instanceof GroupShape s) {
                s.setCoordinate(evt.getX(), evt.getY());
            }
            shapesList.addShape(shape);
        }
    }

    public void startGrouping() {
        saveStateForUndo();
        setGroupShape(new GroupShape());
    }

    public void endGrouping() {
        if (groupShape.getShapes().size() > 1) {
            shapesList.addShape(groupShape);
        } else if (groupShape.getShapes().size() == 1) {
            shapesList.addShape(groupShape.getShapes().get(0));
        }
        setGroupShape(null);
    }

    public void dragShape(MouseEvent evt) {
        if (Objects.nonNull(dragged)) {
            dragged.moveTo(evt.getX(), evt.getY());
        }
    }

    private int findShapeIndex(MouseEvent evt) {
        int i = getShapesList().size() - 1;
        while (i >= 0 && !getShapesList().get(i).isInside(evt.getX(), evt.getY())) {
            i--;
        }
        return i;
    }

    private void saveStateForUndo(){
        undoStack.push(copyShapeList());
    }

    private GroupShape copyShapeList() {
        GroupShape shapes = new GroupShape();
        for (Shape shape : getShapesList()) {
            shapes.addShape((new ShapeCopyFactory()).copyShape(shape));
        }
        return shapes;
    }
}
