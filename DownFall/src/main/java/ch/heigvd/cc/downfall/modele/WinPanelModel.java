package ch.heigvd.cc.downfall.modele;

import ch.heigvd.cc.downfall.view.Background;
import ch.heigvd.cc.downfall.view.WinPanelView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class WinPanelModel extends PannelModel {

    private Background bg;
    HashSet<Integer> keysPressed;

    private String[] options = {
            "Rematch",
            "Main menu",
            "Quit"
    };

    private int currentChoice = 0;
    private Boolean downPress = false;
    private Boolean upPress = false;

    private WinPanelView winPanel;

    public WinPanelModel(String player1name, int player1wins,  String player2name, int player2wins, Boolean isLooserLeft){
        this.bg = new Background("/Backgrounds/menubg.gif");
        bg.setVector(0.5, 0.5);
        winPanel = new WinPanelView(options, player1name, player1wins, player2name, player2wins, isLooserLeft);
        winPanel.setBg(bg);
    }

    public void update() {
        if(keysPressed.contains(KeyEvent.VK_DOWN)){
            if(downPress){
                currentChoice = (currentChoice+1)%options.length;
            }
            downPress = false;
        }else{
            downPress = true;
        }
        if(keysPressed.contains(KeyEvent.VK_UP)){
            if(upPress){
                if(currentChoice == 0){
                    currentChoice = options.length-1;
                }else{
                    currentChoice--;
                }
            }
            upPress = false;
        }else{
            upPress = true;
        }

        winPanel.setSelectedOption(currentChoice);
    }

    public void setKeyPressedList(HashSet keysPressed){
        this.keysPressed = keysPressed;
    }

    public void repaint() {
        winPanel.repaint();
    }

    public JPanel getPannel() {
        return winPanel;
    }

    public int getGameOption(){
        return currentChoice;
    }
}
