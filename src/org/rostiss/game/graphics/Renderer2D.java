package org.rostiss.game.graphics;

import org.rostiss.game.entity.projectile.Projectile;
import org.rostiss.game.level.tile.Tile;

import java.util.Random;

/**
 * File: Renderer2D.java
 * Created by Atlas IND on 6/20/2015 at 4:28 PM.
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

public class Renderer2D {

    public final int MAP_SIZE = 4;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int[] pixels;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    public int width, height, xOffset, yOffset;

    private Random random = new Random();

    public Renderer2D(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++)
            tiles[i] = random.nextInt(0xFFFFFF);
        tiles[0] = 0;
    }

    public void renderPlayer(int xPos, int yPos, Sprite sprite, boolean reflectX) {
        xPos -= xOffset;
        yPos -= yOffset;
        for (int y = 0; y < sprite.SIZE; y++) {
            int worldY = yPos + y;
            for (int x = 0; x < sprite.SIZE; x++) {
                int worldX = xPos + x;
                int dx = sprite.SIZE - 1 - x;
                if (worldX < -sprite.SIZE || worldX >= width || worldY < 0 || worldY >= height) break;
                if (worldX < 0) worldX = 0;
                int color;
                if (reflectX)
                    color = sprite.pixels[dx + y * sprite.SIZE];
                else
                    color = sprite.pixels[x + y * sprite.SIZE];
                if (color != 0xFFFF00FF)
                    pixels[worldX + worldY * width] = color;
            }
        }
    }

    public void renderTile(int xPos, int yPos, Tile tile) {
        xPos -= xOffset;
        yPos -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int worldY = yPos + y;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int worldX = xPos + x;
                if (worldX < -tile.sprite.SIZE || worldX >= width || worldY < 0 || worldY >= height) break;
                if (worldX < 0) worldX = 0;
                pixels[worldX + worldY * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void renderProjectile(int xPos, int yPos, Projectile projectile) {
        xPos -= xOffset;
        yPos -= yOffset;
        for (int y = 0; y < projectile.getSize(); y++) {
            int worldY = yPos + y;
            for (int x = 0; x < projectile.getSize(); x++) {
                int worldX = xPos + x;
                if (worldX < -projectile.getSize() || worldX >= width || worldY < 0 || worldY >= height) break;
                if (worldX < 0) worldX = 0;
                int color = projectile.getSprite().pixels[x + y * projectile.getSize()];
                if (color != 0xFFFF00FF)
                    pixels[worldX + worldY * width] = color;
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = 0x000000;
    }
}