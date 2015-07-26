package org.rostiss.game.entity.mob;

import org.rostiss.game.graphics.AnimatedSprite;
import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.graphics.SpriteSheet;
import org.rostiss.game.level.Node;
import org.rostiss.game.util.Vector2i;

import java.util.List;

/**
 * File: AStar.java
 * Created by Atlas IND on 7/25/2015 at 7:49 PM.
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

public class AStar extends Mob {

    private static final double SPEED = 0.75;

    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.TEST_UP, 32, 32, 3);
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.TEST_DOWN, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.TEST_LEFT, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.TEST_RIGHT, 32, 32, 3);
    private AnimatedSprite animatedSprite = down;
    private List<Node> path = null;
    private int time;
    private double dx, dy;

    public AStar(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = animatedSprite.getSprite();
    }

    public void update() {
        time++;
        move();
        if (dy < 0) {
            animatedSprite = up;
            direction = Direction.UP;
        } else if (dy > 0) {
            animatedSprite = down;
            direction = Direction.DOWN;
        }
        if (dx < 0) {
            animatedSprite = left;
            direction = Direction.LEFT;
        } else if (dx > 0) {
            animatedSprite = right;
            direction = Direction.RIGHT;
        }
        if (walking) animatedSprite.update();
        else animatedSprite.setFrame(0);
    }

    public void render(Renderer2D renderer) {
        sprite = animatedSprite.getSprite();
        renderer.renderMob((int) (x - 16), (int) (y - 16), this);
    }

    private void move() {
        dx = 0;
        dy = 0;
        int px = (int)level.getClientPlayer().getX();
        int py = (int)level.getClientPlayer().getY();
        Vector2i start = new Vector2i((int)getX() >> 4, (int)getY() >> 4);
        Vector2i end = new Vector2i(px >> 4, py >> 4);
        if(time % 3 == 0) path = level.findPath(start, end);
        if (path != null) {
            if(path.size() > 0) {
                Vector2i vector = path.get(path.size() - 1).tile;
                if((int)x < vector.getX() << 4) dx += SPEED;
                if((int)x > vector.getX() << 4) dx -= SPEED;
                if((int)y < vector.getY() << 4) dy += SPEED;
                if((int)y > vector.getY() << 4) dy -= SPEED;
            }
        }
        if (dx != 0 || dy != 0) {
            move(dx, dy);
            walking = true;
        } else
            walking = false;
    }
}