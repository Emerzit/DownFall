package ch.heigvd.cc.view;

//import ch.heigvd.cc.modele.*;

import ch.heigvd.cc.modele.PlayerPannelModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

public class GameFrame extends JFrame implements Runnable, KeyListener {
    JFrame gameFrame;
    ArrayList<PlayerPannelModel> pannels;

    //dimentions
    public static int WIDTH = 640;
    public static int HEIGHT = 480;
    public static  int SCALE = 1;


    //Thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;

    //Player1 keys
    private int player1UP = KeyEvent.VK_UP;
    private int player1DOWN = KeyEvent.VK_DOWN;
    private int player1LEFT = KeyEvent.VK_LEFT;
    private int player1RIGHT = KeyEvent.VK_RIGHT;

    //private int[] player1keysVal = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
    //private Boolean[] player1keys = {false,false,false,false};

    //player2 keys
    int player2UP = KeyEvent.VK_W;
    int player2DOWN = KeyEvent.VK_S;
    int player2LEFT = KeyEvent.VK_A;
    int player2RIGHT = KeyEvent.VK_D;

    //private int[] player2keysVal = {KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D};
    //private Boolean[] player2keys = {false,false,false,false};

    HashSet<Integer> keysPressed;

    public GameFrame(){
        pannels = new ArrayList<PlayerPannelModel>();
        keysPressed = new HashSet<Integer>();
        /*TEST*/
        PlayerPannelModel player1 = new PlayerPannelModel();
        player1.setPlayerKeyMap(player1UP, player1DOWN, player1LEFT, player1RIGHT);
        player1.setKeyPressedList(keysPressed);
        player1.init();
        PlayerPannelModel player2 = new PlayerPannelModel();
        player2.setPlayerKeyMap(player2UP, player2DOWN, player2LEFT, player2RIGHT);
        player2.setKeyPressedList(keysPressed);
        player2.init();

        pannels.add(player2);
        pannels.add(player1);

    }

    public void init(){
        gameFrame = new JFrame("DownFall");
        for(int i = 0; i< pannels.size(); i++){
            gameFrame.add(pannels.get(i).getPlayerPannel());
        }
        gameFrame.setLayout(new GridLayout(1, pannels.size()));
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);

        gameFrame.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        gameFrame.setFocusable(true);
        gameFrame.requestFocus();
        gameFrame.pack();
        gameFrame.setVisible(true);
        gameFrame.addKeyListener(this);

    }

    /*
    void setGamePannels(ArrayList<JPanel> pannels){
        this.pannels = pannels;
    }*/

    public void keyTyped(KeyEvent e) {//System.out.println("adsfadsf");
        }
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyCode());
    }
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

    public void run(){
        //init();
        long start;
        long elapsed;
        long wait;

        while(true){
            start = System.nanoTime();
            for(int i = 0; i<pannels.size(); i++){
                pannels.get(i).update();
                pannels.get(i).getPlayerPannel().repaint();
            }
            //update();
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;
            if(wait < 0) wait = 5;

            try{
                Thread.sleep(wait);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
