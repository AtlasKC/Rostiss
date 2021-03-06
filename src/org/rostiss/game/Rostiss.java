package org.rostiss.game;

import org.rostiss.game.entity.mob.Player;
import org.rostiss.game.graphics.Renderer2D;
import org.rostiss.game.graphics.ui.UIManager;
import org.rostiss.game.input.Keyboard;
import org.rostiss.game.input.Mouse;
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

    private static Rostiss instance;
    private static UIManager uiManager;
    private Renderer2D renderer;
    private Keyboard keyboard = new Keyboard();
    private Mouse mouse = new Mouse();
    private Level level = Level.spawn;
    private Player player;
    private Thread thread;
    private JFrame frame;
    private BufferedImage image;
    private String title;
    private int[] pixels;
    private int scale, width, height;
    private boolean running = false;

    //Multiplayer stuff
//    public Client client;
//    public Server server;

    private Rostiss() {
        this("Rostiss 0.1.3-7 Beta");
    }

    private Rostiss(String title) {
        this(title, 300, 300 / 16 * 9, 3);
    }

    private Rostiss(String title, int width) {
        this(title, width / 3, width / 3 / 16 * 9, 3);
    }

    private Rostiss(String title, int width, int height, int scale) {
        this.width = width - 75;
        this.height = height;
        this.scale = scale;
        this.title = title;
        TileCoords spawn = new TileCoords(19, 62);
        setPreferredSize(new Dimension((this.width + 75) * this.scale, this.height * this.scale));
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        renderer = new Renderer2D(width, height);
        player = new Player("AtlasIND", keyboard, spawn.getX(), spawn.getY());
        player.setLevel(Level.spawn);
        level.add(player);
        frame = new JFrame();
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle(title + " - 0 ups, 0 fps");
        frame.setVisible(true);
        addKeyListener(keyboard);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        start();
    }

    private synchronized void start() {
        requestFocus();
        running = true;
        thread = new Thread(this);
        thread.start();
//        if(JOptionPane.showConfirmDialog(this, "Start a server?") == 0) {
//        	server = new Server(this);
//        	server.start();
//        }
//        client = new Client(this, "localhost");
//        client.start();
//        client.sendData("ping".getBytes());
    }

    private synchronized void stop() {
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
                //System.out.println(updates + " ups, " + frames + " fps");
                frame.setTitle(title + " - " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void update() {
        keyboard.update();
        level.update();
        getUIManager().update();
    }

    private void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }
        renderer.clear();
        level.render((int) (player.getX() - renderer.width / 2), (int) (player.getY() - renderer.height / 2), renderer);
        arraycopy(renderer.pixels, 0, pixels, 0, pixels.length);
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        getUIManager().render(g);
        g.dispose();
        bufferStrategy.show();
    }

    public int getScale() {
        return scale;
    }

    public int getWidth() {
        return width * scale;
    }

    public int getHeight() {
        return height * scale;
    }

    public static Rostiss getInstance() {
        if (instance == null)
            instance = new Rostiss("Rostiss 0.3.7-4 Beta", 900);
        return instance;
    }

    public static UIManager getUIManager() {
        if (uiManager == null)
            uiManager = new UIManager();
        return uiManager;
    }

    public static void main(String[] args) {
        getInstance();
    }
}