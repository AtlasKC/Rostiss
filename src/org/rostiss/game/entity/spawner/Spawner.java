package org.rostiss.game.entity.spawner;

import org.rostiss.game.entity.Entity;
import org.rostiss.game.level.Level;

public abstract class Spawner extends Entity {

	private Type type;

	public enum Type {
		PARTICLE, MOB
	}
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		setLevel(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}