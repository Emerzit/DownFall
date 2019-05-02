package ch.heigvd.cc.downfall;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerPannel extends JPanel {
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

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
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
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }
}
