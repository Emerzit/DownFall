/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.cc.downfall;
import java.awt.*;
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
   
   Partie(){
      super("DownFall");
      setSize(width, height);
      setResizable(false);
      setLayout(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      PlayerPanel playerOnePanel = new PlayerPanel(0, 0, width/2, height, Color.BLUE);
      PlayerPanel playerTwoPanel = new PlayerPanel(width/2, 0, width/2, height, Color.ORANGE);
      //playerOnePanel.setBounds(0, 0, width/2, height);
      //playerTwoPanel.setBounds(width/2, 0, width/2, height);
      add(playerOnePanel);
      add(playerTwoPanel);
      setLayout(new BorderLayout());
      setVisible(true);
   }
}
