package ch.heigvd.cc.downfall.view;
import javax.swing.*;
import java.awt.*;


public class MenuPanel extends JPanel {
    Background bg;

    private Color textColor;
    private Font unicornFont;
    private Font softFont;

    private String[] options;

    public MenuPanel(String[] options){
        this.options = options;
        textColor = new Color(248, 172, 213);
        unicornFont = CustomFonts.UnicornFont.deriveFont(Font.PLAIN,30);
        softFont = CustomFonts.SoftFont.deriveFont(Font.PLAIN,25);
    }

    public void setBg(Background bg){
        this.bg = bg;
    }
}
