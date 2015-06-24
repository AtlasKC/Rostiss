package org.rostiss.game.level.tile.spawn_level;

import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.graphics.Sprite;
import org.rostiss.game.level.tile.Tile;

/**
 * File: WallTile.java
 * Created by Atlas IND on 6/24/2015 at 12:29 PM.
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

public class WallTile extends Tile {

    public WallTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Renderer2D renderer) {
        renderer.renderTile(x << 4, y << 4, this);
    }

    public boolean solid() {
        return true;
    }
}