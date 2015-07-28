package org.rostiss.game.graphics;

import org.rostiss.game.entity.mob.AStar;
import org.rostiss.game.entity.mob.Chaser;
import org.rostiss.game.entity.mob.Mob;
import org.rostiss.game.entity.projectile.Projectile;
import org.rostiss.game.level.tile.Tile;
import org.rostiss.game.util.Vector2i;

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

    private final int MAP_SIZE = 4, MAP_SIZE_MASK = MAP_SIZE - 1;
    public int[] pixels;
    private int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    public int width;
    public int height;
    private int xOffset;
    private int yOffset;

    public Renderer2D(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++)
            tiles[i] = new Random().nextInt(0xFFFFFF);
        tiles[0] = 0;
    }

    public void renderSpriteSheet(int dx, int dy, SpriteSheet spriteSheet, boolean fixed) {
        if (fixed) {
            dx -= xOffset;
            dy -= yOffset;
        }
        for (int y = 0; y < spriteSheet.HEIGHT; y++) {
            int worldY = y + dy;
            for (int x = 0; x < spriteSheet.WIDTH; x++) {
                int worldX = x + dx;
                if(worldX < 0 || worldX >= width || worldY < 0 || worldY >= height) continue;
                pixels[worldX + worldY * width] = spriteSheet.pixels[x + y * spriteSheet.WIDTH];
            }
        }
    }

    public void renderSprite(int dx, int dy, Sprite sprite, boolean fixed) {
        if (fixed) {
            dx -= xOffset;
            dy -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight(); y++) {
            int worldY = y + dy;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int worldX = x + dx;
                if(worldX < 0 || worldX >= width || worldY < 0 || worldY >= height) continue;
                pixels[worldX + worldY * width] = sprite.pixels[x + y * sprite.getWidth()];
            }
        }
    }

    public void renderMob(int xPos, int yPos, Mob mob) {
        xPos -= xOffset;
        yPos -= yOffset;
        for (int y = 0; y < mob.getSprite().SIZE; y++) {
            int worldY = yPos + y;
            for (int x = 0; x < mob.getSprite().SIZE; x++) {
                int worldX = xPos + x;
                if (worldX < -mob.getSprite().SIZE || worldX >= width || worldY < 0 || worldY >= height) break;
                if (worldX < 0) worldX = 0;
                int color = mob.getSprite().pixels[x + y * mob.getSprite().SIZE];
                if(mob instanceof Chaser && color == 0xFFFFB08E) color = 0xFF775243;
                if(mob instanceof AStar && color == 0xFFFFB08E) color = 0xFF000000;
                if (color != 0xFFFF00FF)
                    pixels[worldX + worldY * width] = color;
            }
        }
    }

    public void renderMob(int xPos, int yPos, Sprite sprite, boolean reflectX) {
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

    public void drawRect(Vector2i position, Vector2i size, int color, boolean fixed) {
        int xPos = position.getX();
        int yPos = position.getY();
        int width = size.getX();
        int height = size.getY();
        if(fixed) {
            xPos -= xOffset;
            yPos -= yOffset;
        }
        for(int x = xPos; x < xPos + width; x++) {
            if(yPos >= this.height || x < 0 || x >= this.width) continue;
            if(yPos > 0) pixels[x + yPos * this.width] = color;
            if(yPos + height >= this.height) continue;
            if(yPos + height > 0) pixels[x + (yPos + height) * this.width] = color;
        }
        for(int y = yPos; y <= yPos + height; y++) {
            if(xPos >= this.width || y < 0 || y >= this.height) continue;
            if(xPos > 0) pixels[xPos + y * this.width] = color;
            if(xPos + width >= this.width) continue;
            if(xPos + width > 0) pixels[xPos + width + y * this.width] = color;
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