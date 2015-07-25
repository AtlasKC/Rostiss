package org.rostiss.game.level;

import org.rostiss.game.entity.mob.Chaser;
import org.rostiss.game.entity.mob.Dummy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * File: SpawnLevel.java
 * Created by Atlas IND on 6/24/2015 at 12:00 AM.
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
@SuppressWarnings("SameParameterValue")
public class SpawnLevel extends Level {

    public SpawnLevel(String file) {
        super(file);
    }

    protected void loadLevel(String file) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(file));
            this.width = image.getWidth();
            this.height = image.getHeight();
            tiles = new int[width * height];
            image.getRGB(0, 0, width, height, tiles, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: SpawnLevel - loadLevel() | Could not load level file.");
        }
        for(int i = 0; i < 5; i++)
            add(new Dummy(19, 55));
        add(new Chaser(15, 60));
    }
}