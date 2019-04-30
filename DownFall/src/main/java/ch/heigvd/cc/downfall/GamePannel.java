package ch.heigvd.cc.downfall;

import ch.heigvd.cc.downfall.GameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class GamePannel extends JPanel implements Runnable, KeyListener{

    //dimentions
    public static int WIDTH = 640;
    public static int HEIGHT = 480;
    public static  int SCALE = 1;

    //Thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;

    //image
    private BufferedImage image;
    private Graphics2D g;

    // game state manager
    private GameStateManager gsm;

    // constructeur
    public GamePannel(){
        super();
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();
        if(thread==null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init(){
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D)g;
        running = true;
        gsm = new GameStateManager();
    }

    public void run(){
        init();
        long start;
        long elapsed;
        long wait;

        while(running){
            start = System.nanoTime();
            update();
            draw();
            drawToScreen();
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;

            try{
                Thread.sleep(wait);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void update(){
        gsm.update();
    }

    private void draw(){
        gsm.draw(g);
    }

    private void drawToScreen(){
        Graphics g2 = getGraphics();
        g2.drawImage(image,0,0,null);
        g2.dispose();
    }



    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());
    }
}
