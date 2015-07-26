package org.rostiss.game.util;

/**
 * File: Vector2i.java
 * Created by Atlas IND on 7/24/2015 at 5:49 PM.
 * [2014] - [2015] Rostiss Development
 * All rights reserved.
 * NOTICE:  All information contained herein is, and remains
 * the property of Rostiss Development and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Rostiss Development
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Rostiss Development.
 */
public class Vector2i {

    private int x, y;

    public Vector2i() {
        this(0, 0);
    }

    public Vector2i(int value) {
        this(value, value);
    }

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i(Vector2i vector) {
        this.x = vector.getX();
        this.y = vector.getY();
    }

    public Vector2i add(int value) {
        this.x += value;
        this.y += value;
        return this;
    }

    public Vector2i add(Vector2i vector) {
        this.x += vector.getX();
        this.y += vector.getY();
        return this;
    }

    public Vector2i sub(int value) {
        this.x -= value;
        this.y -= value;
        return this;
    }

    public Vector2i sub(Vector2i vector) {
        this.x -= vector.getX();
        this.y -= vector.getY();
        return this;
    }

    public static double getDistance(Vector2i vector1, Vector2i vector2) {
        double dx = vector1.getX() - vector2.getX();
        double dy = vector1.getY() - vector2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public boolean equals(Object object) {
        if(!(object instanceof Vector2i)) return false;
        Vector2i vector = (Vector2i)object;
        if(this.x == vector.getX() && this.y == vector.getY()) return true;
        return false;
    }

    public int getX() {
        return x;
    }

    public Vector2i setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Vector2i setY(int y) {
        this.y = y;
        return this;
    }
}