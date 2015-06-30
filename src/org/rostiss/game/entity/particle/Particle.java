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

    private List<Particle> particles = new ArrayList<>();
    private Sprite sprite;
    private int time;

    public Particle(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
        sprite = Sprite.PARTICLE;
        particles.add(this);
    }

    public Particle(int x, int y, int time, int amount) {
        this(x, y, time);
        for (int i = 0; i < amount - 1; i++)
            particles.add(new Particle(x, y, time));
    }

    public void update() {}

    public void render(Renderer2D renderer) {
        particles.forEach(particle -> renderer.renderSprite(x, y, sprite, false));
    }
}