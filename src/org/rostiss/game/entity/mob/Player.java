package org.rostiss.game.entity.mob;

import org.rostiss.game.graphics.Sprite;
import org.rostiss.game.input.Keyboard;

/**
 * File: Player.java
 * Created by Atlas IND on 6/22/2015 at 7:04 PM.
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

public class Player extends Mob {

    private Keyboard keyboard;

    public Player(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Player(Keyboard keyboard, int x, int y) {
        this.keyboard = keyboard;
        this.x = x;
        this.y = y;
        this.sprite = Sprite.PLAYER_F1;
    }

    public void update() {
        int dx = 0, dy = 0;
        if (keyboard.up) dy--;
        if (keyboard.down) dy++;
        if (keyboard.left) dx--;
        if (keyboard.right) dx++;
        if(dx != 0 || dy != 0) move(dx, dy);
    }

    public void render() {

    }
}