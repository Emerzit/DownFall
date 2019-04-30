package ch.heigvd.cc.downfall;

import javax.swing.*;

public class MainFrame extends JFrame {

    Partie pEnCours;

    MainFrame(){
        setTitle("DownFall");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);

        startPartie();
    }

    void startPartie(){
        pEnCours = new Partie();
        JPanel player1 = new PlayerPannel(pEnCours.getPlayer1());
        JPanel player2 = new PlayerPannel(pEnCours.getPlayer2());

        setContentPane(player1);
        setContentPane(player2);
    }

    void showMenu(){};

}
