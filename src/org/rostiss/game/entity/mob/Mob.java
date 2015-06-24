package org.rostiss.game.entity.mob;

import org.rostiss.game.entity.Entity;
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
        if(dx > 0) direction = 1;
        if(dx < 0) direction = 3;
        if(dy > 0) direction = 2;
        if(dy < 0) direction = 0;
        if(!collision()) {
            x += dx;
            y += dy;
        }
    }

    public void render() {
    }

    private boolean collision() {
        return false;
    }
}