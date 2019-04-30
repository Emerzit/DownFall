package ch.heigvd.cc.downfall;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame("DownFall");
        window.setContentPane(new GamePannel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
