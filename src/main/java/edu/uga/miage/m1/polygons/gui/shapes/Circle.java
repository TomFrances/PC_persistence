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
import java.awt.geom.Ellipse2D;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

public class Circle implements Shape {

    int x;

    int y;

    public Circle(int x, int y) {
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
    public void draw(Graphics2D g2,Color color) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradient = new GradientPaint(x - 25f, y - 25f, Color.RED, x + 25f, y - 25f, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(new Ellipse2D.Double(x - 25f, y - 25f, 50, 50));
        BasicStroke wideStroke = new BasicStroke(3.0f);
        g2.setColor(color);
        g2.setStroke(wideStroke);
        g2.draw(new Ellipse2D.Double(x - 25f, y - 25f, 50, 50));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public boolean isInside(int x,int y){
        return Math.sqrt(Math.pow(x-this.x,2)+Math.pow(y-this.y,2))<25;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void moveTo(int x,int y){
        this.x = x;
        this.y = y;
    }
}
