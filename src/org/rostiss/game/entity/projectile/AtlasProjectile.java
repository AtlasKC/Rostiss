package org.rostiss.game.entity.projectile;

import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.graphics.Sprite;

import static java.lang.Math.*;

/**
 * File: AtlasProjectile.java
 * Created by Atlas IND on 6/26/2015 at 9:50 PM.
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

public class AtlasProjectile extends Projectile {

    public static final int FIRE_RATE = 12;

    public AtlasProjectile(int xOrigin, int yOrigin, double angle) {
        super(xOrigin, yOrigin, angle);
        sprite = Sprite.ATLAS;
        damage = 20;
        range = 200;
        speed = 4;
        dx = speed * cos(this.angle);
        dy = speed * sin(this.angle);
    }

    protected void move() {
        x += dx;
        y += dy;
        if (sqrt(abs((x - xOrigin) * (x - xOrigin) + (y - yOrigin) * (y - yOrigin))) > range)
            remove();
    }

    public void update() {
        move();
    }

    public void render(Renderer2D renderer) {
        renderer.renderProjectile((int) x, (int) y, this);
    }
}