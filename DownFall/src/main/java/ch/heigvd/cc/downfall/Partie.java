/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.cc.downfall;
import java.awt.*;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Edouard
 */
public class Partie extends JFrame {
   private static final int width = 600;
   private static final int height = 400;
   //private ArrayList<ClientListener> listeners = new ArrayList<>();
   
   private int pOneUp = VK_UP;
   private int pOneDown = VK_DOWN;
   private int pOneRight = VK_RIGHT;
   private int pOneLeft = VK_LEFT;
   private int pOneJump = VK_SPACE;
   private int pOneBtnA = VK_W;
   private int pOneBtnB = VK_D;
   
   PlayerPanel playerOnePanel;
   PlayerPanel playerTwoPanel;
   
   Partie(){
      super("DownFall");
      setSize(width, height);
      setResizable(false);
      setLayout(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      playerOnePanel = new PlayerPanel(0, 0, width/2, height, Color.BLUE);
      playerTwoPanel = new PlayerPanel(width/2, 0, width/2, height, Color.ORANGE);
      //playerOnePanel.setBounds(0, 0, width/2, height);
      //playerTwoPanel.setBounds(width/2, 0, width/2, height);
      add(playerOnePanel);
      add(playerTwoPanel);
      setLayout(new BorderLayout());
      setVisible(true);
      this.addKeyListener(k);
   }
   
   KeyListener k = new KeyListener(){
         public void keyTyped(KeyEvent e) {}
         public void keyPressed(KeyEvent e) {
            int keyPressed = e.getKeyCode();
            if(keyPressed == pOneUp){
               playerOnePanel.moveUp();
            }
         }
         public void keyReleased(KeyEvent e) {}
   };
}
