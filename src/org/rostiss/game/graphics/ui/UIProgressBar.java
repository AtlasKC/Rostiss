package org.rostiss.game.graphics.ui;

import org.rostiss.game.util.Vector2i;

import java.awt.*;

/**
 * File: UIProgressBar.java
 * Created by Atlas IND on 8/10/2015 at 3:49 PM.
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

public class UIProgressBar extends UIComponent {

    private Vector2i size;
    private Color fgColor;
    private double progress;

    public UIProgressBar(Vector2i position, Vector2i size) {
        super(position);
        this.size = size;
        this.fgColor = new Color(0xFF00FF);
    }

    public UIProgressBar setForegroundColor(int fgColor) {
        this.fgColor = new Color(fgColor);
        return this;
    }

    public double getProgress() {
        return progress;
    }

    public UIProgressBar setProgress(double progress) {
        if(!(progress < 0.0) && !(progress > 1.0))
            this.progress = progress;
        else this.progress = 0.0;
        return this;
    }

    public void update() {
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(position.getX() + offset.getX(), position.getY() + offset.getY(), size.getX(), size.getY());
        g.setColor(fgColor);
        g.fillRect(position.getX() + offset.getX() + 1, position.getY() + offset.getY() + 1, (int)(progress * size.getX()) - 2, size.getY() - 2);
    }
}