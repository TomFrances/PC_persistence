package edu.uga.miage.m1.polygons.gui.shapes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;


/**
 * This interface defines the <tt>SimpleShape</tt> extension. This extension
 * is used to draw shapes.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
@XmlSeeAlso({Circle.class, Square.class, Triangle.class, GroupShape.class})
@XmlType(propOrder = {"x", "y"})
@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Square.class, name = "square"),
        @JsonSubTypes.Type(value = Triangle.class, name = "triangle"),
        @JsonSubTypes.Type(value = Circle.class, name = "circle"),
        @JsonSubTypes.Type(value = GroupShape.class, name = "groupShape")
})
public abstract class Shape implements IsInside {

    private int x;
    private int y;

    protected Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected Shape(Shape original) {
        if (Objects.nonNull(original)) {
            this.x = original.getX();
            this.y = original.getY();
        }
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveTo(int x, int y) {
        setX(x);
        setY(y);
    }

}