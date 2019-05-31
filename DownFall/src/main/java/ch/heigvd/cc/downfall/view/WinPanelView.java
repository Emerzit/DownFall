package ch.heigvd.cc.downfall.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WinPanelView extends JPanel {

    Background bg;

    private Color textColor;
    private Color selectColor;
    private Color shadeColor;
    private Font unicornFont;
    private Font unicornFontSmall;
    private Font softFont;

    private String[] options;
    private int selectedOption;

    private String player1name;
    private String player2name;

    private int player1wins;
    private int player2wins;

    private Boolean isLooserLeft;

    BufferedImage winner;
    BufferedImage looser;

    public WinPanelView(String[] options, String player1name, int player1wins,  String player2name, int player2wins, Boolean isLooserLeft){
        this.player1name = player1name;
        this.player2name = player2name;
        this.player1wins = player1wins;
        this.player2wins = player2wins;

        this.isLooserLeft = isLooserLeft;
        this.options = options;
        textColor = new Color(248, 172, 213);
        selectColor = new Color(255, 75, 150);
        shadeColor = new Color(0, 0, 0, 25);

        unicornFont = CustomFonts.UnicornFont.deriveFont(Font.PLAIN,60);
        unicornFontSmall = CustomFonts.UnicornFont.deriveFont(Font.PLAIN,30);
        softFont = CustomFonts.SoftFont.deriveFont(Font.PLAIN,25);

        try {
            winner = ImageIO.read(
                    PlatformImg.class.getResourceAsStream("/Player/playerWin.png")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            looser = ImageIO.read(
                    PlatformImg.class.getResourceAsStream("/Player/playerLoose.png")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        FontMetrics metrics = g2.getFontMetrics(unicornFont);
        int txtX = (GameFrame.WIDTH - metrics.stringWidth("Game Over")) / 2;

        g2.setFont(unicornFont);
        g2.setColor(shadeColor);
        g2.drawString("Game Over",txtX+3, 103);
        g2.setColor(textColor);
        g2.drawString("Game Over",txtX, 100);


        // affichage image gagnant / perdant
        if(isLooserLeft) {
            g2.drawImage(looser, GameFrame.WIDTH / 4 - looser.getWidth() / 2, 140, null);
            g2.drawImage(winner, GameFrame.WIDTH / 4 - winner.getWidth() / 2 + GameFrame.WIDTH / 2, 140, null);
        }else{
            g2.drawImage(winner, GameFrame.WIDTH / 4 - looser.getWidth() / 2, 140, null);
            g2.drawImage(looser, GameFrame.WIDTH / 4 - winner.getWidth() / 2 + GameFrame.WIDTH / 2, 140, null);
        }

        // affichage nom player 1/2
        metrics = g2.getFontMetrics(unicornFontSmall);
        g2.setFont(unicornFontSmall);
        int player1X = (GameFrame.WIDTH/4 - metrics.stringWidth(player1name)/2) + GameFrame.WIDTH/2;
        int player2X = (GameFrame.WIDTH/4 - metrics.stringWidth(player2name)/2);
        g2.setColor(shadeColor);
        g2.drawString(player1name,player1X+3, 323);
        g2.drawString(player2name,player2X+3, 323);
        g2.setColor(selectColor);
        g2.drawString(player1name,player1X, 320);
        g2.drawString(player2name,player2X, 320);

        // affichage score
        metrics = g2.getFontMetrics(softFont);
        g2.setFont(softFont);
        int scoreX =  (GameFrame.WIDTH/2 - metrics.stringWidth("[ "+player2wins+" - "+player1wins+" ]")/2);
        g2.setColor(shadeColor);
        g2.drawString("[ "+player2wins+" - "+player1wins+" ]", scoreX+3,GameFrame.HEIGHT/2 - metrics.getHeight()/2 +3);
        g2.setColor(selectColor);
        g2.drawString("[ "+player2wins+" - "+player1wins+" ]", scoreX,GameFrame.HEIGHT/2 - metrics.getHeight()/2 );

        // affichage menu
        g2.setFont(softFont);
        for(int i=0; i<options.length;i++ ){
            g2.setColor(shadeColor);
            int posX = (GameFrame.WIDTH - metrics.stringWidth(options[i])) / 2;
            g2.drawString(options[i],posX+2, 300+(i+1)*40+2);
            if(i == selectedOption){
                g2.setColor(selectColor);
            }else {
                g2.setColor(textColor);
            }
            g2.drawString(options[i],posX, 300+(i+1)*40);
        }
    }
}
