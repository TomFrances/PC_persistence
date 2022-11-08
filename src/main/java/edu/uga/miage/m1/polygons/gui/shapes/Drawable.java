package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.*;

public interface Drawable {
    boolean isInside(int x,int y);
    void moveTo(int x,int y);
    /**
     * Method to draw the shape of the extension.
     * @param g2 The graphics object used for painting.
     **/
    void draw(Graphics2D g2);
    void draw(Graphics2D g2,Color color);
}
