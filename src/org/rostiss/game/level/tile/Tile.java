package org.rostiss.game.level.tile;

import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.graphics.Sprite;

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

    public static Tile GRASS = new GrassTile(Sprite.GRASS);
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