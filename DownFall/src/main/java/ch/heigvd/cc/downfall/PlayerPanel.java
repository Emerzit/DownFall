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
   
   PlayerPanel(Color bgcolor){
      this.bgcolor = bgcolor;
      repaint();
   }
   
   @Override
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      setBackground(bgcolor);
      g.setColor(Color.BLACK);
      g.fillRect(10, 10, 50, 50);
   }
}
