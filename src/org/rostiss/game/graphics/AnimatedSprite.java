package org.rostiss.game.graphics;

/**
 * File: AnimatedSprite.java
 * Created by Atlas IND on 7/15/2015 at 4:28 PM.
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
public class AnimatedSprite extends Sprite {

    private Sprite sprite;
    private int frame = 0, rate = 8, size = -1, time = 0;

    public AnimatedSprite(SpriteSheet spriteSheet, int width, int height, int size) {
        super(spriteSheet, width, height);
        this.size = size;
        sprite = spriteSheet.getSprites()[0];
    }

    public void update() {
        time++;
        if(time % rate == 0) {
            if(frame >= size - 1) frame = 0;
            else frame++;
            sprite = spriteSheet.getSprites()[frame];
        }
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setFrame(int frame) {
        if(frame > spriteSheet.getSprites().length - 1)
            return;
        this.frame = frame;
        sprite = spriteSheet.getSprites()[frame];
    }

    public Sprite getSprite() {
        return sprite;
    }
}