package edu.uga.miage.m1.polygons.gui.shapes;

import com.fasterxml.jackson.annotation.*;
import edu.uga.miage.m1.polygons.gui.copy_factory.ShapeCopyFactory;

import javax.xml.bind.annotation.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.List;


@JsonIgnoreProperties(value = { "color" })
@XmlType(propOrder = { "shapes" })
public class GroupShape extends Shape {

    @XmlElements(
            {
                    @XmlElement(name = "square", type = Square.class),
                    @XmlElement(name = "circle", type = Circle.class),
                    @XmlElement(name = "triangle", type = Triangle.class),
                    @XmlElement(name = "groupShape", type = GroupShape.class)
            }
    )
    @JsonProperty("shapes")
    private final List<Shape> shapes = new ArrayList<>();
    private Color color = null;
    @JsonCreator
    public GroupShape() {
        super(0, 0);
        Random rand = new SecureRandom();
        // Java 'Color' class takes 3 floats, from 0 to 1.
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();

        this.color = new Color(red, green, blue);
    }

    public GroupShape(GroupShape original) {
        super(original);
        if (Objects.nonNull(original)) {
            this.color = original.getColor();
            ShapeCopyFactory shapeCopy = new ShapeCopyFactory();
            for (Shape shape : original.getShapes()) {
                this.shapes.add(shapeCopy.copyShape(shape));
            }
        }
    }

    public GroupShape(int x, int y) {
        super(x, y);
        Random rand = new SecureRandom();
        // Java 'Color' class takes 3 floats, from 0 to 1.
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();
        this.color = new Color(red, green, blue);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public Shape getShape(int index){
        return shapes.get(index);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }
    public void addAllShapes(List<Shape> shapes) {
        this.shapes.addAll(shapes);
    }
    public void remove(Shape shape){
         shapes.remove(shape);
    }
    public Shape remove(int index){
        return shapes.remove(index);
    }
    public void setColor(Color color) {
        this.color = color;
    }
    @XmlTransient
    public Color getColor() {
        return color;
    }
    public void setCoordinate(int x, int y) {
        setX(x);
        setY(y);
    }
    public boolean isInside(int x, int y) {
        return shapes.stream().anyMatch(shape -> shape.isInside(x, y));
    }

    @Override
    public void moveTo(int x, int y) {
        int distX = x - getX();
        int distY = y - getY();
        for (Shape shape : shapes) {
            shape.moveTo(shape.getX() + distX, shape.getY() + distY);
        }
        setCoordinate(x, y);
    }
}
