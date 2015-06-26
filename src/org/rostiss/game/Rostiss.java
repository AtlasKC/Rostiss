package org.rostiss.game;

import org.rostiss.game.entity.mob.Player;
import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.input.Keyboard;
import org.rostiss.game.level.Level;
import org.rostiss.game.level.TileCoords;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import static java.lang.System.arraycopy;
import static java.lang.System.nanoTime;

/**
 * File: Rostiss.java
 * Created by Atlas IND on 6/19/2015 at 11:15 AM.
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

public class Rostiss extends Canvas implements Runnable {

    public int scale, width, height;

    private String title;
    private Renderer2D renderer;
    private Keyboard keyboard;
    private Player player;
    private Thread thread;
    private JFrame frame;
    private BufferedImage image;
    private int[] pixels;
    private boolean running = false;

    public Rostiss() {
        this("Rostiss");
    }

    public Rostiss(String title) {
        this(title, 300, (int) 168.75, 3);
    }

    public Rostiss(String title, int width, int height, int scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.title = title;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        renderer = new Renderer2D(width, height);
        keyboard = new Keyboard();
        TileCoords spawn = new TileCoords(19, 62);
        player = new Player(keyboard, spawn.getX(), spawn.getY());
        Dimension size = new Dimension(this.width * this.scale, this.height * this.scale);
        setPreferredSize(size);
        frame = new JFrame();
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle(title + " - 0 ups, 0 fps");
        frame.setVisible(true);
        addKeyListener(keyboard);
        start();
    }

    public synchronized void start() {
        requestFocus();
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long previous = nanoTime(), timer = System.currentTimeMillis();
        final double k = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0, updates = 0;
        while (running) {
            long current = nanoTime();
            delta += (current - previous) / k;
            previous = current;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " ups, " + frames + " fps");
                frame.setTitle(title + " - " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public void update() {
        keyboard.update();
        player.update();
    }

    public void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }
        renderer.clear();
        int dx = player.x - renderer.width / 2;
        int dy = player.y - renderer.height / 2;
        Level.spawn.render(dx, dy, renderer);
        player.render(renderer);
        arraycopy(renderer.pixels, 0, pixels, 0, pixels.length);
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bufferStrategy.show();
    }

    public static void main(String[] args) {
        new Rostiss();
    }
}