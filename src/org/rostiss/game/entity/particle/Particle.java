package org.rostiss.game.entity.particle;

import org.rostiss.game.entity.Entity;
import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * File: Particle.java
 * Created by Atlas IND on 6/29/2015 at 5:07 PM.
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

public class Particle extends Entity {

    protected double dx, dy, xx, yy;
    private List<Particle> particles = new ArrayList<>();
    private Sprite sprite;
    private int time;

    public Particle(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
        sprite = Sprite.PARTICLE;
        xx = x;
        yy = y;
        this.dx = random.nextGaussian();
        this.dy = random.nextGaussian();
    }

    public Particle(int x, int y, int time, int amount) {
        this(x, y, time);
        for (int i = 0; i < amount - 1; i++) {
            particles.add(new Particle(x, y, time));
        }
        particles.add(this);
    }

    public void update() {
        this.xx += dx;
        this.yy += dy;
        x = (int)xx;
        y = (int)yy;
    }

    public List<Particle> getParticles() { return particles; }

    public void render(Renderer2D renderer) {
        renderer.renderSprite((int)xx, (int)yy, sprite, true);
    }
}