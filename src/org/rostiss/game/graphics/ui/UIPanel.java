package org.rostiss.game.graphics.ui;

import org.rostiss.game.util.Vector2i;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * File: UIPanel.java
 * Created by Atlas IND on 8/8/2015 at 11:07 PM.
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

public class UIPanel extends UIComponent {

    private List<UIComponent> components = new ArrayList<>();
    private Vector2i size;

    public UIPanel(Vector2i position, Vector2i size) {
        super(position);
        this.size = size;
        color = new Color(0x4F4F4F);
    }

    public void addComponent(UIComponent component) { components.add(component); }

    public void update() {
        for (UIComponent component : components) {
            component.setOffset(position);
            component.update();
        }
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(position.getX(), position.getY(), size.getX(), size.getY());
        for (UIComponent component : components)
            component.render(g);
    }
}