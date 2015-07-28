package org.rostiss.game.util;

import org.rostiss.game.graphics.Renderer2D;

/**
 * File: Debug.java
 * Created by Atlas IND on 7/27/2015 at 12:51 PM.
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

public class Debug {

    private Debug() {}

    public static void drawRect(Renderer2D renderer, Vector2i position, Vector2i size, int color, boolean fixed) {
        renderer.drawRect(position, size, color, fixed);
    }

    public static void drawRect(Renderer2D renderer, Vector2i position, Vector2i size, boolean fixed) {
        drawRect(renderer, position, size, 0, fixed);
    }
}