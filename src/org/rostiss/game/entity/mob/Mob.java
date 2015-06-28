package org.rostiss.game.entity.mob;

import org.rostiss.game.entity.Entity;
import org.rostiss.game.entity.projectile.AtlasProjectile;
import org.rostiss.game.entity.projectile.Projectile;
import org.rostiss.game.graphics.Sprite;

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

    protected Sprite sprite;
    protected int direction = 0;
    protected boolean moving = false;

    public void update() {
    }

    public void move(int dx, int dy) {
        if (dx != 0 && dy != 0) {
            move(dx, 0);
            move(0, dy);
            return;
        }
        if (dx > 0) direction = 1;
        if (dx < 0) direction = 3;
        if (dy > 0) direction = 2;
        if (dy < 0) direction = 0;
        if (!collision(dx, dy)) {
            x += dx;
            y += dy;
        }
    }

    public void render() {
    }

    private boolean collision(int dx, int dy) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((x + dx) + c % 2 * 14) / 16;
            int yt = ((y + dy) + c / 2 * 12 + 8) / 16;
            if (level.getTile(xt, yt).solid())
                solid = true;
        }
        return solid;
    }

    protected void shoot(int x, int y, double direction) {
        Projectile projectile = new AtlasProjectile(x, y, direction);
        level.add(projectile);
    }
}