package ch.heigvd.cc.downfall.view;

import ch.heigvd.cc.downfall.modele.CupCakeModel;
import ch.heigvd.cc.downfall.modele.PlayerPannelModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    /*private int player1UP = KeyEvent.VK_UP;
    private int player1DOWN = KeyEvent.VK_DOWN;
    private int player1LEFT = KeyEvent.VK_LEFT;
    private int player1RIGHT = KeyEvent.VK_RIGHT;*/

    private int player1UP = 104;
    private int player1DOWN = 101;
    private int player1LEFT = 100;
    private int player1RIGHT = 102;
    private int player1SHOOT = KeyEvent.VK_DOWN;
    private ArrayList<CupCakeModel> cupCakesPlayer1Throw;

    //player2 keys
    private int player2UP = KeyEvent.VK_W;
    private int player2DOWN = KeyEvent.VK_S;
    private int player2LEFT = KeyEvent.VK_A;
    private int player2RIGHT = KeyEvent.VK_D;
    private int player2SHOOT = KeyEvent.VK_SPACE;
    private ArrayList<CupCakeModel> cupCakesPlayer2Throw;

    HashSet<Integer> keysPressed;

    Boolean pause = false;

    public GameFrame(){
        pannels = new ArrayList<PlayerPannelModel>();
        keysPressed = new HashSet<Integer>();
        cupCakesPlayer1Throw = new ArrayList<CupCakeModel>();
        cupCakesPlayer2Throw = new ArrayList<CupCakeModel>();
        PlayerPannelModel player1 = new PlayerPannelModel("Player 1");
        player1.setPlayerKeyMap(player1UP, player1DOWN, player1LEFT, player1RIGHT, player1SHOOT);
        player1.setKeyPressedList(keysPressed);
        player1.setCupCakesOther(cupCakesPlayer2Throw);
        player1.setCupCakesThrow(cupCakesPlayer1Throw);
        player1.init();
        PlayerPannelModel player2 = new PlayerPannelModel("Player 2");

        player2.setPlayerKeyMap(player2UP, player2DOWN, player2LEFT, player2RIGHT, player2SHOOT);
        player2.setKeyPressedList(keysPressed);
        player2.setCupCakesOther(cupCakesPlayer1Throw);
        player2.setCupCakesThrow(cupCakesPlayer2Throw);
        player2.init();
        //player2.getPlayerPannel().setCupCakesThrow(cupCakesPlayer2Throw);

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

    public void keyTyped(KeyEvent e) {

        }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) pause = !pause;
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
                pannels.get(i).repaint();
                pannels.get(i).setPause(pause);
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
