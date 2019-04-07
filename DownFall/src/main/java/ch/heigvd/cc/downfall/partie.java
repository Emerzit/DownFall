/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.cc.downfall;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author Edouard
 */
public class partie extends JFrame {
   private static final int width = 600;
   private static final int height = 400;
   partie(){
      super ("DownFall");
      setSize(width, height);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setLayout(new FlowLayout());
      
   }
}
