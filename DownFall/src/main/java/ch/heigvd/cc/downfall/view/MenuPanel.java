package ch.heigvd.cc.downfall.view;
import com.sun.javafx.font.Metrics;

import javax.swing.*;
import java.awt.*;


public class MenuPanel extends JPanel {
    Background bg;

    private Color textColor;
    private Color selectColor;
    private Color shadeColor;
    private Font unicornFont;
    private Font softFont;

    private String[] options;
    private int selectedOption;

    public MenuPanel(String[] options){
        this.options = options;
        textColor = new Color(248, 172, 213);
        selectColor = new Color(255, 75, 150);
        shadeColor = new Color(0, 0, 0, 25);

        unicornFont = CustomFonts.UnicornFont.deriveFont(Font.PLAIN,60);
        softFont = CustomFonts.SoftFont.deriveFont(Font.PLAIN,25);
    }

    public void setSelectedOption(int option){
        selectedOption = option;
    }

    public void setBg(Background bg){
        this.bg = bg;
    }

    protected void paintComponent(Graphics g) {
        bg.update();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // for smooth text
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        bg.draw(g2);

        g2.setFont(unicornFont);
        g2.setColor(shadeColor);
        g2.drawString("DownFall",177, 189);
        g2.setColor(textColor);
        g2.drawString("DownFall",174, 186);

        FontMetrics metrics = g2.getFontMetrics(softFont);
        g2.setFont(softFont);
        for(int i=0; i<options.length;i++ ){
            g2.setColor(shadeColor);
            int posX = (GameFrame.WIDTH - metrics.stringWidth(options[i])) / 2;
            g2.drawString(options[i],posX+2, 186+(i+1)*40+2);
            if(i == selectedOption){
                g2.setColor(selectColor);
            }else {
                g2.setColor(textColor);
            }
            g2.drawString(options[i],posX, 186+(i+1)*40);
        }
    }
}
