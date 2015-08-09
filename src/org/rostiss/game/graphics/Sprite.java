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

    public static Sprite ATLAS = new Sprite(16, 0, 0, SpriteSheet.PROJECTILE_ATLAS);
    public static Sprite ROCKET = new Sprite(16, 0, 1, SpriteSheet.PROJECTILE_ATLAS);
    public static Sprite GRASS1 = new Sprite(16, 0, 0, SpriteSheet.LEVEL_SPAWN);
    public static Sprite GRASS2 = new Sprite(16, 1, 0, SpriteSheet.LEVEL_SPAWN);
    public static Sprite BRICK1 = new Sprite(16, 0, 1, SpriteSheet.LEVEL_SPAWN);
    public static Sprite BRICK2 = new Sprite(16, 0, 2, SpriteSheet.LEVEL_SPAWN);
    public static Sprite ROCK = new Sprite(16, 1, 1, SpriteSheet.LEVEL_SPAWN);
    public static Sprite WATER = new Sprite(16, 2, 0, SpriteSheet.LEVEL_SPAWN);
    public static Sprite VOID = new Sprite(16, 0x7700FF);
    public static Sprite PARTICLE_DEFAULT = new Sprite(3, 0x111111);
    public static Sprite PARTICLE_FIRE = new Sprite(3, 0xFF3F00);

    public int[] pixels;
    public final int SIZE;

    private int x, y, width, height;
    protected SpriteSheet spriteSheet;

    public Sprite(int size, int color) {
        SIZE = size;
        this.width = SIZE;
        this.height = SIZE;
        pixels = new int[width * height];
        setColor(color);
    }

    public Sprite(int width, int height, int color) {
        this.SIZE = width;
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        setColor(color);
    }

    protected Sprite(SpriteSheet spriteSheet, int width, int height) {
        if(width == height) this.SIZE = width;
        else this.SIZE = -1;
        this.width = width;
        this.height = height;
        this.spriteSheet = spriteSheet;
    }

    public Sprite(int size, int x, int y, SpriteSheet spriteSheet) {
        this.SIZE = size;
        this.width = SIZE;
        this.height = SIZE;
        this.pixels = new int[width * height];
        this.x = x * size;
        this.y = y * size;
        this.spriteSheet = spriteSheet;
        load();
    }

    public Sprite(int[] pixels, int width, int height) {
        if(width == height) this.SIZE = width;
        else this.SIZE = -1;
        this.width = width;
        this.height = height;
        this.pixels = new int[pixels.length];
        System.arraycopy(pixels, 0, this.pixels, 0, pixels.length);
    }

    public static Sprite[] split(SpriteSheet sheet) {
        Sprite[] sprites = new Sprite[sheet.getWidth() * sheet.getHeight() / sheet.WIDTH * sheet.HEIGHT];
        int[] pixels = new int[sheet.WIDTH * sheet.HEIGHT];
        int index = 0;
        for(int yp = 0; yp < sheet.getHeight() / sheet.HEIGHT; yp++) {
            for(int xp = 0; xp < sheet.getWidth() / sheet.WIDTH; xp++) {
                for(int y = 0; y < sheet.HEIGHT; y++) {
                    for(int x = 0; x < sheet.WIDTH; x++) {
                        int xOffset = x + xp * sheet.WIDTH;
                        int yOffset = y + yp * sheet.HEIGHT;
                        pixels[x + y * sheet.WIDTH] = sheet.getPixels()[xOffset + yOffset * sheet.getWidth()];
                    }
                }
                sprites[index++] = new Sprite(pixels, sheet.WIDTH, sheet.HEIGHT);
            }
        }
        return sprites;
    }

    public static Sprite rotate(Sprite sprite, double angle) {
        return new Sprite(rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
    }

    private static int[] rotate(int[] pixels, int width, int height, double angle) {
        int[] result = new int[width * height];
        double dx_x = rotateX(1.0, 0.0, -angle);
        double dx_y = rotateY(1.0, 0.0, -angle);
        double dy_x = rotateX(0.0, 1.0, -angle);
        double dy_y = rotateY(0.0, 1.0, -angle);
        double x0 = rotateX(-width / 2.0, -height / 2.0, -angle) + width / 2.0;
        double y0 = rotateY(-width / 2.0, -height / 2.0, -angle) + height / 2.0;
        for (int y = 0; y < height; y++) {
            double x1 = x0, y1 = y0;
            for (int x = 0; x < width; x++) {
                int ix = (int) x1;
                int iy = (int) y1;
                int color = 0;
                if (ix < 0 || ix >= width || iy < 0 || iy >= height) color = 0xffff00ff;
                else color = pixels[ix + iy * width];
                result[x + y * width] = color;
                x1 += dx_x;
                y1 += dx_y;
            }
            x0 += dy_x;
            y0 += dy_y;
        }
        return result;
    }

    private static double rotateX(double x, double y, double angle) {
        angle -= Math.PI / 2;
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return x * cos + y * -sin;
    }

    private static double rotateY(double x, double y, double angle) {
        angle -= Math.PI / 2;
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return x * sin + y * cos;
    }

    private void setColor(int color) {
        for(int i = 0; i < width * height; i++)
            pixels[i] = color;
    }

    private void load() {
        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
                pixels[x + y * width] = spriteSheet.getPixels()[(x + this.x) + (y + this.y) * spriteSheet.WIDTH];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}