package ch.heigvd.cc.downfall;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerPannel extends JPanel implements Runnable, KeyListener, Observer {
    public static int WIDTH = 540;
    public static int HEIGHT = 720;
    public static int SCALE = 1;
    PlayerEnvir player;

    //Thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    //image
    private BufferedImage image;
    private Graphics2D g;

    PlayerPannel(PlayerEnvir player) {
        super();
        this.player = player;
        add(new JLabel(player.getName()));
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    private void init() {

        try {
            image = ImageIO.read(this.getClass().getResource("/Player.png"));
        } catch (IOException e) {
            System.out.println(e);// TODO: handle exception
        }
        g = (Graphics2D) g;
        running = true;
    }

    public void run() {
        init();
        long start;
        long elapsed;
        long wait;

        while (running) {
            start = System.nanoTime();
            drawToScreen();
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, WIDTH, HEIGHT);
        g2.drawImage(image, (int) player.getPosX(), (int) player.getPosY(), null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent ev) {
        if (ev.getKeyCode() == KeyEvent.VK_W) {
            player.setXSpeed(0.0);
            player.setYSpeed(-1.0);
        }
        if (ev.getKeyCode() == KeyEvent.VK_A) {
            player.setXSpeed(-1.);
            player.setYSpeed(0.);
        }
        if (ev.getKeyCode() == KeyEvent.VK_S) {
        }
        if (ev.getKeyCode() == KeyEvent.VK_D) {
            player.setXSpeed(1.);
            player.setYSpeed(0.);
        }
    }

    public void keyReleased(KeyEvent ev) {
        if (ev.getKeyCode() == KeyEvent.VK_W) {
            player.setXSpeed(0.0);
            player.setYSpeed(0.0);
        }
        if (ev.getKeyCode() == KeyEvent.VK_A) {
            player.setXSpeed(0.);
            player.setYSpeed(0.);
        }
        if (ev.getKeyCode() == KeyEvent.VK_S) {
        }
        if (ev.getKeyCode() == KeyEvent.VK_D) {
            player.setXSpeed(0.);
            player.setYSpeed(0.);
        }
    }

    public void update(Object object) {

    }
}
