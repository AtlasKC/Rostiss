package org.rostiss.game.graphics;

/**
 * File: Font.java
 * Created by Atlas IND on 8/6/2015 at 10:35 AM.
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

public class Font {

    private static SpriteSheet arial = new SpriteSheet("/fonts/arial.png", 16);
    private static Sprite[] arial_chars = Sprite.split(arial);
    private static String characters = "ABCDEFGHIJKLM" +
            "NOPQRSTUVWXYZ" +
            "abcdefghijklm" +
            "nopqrstuvwxyz" +
            "0123456789.,'" +
            "'\"\";:!@$%()-+";

    public Font() {

    }

    public void render(String text, int x, int y, int size, Renderer2D renderer) {
        int xOffset = -size, yOffset, line = 0;
        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);
            int index = characters.indexOf(current);
            xOffset += size;
            yOffset = 0;
            if (current == 'g' || current == 'y' || current == 'q' || current == 'p' || current == 'j') yOffset = 3;
            if (current == ',') yOffset = 2;
            if (current == '\n') {
                xOffset = -size;
                line++;
            }
            if (index != -1)
                renderer.renderText(x + xOffset, y + (int) (line * (size + Math.sqrt(size))) + yOffset, 0xff7f007f, arial_chars[index], false);
        }
    }
}