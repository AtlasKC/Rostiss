package org.rostiss.game.level;

import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.level.tile.Tile;

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

    public void update() {
    }

    public void render(int xPos, int yPos, Renderer2D renderer) {
        renderer.setOffset(xPos, yPos);
        int x0 = xPos >> 4;
        int x1 = (xPos + renderer.width + 16) >> 4;
        int y0 = yPos >> 4;
        int y1 = (yPos + renderer.height + 16) >> 4;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, renderer);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) return Tile.VOID;
        if (tiles[x + y * width] == Tile.grass) return Tile.GRASS2;
        if (tiles[x + y * width] == Tile.water) return Tile.WATER;
        if (tiles[x + y * width] == Tile.rock) return Tile.ROCK;
        if (tiles[x + y * width] == Tile.brick1) return Tile.BRICK1;
        if (tiles[x + y * width] == Tile.brick2) return Tile.BRICK2;
        return Tile.VOID;
    }

    private void time() {
    }

    protected void generateLevel() {
    }

    protected void loadLevel(String file) {
    }
}