package org.rostiss.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * File: Mouse.java
 * Created by Atlas IND on 6/26/2015 at 12:56 PM.
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

public class Mouse implements MouseListener, MouseMotionListener {

    private static int x = -1, y = -1, button = -1;

    public void mousePressed(MouseEvent e) {
        button = e.getButton();
    }

    public void mouseReleased(MouseEvent e) {
        button = -1;
    }

    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static int getButton() {
        return button;
    }
}