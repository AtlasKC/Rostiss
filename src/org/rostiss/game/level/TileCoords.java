package org.rostiss.game.level;

/**
 * File: TileCoords.java
 * Created by Atlas IND on 6/24/2015 at 4:22 PM.
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

public class TileCoords {

    private final int TILE_SIZE = 16;
    private int x, y;

    public TileCoords(int x, int y) {
        this.x = x * TILE_SIZE;
        this.y = y * TILE_SIZE;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public int[] getXY() { return new int[] { x, y }; }
}