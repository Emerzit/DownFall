package ch.heigvd.cc.downfall;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    Partie pEnCours;

    MainFrame() {
        setTitle("DownFall");
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        setResizable(false);

        startPartie();
    }

    void startPartie() {
        pEnCours = new Partie();
        JPanel mainPanel = new JPanel();
        JPanel player1 = new PlayerPannel(pEnCours.getPlayer1());
        JPanel player2 = new PlayerPannel(pEnCours.getPlayer2());
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(player1);
        mainPanel.add(player2);
        getContentPane().add(mainPanel);
        this.setVisible(true);
    }

    void showMenu() {
    }

    ;

}
