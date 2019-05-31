package ch.heigvd.cc.downfall.view;

import ch.heigvd.cc.downfall.modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;

public class GameFrame extends JFrame implements Runnable, KeyListener {
    JFrame gameFrame;
    ArrayList<PannelModel> pannels;

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

    private String player1Name = "Player1";
    private int player1wins = 0;
    private int player1UP = 104;
    private int player1DOWN = 101;
    private int player1LEFT = 100;
    private int player1RIGHT = 102;
    private int player1SHOOT = KeyEvent.VK_DOWN;
    private ArrayList<CupCakeModel> cupCakesPlayer1Throw;

    //player2
    private String player2Name = "Player2";
    private int player2wins = 0;
    private int player2UP = KeyEvent.VK_W;
    private int player2DOWN = KeyEvent.VK_S;
    private int player2LEFT = KeyEvent.VK_A;
    private int player2RIGHT = KeyEvent.VK_D;
    private int player2SHOOT = KeyEvent.VK_SPACE;
    private ArrayList<CupCakeModel> cupCakesPlayer2Throw;

    HashSet<Integer> keysPressed;
    HashSet<Integer> keysToggled;

    Boolean pause = false;
    Boolean pausePress = false;

    MenuPanelModel menu;
    WinPanelModel winPanel;

    // afin de savoir quel état afficher
    // 0 = gameMenu
    // 1 = game options
    // 2 = game play
    // 3 = game win
    // 4 = quit
    int curState = 0;
    int nextState = 0;

    public GameFrame(){
        pannels = new ArrayList<PannelModel>();
        keysPressed = new HashSet<Integer>();
        keysToggled = new HashSet<Integer>();
    }

    public void init(){
        gameFrame = new JFrame("DownFall");
        initMenu();

        for(int i = 0; i< pannels.size(); i++){
            gameFrame.add(pannels.get(i).getPannel());
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

    private void initWinPanel(){
        if(((PlayerPannelModel)pannels.get(0)).hasLost()){
            player1wins++;
        }else{
            player2wins++;
        }
        winPanel = new WinPanelModel(player1Name, player1wins, player2Name,player2wins,((PlayerPannelModel)pannels.get(0)).hasLost());
        gameFrame.getContentPane().removeAll();
        pannels.clear();
        winPanel.setKeyPressedList(keysPressed);
        pannels.add(winPanel);
        for(int i = 0; i< pannels.size(); i++){
            gameFrame.add(pannels.get(i).getPannel());
        }
        gameFrame.setVisible(true);
    }

    private void initMenu(){
        player1wins = 0;
        player2wins = 0;
        gameFrame.getContentPane().removeAll();
        pannels.clear();
        menu = new MenuPanelModel();
        menu.setKeyPressedList(keysPressed);
        pannels.add(menu);
        for(int i = 0; i< pannels.size(); i++){
            gameFrame.add(pannels.get(i).getPannel());
        }
        gameFrame.setVisible(true);
    }

    private void initGame(){
        gameFrame.getContentPane().removeAll();
        pannels.clear();
        cupCakesPlayer1Throw = new ArrayList<CupCakeModel>();
        cupCakesPlayer2Throw = new ArrayList<CupCakeModel>();
        PlayerPannelModel player1 = new PlayerPannelModel(player1Name);
        player1.setPlayerKeyMap(player1UP, player1DOWN, player1LEFT, player1RIGHT, player1SHOOT);
        player1.setKeyPressedList(keysPressed);
        player1.setCupCakesOther(cupCakesPlayer2Throw);
        player1.setCupCakesThrow(cupCakesPlayer1Throw);
        player1.init();
        PlayerPannelModel player2 = new PlayerPannelModel(player2Name);

        player2.setPlayerKeyMap(player2UP, player2DOWN, player2LEFT, player2RIGHT, player2SHOOT);
        player2.setKeyPressedList(keysPressed);
        player2.setCupCakesOther(cupCakesPlayer1Throw);
        player2.setCupCakesThrow(cupCakesPlayer2Throw);
        player2.init();

        pannels.add(player2);
        pannels.add(player1);
        for(int i = 0; i< pannels.size(); i++){
            gameFrame.add(pannels.get(i).getPannel());
        }
        gameFrame.setVisible(true);
        pause = false;
    }

    public void keyTyped(KeyEvent e) {

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
            if(curState != nextState){
                // 0 = gameMenu
                // 1 = game options
                // 2 = game play
                // 3 = game win
                // 4 = quit
                switch (nextState){
                    case 0:initMenu();break;
                    case 2:initGame(); break;
                    case 3:initWinPanel();break;
                    case 4:System.exit(0);
                }
                curState = nextState;
            }
            start = System.nanoTime();
            togglePause();
            for(int i = 0; i<pannels.size(); i++){
                pannels.get(i).update();
                pannels.get(i).repaint();
                if(pannels.get(i).getClass() == PlayerPannelModel.class){
                    ((PlayerPannelModel) pannels.get(i)).setPause(pause);
                    if(((PlayerPannelModel) pannels.get(i)).hasLost()) nextState = 3;
                }
                if(pannels.get(i).getClass() == MenuPanelModel.class){
                    manageGameMenu((MenuPanelModel)pannels.get(i));
                }
                if(pannels.get(i).getClass() == WinPanelModel.class){
                    manageWinPanel((WinPanelModel) pannels.get(i));
                }
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

    // 0 = gameMenu
    // 1 = game options
    // 2 = game play
    // 3 = game win
    // 4 = quit

    private void manageGameMenu(MenuPanelModel menu){
        //System.out.println(toggleKey(KeyEvent.VK_ENTER));
        if(toggleKey(KeyEvent.VK_ENTER)){
           switch (menu.getGameOption()){
               case 0 : nextState = 2; break;
               case 1 : nextState = 1; break;
               case 2 : nextState = 4; break;
            }
        }
    }

    private void manageWinPanel(WinPanelModel win){
        if(toggleKey(KeyEvent.VK_ENTER) && keysPressed.contains(KeyEvent.VK_ENTER)){
            switch (win.getGameOption()){
                case 0 : nextState = 2; break;
                case 1 : nextState = 0; break;
                case 2 : nextState = 4; break;
            }
        }
    }

    private void togglePause(){
        if(keysPressed.contains(KeyEvent.VK_ESCAPE)){
            if(pausePress){
                pause = !pause;
            }
            pausePress = false;
        }else{
            pausePress = true;
        }
    }

    /*if(keysPressed.contains(shoot)){
            if(okToShoot){
                shooting = true;
            }else{
                shooting = false;
            }
            okToShoot = false;
        }else{
            shooting = false;
            okToShoot = true;
        }*/

    private Boolean toggleKey(int key){
        Boolean returnVal = false;
        if(keysPressed.contains(key)) {
            if(keysToggled.contains(key)){
                returnVal = true;
            }else{
                returnVal = false;
            }
            keysToggled.remove(key);
        }else{
            returnVal = false;
            keysToggled.add(key);
        }
        return returnVal;
    }
}
