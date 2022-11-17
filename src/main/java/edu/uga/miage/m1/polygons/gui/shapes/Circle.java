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

@Value
@XmlRootElement(name = "circle")
public class Circle extends Shape {

    public Circle() {
        super(0,0);
    }

    public Circle(int x, int y) {
        super(x, y);
    }

    public Circle(Circle original) {
        super(original);
    }

    public boolean isInside(int x, int y) {
        return Math.sqrt(Math.pow((double) x - this.getX(), 2) + Math.pow((double) y - this.getY(), 2)) <= 25;
    }

}
