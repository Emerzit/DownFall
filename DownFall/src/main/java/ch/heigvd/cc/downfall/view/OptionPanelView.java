package ch.heigvd.cc.downfall.view;

import ch.heigvd.cc.downfall.utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class OptionPanelView extends JPanel {

    Background bg;

    private Color textColor;
    private Color selectColor;
    private Color modifyColor;
    private Color shadeColor;
    private Font unicornFont;
    private Font unicornFontSmall;
    private Font softFont;

    private ArrayList<Pair> options;

    private int selectedOption;
    private Boolean choiceIsSelected = false;

    public OptionPanelView(ArrayList<Pair> options){
        this.options = options;
        textColor = new Color(248, 172, 213);
        selectColor = new Color(255, 75, 150);
        shadeColor = new Color(0, 0, 0, 25);
        modifyColor = new Color(3, 92, 208);

        unicornFont = CustomFonts.UnicornFont.deriveFont(Font.PLAIN,40);
        unicornFontSmall = CustomFonts.UnicornFont.deriveFont(Font.PLAIN,25);
        softFont = CustomFonts.SoftFont.deriveFont(Font.PLAIN,22);
    }

    public void setOptions(ArrayList<Pair> options){
        this.options = options;
    }

    protected void paintComponent(Graphics g) {
        int topPos = 20;
        int leftMargin = 30;
        int space = 25;

        bg.update();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // for smooth text
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        bg.draw(g2);


        g2.setFont(unicornFont);
        FontMetrics metrics = g2.getFontMetrics(unicornFont);

        int txtX = (GameFrame.WIDTH/4 - metrics.stringWidth("Player 2")/2);
        topPos += metrics.getHeight();

        g2.setColor(shadeColor);
        g2.drawString("Player 2", txtX+3, topPos+3);
        g2.setColor(textColor);
        g2.drawString("Player 2", txtX, topPos);

        txtX = (GameFrame.WIDTH/4 - metrics.stringWidth("Player 1")/2)+GameFrame.WIDTH/2;
        g2.setColor(shadeColor);
        g2.drawString("Player 1", txtX+3, topPos+3);
        g2.setColor(textColor);
        g2.drawString("Player 1", txtX, topPos);

        topPos += space;

        int posOld = topPos;
        g2.setFont(unicornFontSmall);
        metrics = g2.getFontMetrics(unicornFontSmall);
        int smallHeight = metrics.getHeight();
        for(int i = 0; i< 6; i++){
            if(i == selectedOption && !choiceIsSelected){
                g2.setColor(selectColor);
            }else if((i == selectedOption && choiceIsSelected)){
                g2.setColor(modifyColor);
            }else {
                g2.setColor(textColor);
            }
            topPos+=smallHeight;
            g2.setFont(unicornFontSmall);
            g2.drawString((String)options.get(i).first(), leftMargin, topPos);
            g2.setFont(softFont);
            if(i == 0) {
                g2.drawString(": " + options.get(i).second(), leftMargin + 75, topPos);
            }else {
                g2.drawString(": " + KeyEvent.getKeyText((Integer)options.get(i).second()), leftMargin + 75, topPos);
            }
        }

        topPos = posOld;

        for(int i = 6; i< 12; i++){
            if(i == selectedOption && !choiceIsSelected){
                g2.setColor(selectColor);
            }else if((i == selectedOption && choiceIsSelected)){
                g2.setColor(modifyColor);
            }else {
                g2.setColor(textColor);
            }
            topPos+=smallHeight;
            g2.setFont(unicornFontSmall);
            g2.drawString((String)options.get(i).first(), leftMargin+GameFrame.WIDTH/2, topPos);
            g2.setFont(softFont);
            if(i == 6) {
                g2.drawString(": " + options.get(i).second(), leftMargin+GameFrame.WIDTH/2+75, topPos);
            }else {
                g2.drawString(": " + KeyEvent.getKeyText((Integer)options.get(i).second()), leftMargin+GameFrame.WIDTH/2+75, topPos);
            }
        }
        topPos+=smallHeight+space;
        if(12 == selectedOption){
            g2.setColor(selectColor);
        }else {
            g2.setColor(textColor);
        }
        metrics = g2.getFontMetrics(softFont);
        txtX = (GameFrame.WIDTH/2 - metrics.stringWidth((String)options.get(12).first())/2);
        g2.drawString((String)options.get(12).first(),txtX, topPos );

    }

    public void setSelectedOption(int option){
        selectedOption = option;
    }

    public void setChoiceIsSelected(Boolean choice){
        choiceIsSelected = choice;
    }

    public void setBg(Background bg){
        this.bg = bg;
    }
}
