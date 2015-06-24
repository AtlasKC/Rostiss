package org.rostiss.game.graphics;

/**
 * File: Sprite.java
 * Created by Atlas IND on 6/21/2015 at 9:55 PM.
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

public class Sprite {

    public static Sprite GRASS1 = new Sprite(16, 0, 0, SpriteSheet.LEVEL_SPAWN);
    public static Sprite GRASS2 = new Sprite(16, 1, 0, SpriteSheet.LEVEL_SPAWN);
    public static Sprite BRICK1 = new Sprite(16, 0, 1, SpriteSheet.LEVEL_SPAWN);
    public static Sprite BRICK2 = new Sprite(16, 0, 2, SpriteSheet.LEVEL_SPAWN);
    public static Sprite ROCK = new Sprite(16, 1, 1, SpriteSheet.LEVEL_SPAWN);
    public static Sprite WATER = new Sprite(16, 2, 0, SpriteSheet.LEVEL_SPAWN);
    public static Sprite VOID = new Sprite(16, 0x7700FF);
    public static Sprite PLAYER_FN = new Sprite(32, 1, 7, SpriteSheet.SPRITESHEET);
    public static Sprite PLAYER_FL = new Sprite(32, 1, 6, SpriteSheet.SPRITESHEET);
    public static Sprite PLAYER_FR = new Sprite(32, 1, 5, SpriteSheet.SPRITESHEET);
    public static Sprite PLAYER_BN = new Sprite(32, 0, 7, SpriteSheet.SPRITESHEET);
    public static Sprite PLAYER_BL = new Sprite(32, 0, 6, SpriteSheet.SPRITESHEET);
    public static Sprite PLAYER_BR = new Sprite(32, 0, 5, SpriteSheet.SPRITESHEET);
    public static Sprite PLAYER_SN = new Sprite(32, 2, 7, SpriteSheet.SPRITESHEET);
    public static Sprite PLAYER_SL = new Sprite(32, 2, 6, SpriteSheet.SPRITESHEET);
    public static Sprite PLAYER_SR = new Sprite(32, 2, 5, SpriteSheet.SPRITESHEET);

    public int[] pixels;
    public final int SIZE;

    private int x, y;
    private SpriteSheet spriteSheet;

    public Sprite(int size, int color) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    public Sprite(int size, int x, int y, SpriteSheet spriteSheet) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.spriteSheet = spriteSheet;
        load();
    }

    private void setColor(int color) {
        for(int i = 0; i < pixels.length; i++)
            pixels[i] = color;
    }

    private void load() {
        for(int y = 0; y < SIZE; y++)
            for(int x = 0; x < SIZE; x++)
                pixels[x + y * SIZE] = spriteSheet.pixels[(x + this.x) + (y + this.y) * spriteSheet.SIZE];
    }
}