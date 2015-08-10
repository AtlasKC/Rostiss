package org.rostiss.game.graphics.ui;

import org.rostiss.game.graphics.Font;
import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.util.Vector2i;

/**
 * File: UILabel.java
 * Created by Atlas IND on 8/8/2015 at 11:10 PM.
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

public class UILabel extends UIComponent {

    public String text;

    private Font font;

    public UILabel(Vector2i position, String text) {
        super(position);
        this.text = text;
        this.font = new Font();
    }

    public void render(Renderer2D renderer) {
        font.render(text, position.getX() + offset.getX(), position.getY() + offset.getY(), 12, renderer);
    }
}