package org.rostiss.game.level;

import org.rostiss.game.entity.Entity;
import org.rostiss.game.entity.mob.Player;
import org.rostiss.game.entity.particle.Particle;
import org.rostiss.game.entity.projectile.Projectile;
import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * File: Level.java
 * Created by Atlas IND on 6/22/2015 at 2:36 PM.
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

public class Level {

    public static Level spawn = new SpawnLevel("/levels/spawn_level.png");

    protected int[] tiles;
    protected int width, height;

    private List<Projectile> projectiles = new ArrayList<>();
    private List<Particle> particles = new ArrayList<>();
    private List<Entity> entities = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

    public Level(String file) {
        loadLevel(file);
        generateLevel();
    }

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public void add(Entity entity) {
        entity.setLevel(this);
        if (entity instanceof Projectile)
            projectiles.add((Projectile) entity);
        else if (entity instanceof Particle)
            particles.add((Particle) entity);
        else if (entity instanceof Player)
            players.add((Player) entity);
        else
            entities.add(entity);
    }

    private void remove() {
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved())
                projectiles.remove(projectiles.get(i));
        }
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isRemoved())
                particles.remove(particles.get(i));
        }
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved())
                entities.remove(entities.get(i));
        }
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isRemoved())
                players.remove(players.get(i));
        }
    }

    public void update() {
        projectiles.forEach(Projectile::update);
        particles.forEach(Particle::update);
        entities.forEach(Entity::update);
        players.forEach(Player::update);
        remove();
    }

    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((x + size) - c % 2 * size + xOffset) >> 4;
            int yt = ((y + size) - c / 2 * size + yOffset) >> 4;
            if (getTile(xt, yt).solid()) solid = true;
        }
        return solid;
    }

    public void render(int xPos, int yPos, Renderer2D renderer) {
        renderer.setOffset(xPos, yPos);
        int x0 = xPos >> 4;
        int x1 = (xPos + renderer.width + 16) >> 4;
        int y0 = yPos >> 4;
        int y1 = (yPos + renderer.height + 16) >> 4;
        for (int y = y0; y < y1; y++)
            for (int x = x0; x < x1; x++)
                getTile(x, y).render(x, y, renderer);
        players.forEach(player -> player.render(renderer));
        entities.forEach(entity -> entity.render(renderer));
        projectiles.forEach(projectile -> projectile.render(renderer));
        particles.forEach(particle -> particle.render(renderer));
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.VOID;
        if (tiles[x + y * width] == Tile.grass) return Tile.GRASS2;
        if (tiles[x + y * width] == Tile.water) return Tile.WATER;
        if (tiles[x + y * width] == Tile.rock) return Tile.ROCK;
        if (tiles[x + y * width] == Tile.brick1) return Tile.BRICK1;
        if (tiles[x + y * width] == Tile.brick2) return Tile.BRICK2;
        return Tile.VOID;
    }

    public List<Entity> getEntitiesInRange(Entity entity, int range) {
        List<Entity> result = new ArrayList<>();
        for (Entity e : entities) {
            double x = e.getX();
            double y = e.getY();
            double dx = Math.abs(x - entity.getX());
            double dy = Math.abs(y - entity.getY());
            double distance = Math.sqrt(dx * dx + dy * dy);
            if(distance <= range)
                result.add(e);
        }
        return result;
    }

    public List<Player> getPlayersInRange(Entity entity, int range) {
        List<Player> result = new ArrayList<>();
        for (Player player : players) {
            double x = player.getX();
            double y = player.getY();
            double dx = Math.abs(x - entity.getX());
            double dy = Math.abs(y - entity.getY());
            double distance = Math.sqrt(dx * dx + dy * dy);
            if(distance <= range)
                result.add(player);
        }
        return result;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerAt(int index) {
        return players.get(index);
    }

    public Player getClientPlayer() {
        return players.get(0);
    }

    private void time() {
    }

    protected void generateLevel() {
    }

    protected void loadLevel(String file) {
    }
}