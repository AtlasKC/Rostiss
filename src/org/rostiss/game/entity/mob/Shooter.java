package org.rostiss.game.entity.mob;

import org.rostiss.game.entity.Entity;
import org.rostiss.game.entity.spawner.ParticleSpawner;
import org.rostiss.game.graphics.AnimatedSprite;
import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.graphics.SpriteSheet;
import org.rostiss.game.util.Debug;
import org.rostiss.game.util.Vector2i;

import java.util.List;

/**
 * File: Shooter.java
 * Created by Atlas IND on 7/25/2015 at 10:57 PM.
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

public class Shooter extends Mob {

    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.TEST_UP, 32, 32, 3);
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.TEST_DOWN, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.TEST_LEFT, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.TEST_RIGHT, 32, 32, 3);
    private AnimatedSprite animatedSprite = down;
    private Entity playerToShoot = null;
    private int time, dx, dy;

    public Shooter(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = SpriteSheet.TEST_DOWN.getSprites()[0];
    }

    public void update() {
        time++;
        if (time % (random.nextInt(60) + 30) == 0) {
            dx = random.nextInt(3) - 1;
            dy = random.nextInt(3) - 1;
            if (random.nextInt(4) == 0) {
                dx = dy = 0;
            }
        }
        if (walking) animatedSprite.update();
        else animatedSprite.setFrame(0);
        if (dy < 0) {
            direction = Direction.UP;
            animatedSprite = up;
        } else if (dy > 0) {
            direction = Direction.DOWN;
            animatedSprite = down;
        }
        if (dx < 0) {
            direction = Direction.LEFT;
            animatedSprite = left;
        } else if (dx > 0) {
            direction = Direction.RIGHT;
            animatedSprite = right;
        }
        if (dx != 0 || dy != 0) {
            move(dx, dy);
            walking = true;
        } else
            walking = false;
        if (time % 15 == 0)
            shootRandom();
    }

    public void render(Renderer2D renderer) {
        Debug.drawRect(renderer, new Vector2i(16 * 19, 16 * 55), new Vector2i(60, 40), 0xFF0000, true);
        renderer.renderMob((int) (x - 16), (int) (y - 16), this);
    }

    private void shootRandom() {
        if(time % (30 + random.nextInt(91)) == 0) {
            List<Entity> entities = level.getEntitiesInRange(this, 500);
            entities.add(level.getClientPlayer());
            playerToShoot = entities.get(random.nextInt(entities.size()));
        }
        if (playerToShoot != null)
            shoot(x, y, Math.atan2(playerToShoot.getY() - y, playerToShoot.getX() - x));
    }

    private void shootClosest() {
        List<Entity> entities = level.getEntitiesInRange(this, 500);
        entities.add(level.getClientPlayer());
        double closestDistance = 0;
        playerToShoot = null;
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity instanceof ParticleSpawner)
                continue;
            double distance = Vector2i.getDistance(new Vector2i((int) entity.getX(), (int) entity.getY()), new Vector2i((int) x, (int) y));
            if (entity.equals(entities.get(0)) || distance < closestDistance) {
                closestDistance = distance;
                playerToShoot = entity;
            }
        }
        if (playerToShoot != null)
            shoot(x, y, Math.atan2(playerToShoot.getY() - y, playerToShoot.getX() - x));
    }
}