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

    public final int SIZE;
    public int[] pixels;

    private String file;

    public SpriteSheet(String file, int size) {
        this.file = file;
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(file));
            int width = image.getWidth();
            int height = image.getHeight();
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}