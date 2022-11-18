package edu.uga.miage.m1.polygons.gui;

import edu.uga.miage.m1.polygons.gui.copy_factory.ShapeCopyFactory;
import edu.uga.miage.m1.polygons.gui.factory.ShapeFactory;
import edu.uga.miage.m1.polygons.gui.shapes.*;
import edu.uga.miage.m1.polygons.gui.shapes.Shape;
import edu.uga.miage.m1.polygons.gui.utils.Drawer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class ShapeEditor {
    private Shape dragged;
    private GroupShape groupShape;
    private GroupShape shapesList = new GroupShape();//NOSONAR
    private final ShapeFactory shapeFactory = new ShapeFactory();//NOSONAR
    public final Deque<GroupShape> undoStack = new ArrayDeque<>();
    public final Deque<GroupShape> redoStack = new ArrayDeque<>();
    private final Drawer drawer = new Drawer();

    public void setDragged(Shape dragged) {
        this.dragged = dragged;
    }

    public void setGroupShape(GroupShape groupShape) {
        this.groupShape = groupShape;
    }

    public void setShapesList(GroupShape shapesList) {
        this.shapesList = shapesList;
        drawer.drawAllShapes(shapesList);
    }

    public GroupShape getShapesList(){
        return this.shapesList;
    }

    public void createShape(MouseEvent evt, Shapes type) {
        clearRedo();
        saveStateForUndo();
        Shape shape = shapeFactory.createShape(type.value, evt.getX(), evt.getY());
        drawer.drawShape(shape, Color.BLACK);
        shapesList.addShape(shape);
    }

    public void disassembleGroupShape(MouseEvent evt) {
        clearRedo();
        int i = findShapeIndex(evt);
        if (i >= 0) {
            Shape shape = shapesList.getShape(i);
            if (shape instanceof GroupShape sl) {
                List<Shape> s = sl.getShapes();
                shapesList.remove(i);
                shapesList.addAllShapes(s);
                drawer.drawAllShapes(shapesList);
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
        clearRedo();
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
        clearRedo();
        if (groupShape.getShapes().size() > 1) {
            shapesList.addShape(groupShape);
        } else if (groupShape.getShapes().size() == 1) {
            shapesList.addShape(groupShape.getShapes().get(0));
        }
        drawer.drawAllShapes(shapesList);
        setGroupShape(null);
    }

    public void dragShape(MouseEvent evt) {
        if (Objects.nonNull(dragged)) {
            dragged.moveTo(evt.getX(), evt.getY());
            drawer.drawAllShapes(shapesList);
        }
    }

    private int findShapeIndex(MouseEvent evt) {
        int i = shapesList.getShapes().size() - 1;
        while (i >= 0 && !shapesList.getShapes().get(i).isInside(evt.getX(), evt.getY())) {
            i--;
        }
        return i;
    }

    public void saveStateForUndo() {
        undoStack.push(copyShapeList());
    }

    public void saveStateForRedo() {
        redoStack.push(copyShapeList());
    }

    private void clearRedo() {
        redoStack.clear();
    }

    private void clearUndo() {
        undoStack.clear();
    }

    private GroupShape copyShapeList() {
        GroupShape shapes = new GroupShape();
        for (Shape shape : shapesList.getShapes()) {
            shapes.addShape((new ShapeCopyFactory()).copyShape(shape));
        }
        return shapes;
    }

    public void reset() {
        setShapesList(new GroupShape());
        clearRedo();
        clearUndo();
        setDragged(null);
        setGroupShape(null);
        drawer.drawAllShapes(shapesList);
    }
}
