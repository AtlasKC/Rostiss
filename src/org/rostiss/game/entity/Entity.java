package org.rostiss.game.entity;

import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.level.Level;

import java.util.Random;

/**
 * File: Entity.java
 * Created by Atlas IND on 6/22/2015 at 5:55 PM.
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

public abstract class Entity {

    public int x, y;

    protected final Random random = new Random();
    protected Level level;

    private boolean removed = false;

    public void update() {
    }

    public void render(Renderer2D renderer) {
    }

    public void remove() {
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }
}