package org.rostiss.game.graphics.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * File: UIManager.java
 * Created by Atlas IND on 8/8/2015 at 10:28 PM.
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

public class UIManager {

    private List<UIPanel> panels = new ArrayList<>();

    public UIManager() {

    }

    public void addPanel(UIPanel panel) { panels.add(panel); }

    public void update() {
        for(UIPanel panel : panels)
            panel.update();
    }

    public void render(Graphics g) {
        for(UIPanel panel : panels)
            panel.render(g);
    }
}