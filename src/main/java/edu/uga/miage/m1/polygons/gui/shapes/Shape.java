package edu.uga.miage.m1.polygons.gui.shapes;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

import java.util.Objects;


/**
 * This interface defines the <tt>SimpleShape</tt> extension. This extension
 * is used to draw shapes.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
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