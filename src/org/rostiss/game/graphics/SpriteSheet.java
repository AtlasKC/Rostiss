package org.rostiss.game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * File: SpriteSheet.java
 * Created by Atlas IND on 6/21/2015 at 9:13 PM.
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

public class SpriteSheet {

    public static SpriteSheet SPRITESHEET = new SpriteSheet("/sprites/spritesheet.png", 256);
    public static SpriteSheet LEVEL_SPAWN = new SpriteSheet("/sprites/spawn_level.png", 48);
    public static SpriteSheet PROJECTILE_ATLAS = new SpriteSheet("/sprites/projectiles/atlas.png", 48);
    public static SpriteSheet PLAYER = new SpriteSheet("/sprites/player.png", 128, 96);
    public static SpriteSheet TEST = new SpriteSheet("/sprites/test_mob.png", 128, 96);
    public static SpriteSheet PLAYER_UP = new SpriteSheet(PLAYER, 1, 0, 1, 3, 32);
    public static SpriteSheet PLAYER_DOWN = new SpriteSheet(PLAYER, 0, 0, 1, 3, 32);
    public static SpriteSheet PLAYER_LEFT = new SpriteSheet(PLAYER, 3, 0, 1, 3, 32);
    public static SpriteSheet PLAYER_RIGHT = new SpriteSheet(PLAYER, 2, 0, 1, 3, 32);
    public static SpriteSheet TEST_UP = new SpriteSheet(TEST, 1, 0, 1, 3, 32);
    public static SpriteSheet TEST_DOWN = new SpriteSheet(TEST, 0, 0, 1, 3, 32);
    public static SpriteSheet TEST_LEFT = new SpriteSheet(TEST, 3, 0, 1, 3, 32);
    public static SpriteSheet TEST_RIGHT = new SpriteSheet(TEST, 2, 0, 1, 3, 32);

    public final int SIZE, WIDTH, HEIGHT;

    private Sprite[] sprites;
    private String file;
    private int width, height;
    private int[] pixels;

    public SpriteSheet(String file, int size) {
        this.file = file;
        this.SIZE = size;
        this.WIDTH = size;
        this.HEIGHT = size;
        load();
    }

    public SpriteSheet(String file, int width, int height) {
        this.file = file;
        this.SIZE = -1;
        this.WIDTH = width;
        this.HEIGHT = height;
        load();
    }

    public SpriteSheet(SpriteSheet spriteSheet, int x, int y, int width, int height, int size) {
        int dx = x * size;
        int dy = y * size;
        int w = width * size;
        int h = height * size;
        if (width == height)
            this.SIZE = width;
        else
            this.SIZE = -1;
        this.WIDTH = w;
        this.HEIGHT = h;
        pixels = new int[WIDTH * HEIGHT];
        for (int y0 = 0; y0 < h; y0++) {
            int yPos = dy + y0;
            for (int x0 = 0; x0 < w; x0++) {
                int xPos = dx + x0;
                pixels[x0 + y0 * w] = spriteSheet.pixels[xPos + yPos * spriteSheet.WIDTH];
            }
        }
        int counter = 0;
        sprites = new Sprite[width * height];
        for (int ya = 0; ya < height; ya++) {
            for (int xa = 0; xa < width; xa++) {
                int[] spritePixels = new int[size * size];
                for (int y0 = 0; y0 < size; y0++) {
                    for (int x0 = 0; x0 < size; x0++) {
                        spritePixels[x0 + y0 * size] = pixels[(x0 + xa * size) + (y0 + ya * size) * WIDTH];
                    }
                }
                sprites[counter++] = new Sprite(spritePixels, size, size);
            }
        }
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void load() {
        try {
            System.out.print("Loading - " + file + ": ");
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(file));
            System.out.println("Succeeded!");
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Failed!");
        }
    }
}