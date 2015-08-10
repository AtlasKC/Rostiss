package org.rostiss.game.entity.mob;

import org.rostiss.game.entity.Entity;
import org.rostiss.game.entity.projectile.AtlasProjectile;
import org.rostiss.game.entity.projectile.Projectile;
import org.rostiss.game.graphics.Renderer2D;

/**
 * File: Mob.java
 * Created by Atlas IND on 6/22/2015 at 6:06 PM.
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

public abstract class Mob extends Entity {

    protected enum Direction { UP, DOWN, LEFT, RIGHT }

    protected Direction direction;
    protected int health;
    protected boolean walking = false;

    public abstract void update();

    public abstract void render(Renderer2D renderer);

    public void move(double dx, double dy) {
        if (dx != 0 && dy != 0) {
            move(dx, 0);
            move(0, dy);
            return;
        }
        if (dx > 0) direction = Direction.RIGHT;
        if (dx < 0) direction = Direction.LEFT;
        if (dy > 0) direction = Direction.DOWN;
        if (dy < 0) direction = Direction.UP;
        while (dx != 0) {
            if (Math.abs(dx) > 1) {
                if (!collision(sign(dx), dy))
                    this.x += sign(dx);
                dx -= sign(dx);
            } else {
                if (!collision(sign(dx), dy))
                    this.x += dx;
                dx = 0;
            }
        }
        while (dy != 0) {
            if (Math.abs(dy) > 1) {
                if (!collision(dx, sign(dy)))
                    this.y += sign(dy);
                dy -= sign(dy);
            } else {
                if (!collision(dx, sign(dy)))
                    this.y += dy;
                dy = 0;
            }
        }
    }

    private boolean collision(double dx, double dy) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            double xt = ((x + dx) - c % 2 * 15) / 16;
            double yt = ((y + dy) - c / 2 * 15) / 16;
            int ix = (int) Math.ceil(xt);
            int iy = (int) Math.ceil(yt);
            if (c % 2 == 0) ix = (int) Math.floor(xt);
            if (c / 2 == 0) iy = (int) Math.floor(yt);
            if (level.getTile(ix, iy).solid()) solid = true;
        }
        return solid;
    }

    private int sign(double value) {
        if (value < 0) return -1;
        return 1;
    }

    protected void shoot(double x, double y, double direction) {
        Projectile projectile = new AtlasProjectile(x, y, direction);
        level.add(projectile);
    }
}