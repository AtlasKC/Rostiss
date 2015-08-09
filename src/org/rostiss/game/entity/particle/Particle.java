package org.rostiss.game.entity.particle;

import org.rostiss.game.entity.Entity;
import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.graphics.Sprite;

/**
 * File: Particle.java Created by Atlas IND on 6/29/2015 at 5:07 PM. [2014] -
 * [2015] Rostiss Development All rights reserved. NOTICE: All information
 * contained herein is, and remains the property of Rostiss Development and its
 * suppliers, if any. The intellectual and technical concepts contained herein
 * are proprietary to Rostiss Development and its suppliers and may be covered
 * by U.S. and Foreign Patents, patents in process, and are protected by trade
 * secret or copyright law. Dissemination of this information or reproduction of
 * this material is strictly forbidden unless prior written permission is
 * obtained from Rostiss Development.
 */

public class Particle extends Entity {

	protected double dx, dy, dz, xx, yy, zz;
	private Sprite sprite;
	private int life, time;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.zz = random.nextFloat() + 2.0;
		this.life = life + (random.nextInt(20) - 10);
		this.sprite = Sprite.PARTICLE_FIRE;
		this.dx = random.nextGaussian();
		this.dy = random.nextGaussian();
	}

	public void update() {
		time++;
		if (time >= Integer.MAX_VALUE)
			time = 0;
		if (time > life)
			remove();
		dz -= 0.1;
		if (zz < 0) {
			zz = 0;
			dx *= 0.5;
			dy *= 0.5;
			dz *= -0.5;
		}
		move(xx + dx, (yy + dy) + (zz + dz));
	}

	private void move(double x, double y) {
		if (collision(x, y)) {
			this.dx *= -0.5;
			this.dy *= -0.5;
			this.dz *= -0.5;
		}
		this.xx += dx;
		this.yy += dy;
		this.zz += dz;
	}

	private boolean collision(double x, double y) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 16) / 16;
			double yt = (y - c / 2 * 16) / 16;
			if (c % 2 == 0)
				xt = (int) Math.floor(xt);
			else
				xt = (int) Math.ceil(xt);
			if (c / 2 == 0)
				yt = (int) Math.floor(yt);
			else
				yt = (int) Math.ceil(yt);
			if (level.getTile((int) xt, (int) yt).solid())
				solid = true;
		}
		return solid;
	}

	public void render(Renderer2D renderer) {
		renderer.renderSprite((int) xx - 1, (int) yy - (int) zz - 1, sprite, true);
	}
}