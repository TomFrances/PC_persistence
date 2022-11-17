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

import lombok.Value;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This inner class implements the triangle <tt>SimpleShape</tt> service.
 * It simply provides a <tt>draw()</tt> that paints a triangle.
 *
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
@XmlRootElement(name = "triangle")
@Value
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
    public boolean isInside(int x, int y) {
        int x1 = this.getX()-25;
        int x2 = this.getX();
        int x3 = this.getX()+25;
        int y1 = this.getY()-25;
        int y2 = this.getY()+25;
        int y3 = this.getY()-25;
        double a = area (x1, y1, x2, y2, x3, y3);
        double a1 = area (x, y, x2, y2, x3, y3);
        double a2 = area (x1, y1, x, y, x3, y3);
        double a3 = area (x1, y1, x2, y2, x, y);
        return (a == (a1 + a2 + a3));
    }
    private double area(int x1, int y1, int x2, int y2,int x3, int y3)
    {
        return Math.abs(( x1* ( y2 - y3 ) + x2* ( y3 - y1 )+ x3* ( y1 - y2 ))/2.0);
    }
}
