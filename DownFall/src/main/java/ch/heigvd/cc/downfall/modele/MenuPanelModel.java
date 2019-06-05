package ch.heigvd.cc.downfall.modele;

import ch.heigvd.cc.downfall.view.Background;
import ch.heigvd.cc.downfall.view.MenuPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class MenuPanelModel extends PannelModel {
    private Background bg;
    private MenuPanel menuPanel;
    HashSet<Integer> keysPressed;

    private String[] options = {
            "Start",
            "Options",
            "Quit"
    };

    private int currentChoice = 0;
    private Boolean downPress = false;
    private Boolean upPress = false;

    public MenuPanelModel(){
        menuPanel = new MenuPanel(options);
        this.bg = new Background("/Backgrounds/menubg.gif");
        bg.setVector(0.5, 0.5);
        menuPanel.setBg(bg);
    }

    public void setKeyPressedList(HashSet keysPressed){
        this.keysPressed = keysPressed;
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

        menuPanel.setSelectedOption(currentChoice);
    }

    public void repaint() {
        menuPanel.repaint();
    }

    public JPanel getPannel() {
        return menuPanel;
    }

    public int getGameOption(){
        return currentChoice;
    }
}
