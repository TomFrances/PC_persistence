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
import java.awt.geom.Rectangle2D;
import edu.uga.miage.m1.polygons.gui.persistence.Visitor;

/**
 * This class implements the square <tt>SimpleShape</tt> extension.
 * It simply provides a <tt>draw()</tt> that paints a square.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class Square implements Shape, Drawable {

    int x;

    int y;

    public Square(int x, int y) {
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
        GradientPaint gradient = new GradientPaint(x, y, Color.BLUE, x + 50f, y, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(new Rectangle2D.Double(x - 25d, y - 25d, 50, 50));
        BasicStroke wideStroke = new BasicStroke(3.0f);
        g2.setColor(color);
        g2.setStroke(wideStroke);
        g2.draw(new Rectangle2D.Double(x - 25d, y - 25d, 50, 50));
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
    public boolean isInside(int x, int y) {
        return x <= this.x+25 && x>= this.x-25 && y <= this.y+25 && y>= this.y-25;
    }

    @Override
    public void moveTo(int x,int y){
        this.x = x;
        this.y = y;
    }
}
