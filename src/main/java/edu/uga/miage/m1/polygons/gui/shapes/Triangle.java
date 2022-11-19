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

import javax.xml.bind.annotation.XmlRootElement;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

/**
 * This inner class implements the triangle <tt>SimpleShape</tt> service.
 * It simply provides a <tt>draw()</tt> that paints a triangle.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
@XmlRootElement(name = "triangle")
public class Triangle extends Shape {
    public Triangle() {
        super(0,0);
    }
    public Triangle(int x, int y) {
        super(x, y);
    }

    public Triangle(Triangle original){
        super(original);
    }

    @Override
    public void setShapeDraw() {
        int x = getX();
        int y = getY();
        int[] xCoords = { x, x-25, x + 25 };
        int[] yCoords = { y-25, y + 25, y + 25 };
        GeneralPath polygon = new GeneralPath(Path2D.WIND_EVEN_ODD, xCoords.length);
        polygon.moveTo(x, y - 25f);
        for (int i = 0; i < xCoords.length; i++) {
            polygon.lineTo(xCoords[i], yCoords[i]);
        }
        polygon.closePath();
        shapeDraw = polygon;
    }
}
