/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.cc.downfall;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Edouard
 */
public class PlayerPanel extends JPanel{
   
   Color bgcolor;
   static final int playerHeight = 50;
   static final int playerWidth = 25;
   
   PlayerPanel(int pPosY, int pPosX, int width, int height, Color bgcolor){
      setBounds(pPosY, pPosX, width, height);
      this.bgcolor = bgcolor;
      Player player = new Player();
      add(player);
      // création et posiition du joueur au milieur de sa zone
      player.setBounds(width/2 - playerWidth/2 ,height/2 - playerHeight/2,playerWidth,playerWidth);
      setLayout(new BorderLayout());
      repaint();
   }
   
   @Override
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      setBackground(bgcolor);
   }
   
   void moveUp(){
   
   }
}
