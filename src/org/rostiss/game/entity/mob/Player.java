package org.rostiss.game.entity.mob;

import org.rostiss.game.Rostiss;
import org.rostiss.game.entity.projectile.AtlasProjectile;
import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.graphics.Sprite;
import org.rostiss.game.input.Keyboard;
import org.rostiss.game.input.Mouse;

import static java.lang.Math.atan2;

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
    private Sprite sprite;
    private int animation, rate = AtlasProjectile.FIRE_RATE;
    private boolean walking;

    public Player(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Player(Keyboard keyboard, int x, int y) {
        this.keyboard = keyboard;
        this.x = x;
        this.y = y;
        this.sprite = Sprite.PLAYER_FN;
    }

    public void update() {
        if(rate > 0) rate--;
        int dx = 0, dy = 0;
        animation++;
        if (animation >= 2147483646)
            animation = 0;
        if (keyboard.up) dy--;
        if (keyboard.down) dy++;
        if (keyboard.left) dx--;
        if (keyboard.right) dx++;
        if (dx != 0 || dy != 0) {
            move(dx, dy);
            walking = true;
        } else
            walking = false;
        updateProjectiles();
        updateShooting();
    }

    private void updateProjectiles() {
        for(int i = 0; i < level.getProjectiles().size(); i++) {
            if(level.getProjectile(i).isRemoved())
                level.remove(level.getProjectile(i));
        }
    }

    private void updateShooting() {
        if (Mouse.getButton() == 1 && rate <= 0) {
            double dx = Mouse.getX() - Rostiss.getInstance().getWidth() / 2;
            double dy = Mouse.getY() - Rostiss.getInstance().getHeight() / 2;
            double dir = atan2(dy, dx);
            shoot(x - 8, y - 8, dir);
            rate = AtlasProjectile.FIRE_RATE;
        }
    }

    public void render(Renderer2D renderer) {
        if (direction == 0) {
            sprite = Sprite.PLAYER_FN;
            if (walking) {
                if (animation % 20 >= 10)
                    sprite = Sprite.PLAYER_FL;
                else
                    sprite = Sprite.PLAYER_FR;
            }
        }
        if (direction == 1) {
            sprite = Sprite.PLAYER_SN;
            if (walking) {
                if (animation % 20 >= 10)
                    sprite = Sprite.PLAYER_SL;
                else
                    sprite = Sprite.PLAYER_SR;
            }
        }
        if (direction == 2) {
            sprite = Sprite.PLAYER_BN;
            if (walking) {
                if (animation % 20 >= 10)
                    sprite = Sprite.PLAYER_BL;
                else
                    sprite = Sprite.PLAYER_BR;
            }
        }
        if (direction == 3) {
            sprite = Sprite.PLAYER_SN;
            if (walking) {
                if (animation % 20 >= 10)
                    sprite = Sprite.PLAYER_SL;
                else
                    sprite = Sprite.PLAYER_SR;
            }
            renderer.renderPlayer(x - 8, y - 8, sprite, true);
        } else
            renderer.renderPlayer(x - 8, y - 8, sprite, false);
    }
}