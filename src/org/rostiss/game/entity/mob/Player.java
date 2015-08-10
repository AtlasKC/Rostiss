package org.rostiss.game.entity.mob;

import org.rostiss.game.Rostiss;
import org.rostiss.game.entity.projectile.AtlasProjectile;
import org.rostiss.game.graphics.AnimatedSprite;
import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.graphics.Sprite;
import org.rostiss.game.graphics.SpriteSheet;
import org.rostiss.game.graphics.ui.UILabel;
import org.rostiss.game.graphics.ui.UIManager;
import org.rostiss.game.graphics.ui.UIPanel;
import org.rostiss.game.input.Keyboard;
import org.rostiss.game.input.Mouse;
import org.rostiss.game.util.Vector2i;

import java.awt.*;

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

    private static final double SPEED = 1;

    private UIManager uiManager;
    private String name;
    private Keyboard keyboard;
    private Sprite sprite;
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.PLAYER_UP, 32, 32, 3);
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.PLAYER_DOWN, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.PLAYER_LEFT, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.PLAYER_RIGHT, 32, 32, 3);
    private AnimatedSprite animatedSprite = null;
    private int animation, rate = AtlasProjectile.FIRE_RATE;

    public Player(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Player(String name, Keyboard keyboard, int x, int y) {
        this.name = name;
        this.keyboard = keyboard;
        this.x = x;
        this.y = y;
        this.animatedSprite = down;
        this.sprite = animatedSprite.getSprite();
        this.uiManager = Rostiss.getUIManager();
        UIPanel panel = new UIPanel(new Vector2i(675, 0), new Vector2i(225, 506));
        panel.addComponent(((UILabel) new UILabel(new Vector2i(50, 200), name).setColor(0xBBBBBB)).setFont(new Font("Verdana", Font.BOLD, 21)).setShadow(true));
        uiManager.addPanel(panel);
    }

    public void update() {
        if (walking) animatedSprite.update();
        else animatedSprite.setFrame(0);
        if (rate > 0) rate--;
        double dx = 0, dy = 0;
        animation++;
        if (animation >= 2147483646)
            animation = 0;
        if (keyboard.up) {
            dy -= SPEED;
            animatedSprite = up;
        }
        if (keyboard.down) {
            dy += SPEED;
            animatedSprite = down;
        }
        if (keyboard.left) {
            dx -= SPEED;
            animatedSprite = left;
        }
        if (keyboard.right) {
            dx += SPEED;
            animatedSprite = right;
        }
        if (dx != 0 || dy != 0) {
            move(dx, dy);
            walking = true;
        } else
            walking = false;
        updateShooting();
    }

    private void updateShooting() {
        if (Mouse.getButton() == 1 && rate <= 0) {
            double dx = Mouse.getX() - Rostiss.getInstance().getWidth() / 2;
            double dy = Mouse.getY() - Rostiss.getInstance().getHeight() / 2;
            double dir = atan2(dy - 24, dx - 24);
            shoot(x, y, dir);
            rate = AtlasProjectile.FIRE_RATE;
        }
    }

    public void render(Renderer2D renderer) {
        sprite = animatedSprite.getSprite();
        renderer.renderMob((int) (x - 16), (int) (y - 16), sprite, false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}