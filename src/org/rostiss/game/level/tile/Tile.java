package org.rostiss.game.level.tile;

import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.graphics.Sprite;
import org.rostiss.game.level.tile.spawn_level.GrassTile;
import org.rostiss.game.level.tile.spawn_level.RockTile;
import org.rostiss.game.level.tile.spawn_level.WallTile;
import org.rostiss.game.level.tile.spawn_level.WaterTile;

/**
 * File: Tile.java
 * Created by Atlas IND on 6/22/2015 at 3:01 PM.
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

public class Tile {

    public static final int grass = 0xFF00FF00;
    public static final int brick1 = 0xFF7A7A7A;
    public static final int brick2 = 0xFFA7A7A7;
    public static final int rock = 0xFF7A7A00;
    public static final int water = 0xFF0000FF;

    public static Tile GRASS1 = new GrassTile(Sprite.GRASS1);
    public static Tile GRASS2 = new GrassTile(Sprite.GRASS2);
    public static Tile BRICK1 = new WallTile(Sprite.BRICK1);
    public static Tile BRICK2 = new WallTile(Sprite.BRICK2);
    public static Tile ROCK = new RockTile(Sprite.ROCK);
    public static Tile WATER = new WaterTile(Sprite.WATER);
    public static Tile VOID = new VoidTile(Sprite.VOID);

    public int x, y;
    public Sprite sprite;

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Renderer2D renderer) {
    }

    public boolean solid() {
        return false;
    }
}