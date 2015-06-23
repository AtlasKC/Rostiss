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

    public static Sprite GRASS = new Sprite(16, 0, 0, SpriteSheet.SPRITESHEET);
    public static Sprite VOID = new Sprite(16, 0x7700FF);

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
        for(int y = 0; y < SIZE; y++) {
            for(int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = spriteSheet.pixels[(x + this.x) + (y + this.y) * spriteSheet.SIZE];
            }
        }
    }
}