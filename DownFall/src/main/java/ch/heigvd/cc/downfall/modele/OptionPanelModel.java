package ch.heigvd.cc.downfall.modele;

import ch.heigvd.cc.downfall.utils.Pair;
import ch.heigvd.cc.downfall.view.Background;
import ch.heigvd.cc.downfall.view.OptionPanelView;
import ch.heigvd.cc.downfall.view.WinPanelView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

public class OptionPanelModel extends PannelModel{
    private Background bg;
    HashSet<Integer> keysPressed;

    private ArrayList<Pair> options;

    private int currentChoice = 0;
    private Boolean choiceIsSelected = false;

    private Boolean downPress = false;
    private Boolean upPress = false;

    private OptionPanelView optView;

    private HashSet<Integer> keysToggled;

    int lastTyped;
    int curTyped = -1;

    public void setLastTyped(int key){
        lastTyped = key;
    }

    char lastChar;

    public void setLastChar(char c){
        lastChar = c;
    }

    private String player1Name;
    private String player2Name;

    public OptionPanelModel(
            String pl1Name,
            int pl1Up,
            int pl1Down,
            int pl1Left,
            int pl1Right,
            int pl1Shoot,
            String pl2Name,
            int pl2Up,
            int pl2Down,
            int pl2Left,
            int pl2Right,
            int pl2Shoot){
        keysToggled = new HashSet<Integer>();
        options = new ArrayList<Pair>();

        player1Name = pl1Name;
        player2Name = pl2Name;

        options.add(new Pair("Name",pl2Name));
        options.add(new Pair("Up",pl2Up));
        options.add(new Pair("Down",pl2Down));
        options.add(new Pair("Left",pl2Left));
        options.add(new Pair("Right",pl2Right));
        options.add(new Pair("Shoot",pl2Shoot));
        options.add(new Pair("Name",pl1Name));
        options.add(new Pair("Up",pl1Up));
        options.add(new Pair("Down",pl1Down));
        options.add(new Pair("Left",pl1Left));
        options.add(new Pair("Right",pl1Right));
        options.add(new Pair("Shoot",pl1Shoot));
        options.add(new Pair("Return",null));

        optView = new OptionPanelView(options);
        this.bg = new Background("/Backgrounds/menubg.gif");
        bg.setVector(0.5, 0.5);
        optView.setBg(bg);
    }

    public void setKeyPressedList(HashSet keysPressed){
        this.keysPressed = keysPressed;
    }

    public void update() {
        if(!choiceIsSelected) {
            if (toggleKey(KeyEvent.VK_DOWN)) currentChoice = (currentChoice + 1) % options.size();
            if (toggleKey(KeyEvent.VK_UP)) currentChoice = (currentChoice - 1 + options.size()) % options.size();
            if (toggleKey(KeyEvent.VK_RIGHT) && currentChoice < 6) currentChoice += 6;
            if (toggleKey(KeyEvent.VK_LEFT) && currentChoice >= 6 && currentChoice < 12) currentChoice -= 6;
        }else{
            if(currentChoice != 0 && currentChoice != 6) {
                if (lastTyped != curTyped) {
                    curTyped = lastTyped;
                    options.get(currentChoice).setSecondValue(lastTyped);
                    choiceIsSelected = !choiceIsSelected;
                }
            }else{
                KeyStroke ks = KeyStroke.getKeyStroke(Character.toUpperCase(lastChar), 0);

                if(keysPressed.contains(ks.getKeyCode()) && KeyEvent.VK_ENTER != lastChar) {
                    String tempVal = (String) options.get(currentChoice).second();
                    if(lastChar == KeyEvent.VK_BACK_SPACE && tempVal.length() != 0) {
                        tempVal = tempVal.substring(0, tempVal.length() - 1);
                    }else if(lastChar != KeyEvent.VK_BACK_SPACE){
                        if( (currentChoice == 0 && tempVal == player2Name) || (currentChoice == 6 && tempVal == player1Name) ){
                            tempVal = Character.toString(lastChar);
                        }else {
                            tempVal += lastChar;
                        }
                    }

                    options.get(currentChoice).setSecondValue(tempVal);
                }
                lastChar = 0;
            }
            optView.setOptions(options);
        }

        if(lastTyped != curTyped){
            curTyped = lastTyped;
        }

        if(toggleKey(KeyEvent.VK_ENTER)){
            choiceIsSelected = !choiceIsSelected;
            player2Name = (String)options.get(0).second();
            player1Name = (String)options.get(6).second();
        }

        optView.setChoiceIsSelected(choiceIsSelected);
        optView.setSelectedOption(currentChoice);
    }

    public int getGameOption(){
        return currentChoice;
    }

    public void repaint() {
        optView.repaint();
    }

    public JPanel getPannel() {
        return optView;
    }

    public ArrayList<Pair> getOptions(){
        return options;
    }

    private Boolean toggleKey(int key){
        Boolean returnVal = false;
        if(keysPressed.contains(key)) {
            if(keysToggled.contains(key)){
                returnVal = true;
            }else{
                returnVal = false;
            }
            keysToggled.remove(key);
        }else{
            returnVal = false;
            keysToggled.add(key);
        }
        return returnVal;
    }
}
