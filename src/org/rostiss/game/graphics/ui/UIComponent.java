package org.rostiss.game.graphics.ui;

import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.util.Vector2i;

/**
 * File: UIComponent.java
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

public class UIComponent {

    public Vector2i position, offset;

    public UIComponent(Vector2i position) {
        this.position = position;
        offset = new Vector2i();
    }

    void setOffset(Vector2i offset) {
        this.offset = offset;
    }

    public void update() {}

    public void render(Renderer2D renderer) {}
}