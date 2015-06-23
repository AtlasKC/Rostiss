package org.rostiss.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

/**
 * File: Keyboard.java
 * Created by Atlas IND on 6/21/2015 at 8:36 PM.
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

public class Keyboard implements KeyListener {

    public boolean up, down, left, right;

    private boolean[] keys = new boolean[65536];

    public void update() {
        up = keys[VK_UP] || keys[VK_W];
        down = keys[VK_DOWN] || keys[VK_S];
        left = keys[VK_LEFT] || keys[VK_A];
        right = keys[VK_RIGHT] || keys[VK_D];
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e) {

    }
}