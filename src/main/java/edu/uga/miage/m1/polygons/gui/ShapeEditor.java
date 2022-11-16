package edu.uga.miage.m1.polygons.gui;

import edu.uga.miage.m1.polygons.gui.copy_factory.ShapeCopyFactory;
import edu.uga.miage.m1.polygons.gui.factory.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.*;

import java.awt.event.MouseEvent;
import java.util.*;

public class ShapeEditor {
    private Shape dragged;
    private GroupShape groupShape;
    private List<Shape> shapesList = new ArrayList<>();//NOSONAR
    private final ShapeFactory shapeFactory = new ShapeFactory();//NOSONAR
    private final Deque<List<Shape>> undoStack = new ArrayDeque<>();
    private final Deque<List<Shape>> redoStack = new ArrayDeque<>();

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
        return shapesList;
    }

    public void setShapesList(List<Shape> shapesList) {
        this.shapesList = shapesList;
    }

    public Deque<List<Shape>> getUndoStack() {
        return undoStack;
    }

    public Deque<List<Shape>> getRedoStack() {
        return redoStack;
    }

    public Shape createShape(MouseEvent evt, Shapes type) {
        saveStateForUndo();
        Shape shape = shapeFactory.createShape(type.value, evt.getX(), evt.getY());
        shapesList.add(shape);
        return shape;
    }

    public void disassembleGroupShape(MouseEvent evt) {
        int i = findShapeIndex(evt);
        if (i >= 0) {
            Shape shape = shapesList.get(i);
            if (shape instanceof GroupShape sl) {
                shapesList.remove(shape);
                shapesList.addAll(sl.getShapes());
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
            shapesList.add(shape);
        }
    }

    public void startGrouping() {
        saveStateForUndo();
        setGroupShape(new GroupShape());
    }

    public void endGrouping() {
        if (groupShape.getShapes().size() > 1) {
            shapesList.add(groupShape);
        } else if (groupShape.getShapes().size() == 1) {
            shapesList.add(groupShape.getShapes().get(0));
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

    private List<Shape> copyShapeList() {
        List<Shape> shapes = new ArrayList<>();
        for (Shape shape : getShapesList()) {
            shapes.add((new ShapeCopyFactory()).copyShape(shape));
        }
        return shapes;
    }
}
