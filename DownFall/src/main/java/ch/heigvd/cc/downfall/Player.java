/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.cc.downfall;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author Edouard
 */
public class Player  extends JPanel{
   Player(){
   
   }
   
   @Override
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      setBackground(Color.BLACK);
   }
}
