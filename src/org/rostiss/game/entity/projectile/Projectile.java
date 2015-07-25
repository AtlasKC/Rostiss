package org.rostiss.game.entity.projectile;

import org.rostiss.game.entity.Entity;
import org.rostiss.game.graphics.Sprite;

/**
 * File: Projectile.java
 * Created by Atlas IND on 6/26/2015 at 9:43 PM.
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
public abstract class Projectile extends Entity {

    protected final double xOrigin, yOrigin;
    protected double angle, speed, range, damage, x, y, dx, dy;
    protected Sprite sprite;

    public Projectile(double xOrigin, double yOrigin, double angle) {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.x = xOrigin;
        this.y = yOrigin;
        this.angle = angle;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getSize() {
        return sprite.SIZE;
    }

    protected void move() {}
}