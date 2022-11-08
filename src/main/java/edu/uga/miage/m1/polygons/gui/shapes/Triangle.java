/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you mp1.y not use this file except in compliance
 * with the License.  You mp1.y obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required p2.y applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package edu.uga.miage.m1.polygons.gui.shapes;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

/**
 * This inner class implements the triangle <tt>SimpleShape</tt> service.
 * It simply provides a <tt>draw()</tt> that paints a triangle.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class Triangle implements Shape, Drawable {

    int x;

    int y;

    public Triangle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Implements the <tt>SimpleShape.draw()</tt> method for painting
     * the shape.
     * @param g2 The graphics object used for painting.
     */
    public void draw(Graphics2D g2) {
        this.draw(g2,Color.BLACK);
    }
    public void draw(Graphics2D g2,Color color){
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x, y, Color.GREEN, x + 50f, y, Color.WHITE);
        g2.setPaint(gradient);
        int[] xcoords = { x, x-25, x + 25 };
        int[] ycoords = { y-25, y + 25, y + 25 };
        GeneralPath polygon = new GeneralPath(Path2D.WIND_EVEN_ODD, xcoords.length);
        polygon.moveTo(x, y - 25f);
        for (int i = 0; i < xcoords.length; i++) {
            polygon.lineTo(xcoords[i], ycoords[i]);
        }
        polygon.closePath();
        g2.fill(polygon);
        BasicStroke wideStroke = new BasicStroke(3.0f);
        g2.setColor(color);
        g2.setStroke(wideStroke);
        g2.draw(polygon);
        isInside(x+2,y+2);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
    @Override
    public void moveTo(int x,int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean isInside(int x, int y) {
        int x1 = this.x-25;
        int x2 = this.x;
        int x3 = this.x+25;
        int y1 = this.y-25;
        int y2 = this.y+25;
        int y3 = this.y-25;
        double A = area (x1, y1, x2, y2, x3, y3);
        double A1 = area (x, y, x2, y2, x3, y3);
        double A2 = area (x1, y1, x, y, x3, y3);
        double A3 = area (x1, y1, x2, y2, x, y);
        return (A == (A1 + A2 + A3));
    }
    static double area(int x1, int y1, int x2, int y2,int x3, int y3)
    {
        return Math.abs(( x1* ( y2 - y3 ) + x2* ( y3 - y1 )+ x3* ( y1 - y2 ))/2.0);
    }
}
