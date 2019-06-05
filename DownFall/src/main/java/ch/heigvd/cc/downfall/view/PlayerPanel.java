package ch.heigvd.cc.downfall.view;

import ch.heigvd.cc.downfall.modele.CupCakeModel;
import ch.heigvd.cc.downfall.modele.PlatformModel;
import ch.heigvd.cc.downfall.modele.PlayerModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {

    private Background bg;

    private PlayerModel player;

    ArrayList<PlatformModel> platforms;
    ArrayList<CupCakeModel> cupCakesThrow;      // contient les cupcakes lancé par le joueur
    ArrayList<CupCakeModel> cupCakesReceve;     // contoent les cupcakes lancées par le joeur adverse

    private int maxPrimaryShots;

    public void setMaxPrimaryShots(int maxPrimaryShots){
        this.maxPrimaryShots = maxPrimaryShots;
    }
    private int curPrimaryShots;
    public void setCurPrimaryShots(int curPrimaryShots){
        this.curPrimaryShots = curPrimaryShots;
    }

    private int livesLeft;
    public void setLivesLeft(int lives){
        if(lives>0){
            livesLeft = lives;
        }else{
            livesLeft = 0;
        }
    }

    private Font shootFont;
    private Font livesFont;
    private Font unicornFont;

    // image de fond status de tie
    private BufferedImage statusInfo;

    // position du text et image infos tirs
    private int infoX;
    private int infoY;
    private int infoTxTy;

    // icone des vies
    private BufferedImage lifeIcon;

    // position de base des vies
    private int heartX = 10;
    private int heartY = 10;

    private String playerName;
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    // pour le début de la partie avec countdown
    private Boolean gameStart;
    public void setGameStart(Boolean gameStart){
        this.gameStart = gameStart;
    }
    public Boolean getGameStart(){
        return gameStart;
    }

    private int countDown = 6;
    private long timer = System.nanoTime();

    // pour centrer le text
    FontMetrics metrics;

    // pour effet kitchos -> affichage PUASE
    private int R = 255;
    private int V = 0;
    private int B = 0;
    private int rolli = 0;
    private int rollj = 0;

    private Boolean pause = false;
    public void setPause(Boolean pause){
        this.pause = pause;
    }

    public PlayerPanel() {
        new JPanel();
        Border blackline = BorderFactory.createLineBorder(new Color(0, 0, 0, 45));
        setBorder(blackline);
        setFocusable(true);
        requestFocus();
        try{
            statusInfo = ImageIO.read(
                    getClass().getResourceAsStream("/Weapons/cupCapeStatusBar.png")
            );
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            lifeIcon = ImageIO.read(
                    getClass().getResourceAsStream("/Lifes/heart.png")
            );
        }catch(Exception e){
            e.printStackTrace();
        }

        shootFont = CustomFonts.SoftFont.deriveFont(Font.PLAIN,19);
        infoX = GameFrame.WIDTH/2-statusInfo.getWidth();
        infoY = GameFrame.HEIGHT-(statusInfo.getHeight()*2+5);
        infoTxTy = infoY + shootFont.getSize() + 3;
        livesFont = CustomFonts.SoftFont.deriveFont(Font.PLAIN,30);

        unicornFont = CustomFonts.UnicornFont.deriveFont(Font.PLAIN,40);
    }

    @Override
    protected void paintComponent(Graphics g) {
        bg.update();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // for smooth text
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        bg.draw(g2);
        g2.setColor(Color.ORANGE);
        for (int i = 0; i < platforms.size(); i++) {
            g2.drawImage(platforms.get(i).getImg(), platforms.get(i).getX(), platforms.get(i).getY(), null);
        }
        if (player != null) {
            g2.drawImage(player.getImg(), player.getPosX(), player.getPosY(), null);
        }
        for (int i = 0; i < cupCakesThrow.size(); i++) {
            g2.drawImage(cupCakesThrow.get(i).getImg(), cupCakesThrow.get(i).getPosX(), cupCakesThrow.get(i).getPosY(), null);
        }

        g2.setColor(Color.RED);
        for (int i = 0; i < cupCakesReceve.size(); i++) {
            g2.drawImage(cupCakesReceve.get(i).getImg(), cupCakesReceve.get(i).getPosX(), cupCakesReceve.get(i).getPosY(), null);
        }

        // affichage status tir
        shootStatus(g2);

        // affichage des vies
        lifeStatus(g2);

        // affichage start countdown

        if(!gameStart){
            countDown(g2);
        }

        //PAUSE
        if(pause) pause(g2);

    }

    protected void shootStatus(Graphics2D g2){
        g2.drawImage(statusInfo, infoX, infoY,null);
        g2.setFont(shootFont);
        g2.setColor(new Color(224, 156, 181, 255));
        g2.drawString((maxPrimaryShots - curPrimaryShots)+"/"+maxPrimaryShots, infoX+36, infoTxTy+1);
        g2.setColor(new Color(248, 172, 213, 255));
        g2.drawString((maxPrimaryShots - curPrimaryShots)+"/"+maxPrimaryShots, infoX+35, infoTxTy);
    }

    protected void lifeStatus(Graphics2D g2){
        g2.setFont(livesFont);
        g2.drawImage(lifeIcon, heartX, heartY,null);
        g2.setColor(new Color(255, 255, 255, 116));
        g2.drawString("x"+livesLeft, lifeIcon.getWidth()+1, 51);
        g2.setColor(new Color(248, 172, 213, 255));
        g2.drawString("x"+livesLeft, lifeIcon.getWidth(), 50);
    }

    protected void countDown(Graphics2D g2){
        metrics = g2.getFontMetrics(unicornFont);
        int txtX = (GameFrame.WIDTH/2 - metrics.stringWidth(playerName)) / 2;
        int txtY = ((GameFrame.HEIGHT - metrics.getHeight()*3) / 2) + metrics.getAscent();
        g2.setFont(unicornFont);
        g2.setColor(new Color(0, 0, 0, 25));
        g2.drawString(playerName, txtX+3, txtY+3);
        g2.setColor(new Color(248, 172, 213, 255));
        g2.drawString(playerName, txtX, txtY);

        String coutDownNo = "- " + (countDown-1) + " -";
        if(countDown-1 == 1) coutDownNo = "- Ready ? -";
        if(countDown-1 == 0) coutDownNo = "- FIGHT ! -";
        int countX = (GameFrame.WIDTH/2 - metrics.stringWidth(coutDownNo)) / 2;
        g2.setColor(new Color(0, 0, 0, 25));
        g2.drawString(coutDownNo, countX+3, txtY+metrics.getHeight()+3);
        if(countDown-1 > 1) g2.setColor(new Color(248, 172, 213, 255));
        if(countDown-1 == 1) g2.setColor(new Color(255, 100, 200, 255));
        if(countDown-1 == 0) g2.setColor(new Color(255, 75, 150, 255));

        g2.drawString(coutDownNo, countX, txtY+metrics.getHeight());

        if(!pause) {
            if (System.nanoTime() - timer > 1000000000) {
                countDown--;
                timer = System.nanoTime();
            }
            if (countDown == 0) {
                gameStart = true;
            }
        }
    }

    protected void pause(Graphics2D g2){
        g2.setColor(new Color(0, 0, 0, 45));
        g2.fillRect(0,0,GameFrame.WIDTH/2, GameFrame.HEIGHT);
        rolli = (rolli+1)%256;
        if(rolli == 0) rollj = (rollj+1)%6;
        switch(rollj){
            case 0:
                V++;
                break;
            case 1:
                R--;
                break;
            case 2:
                B++;
                break;
            case 3:
                V--;
                break;
            case 4:
                R++;
                break;
            case 5:
                B--;
                break;
        }
        if(R>255) R =255;
        if(R<0) R =0;
        if(V>255) V =255;
        if(V<0) V =0;
        if(B>255) B =255;
        if(B<0) B =0;
        g2.setFont(unicornFont);
        g2.setColor(new Color(0, 0, 0, 35));
        g2.drawString("[ Pause ]", 82, 238);
        g2.setColor(new Color(R, V, B));
        g2.drawString("[ Pause ]", 79, 235);
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }

    public void setBgVector(int x, int y) {
        bg.setVector(x, y);
    }

    public void setPlatforms(ArrayList<PlatformModel> platforms) {
        this.platforms = platforms;
    }

    public void setCupCakesThrow(ArrayList<CupCakeModel> cupCakesThrow) {
        this.cupCakesThrow = cupCakesThrow;
    }

    public void setCupCakesReceve(ArrayList<CupCakeModel> cupCakesReceve) {
        this.cupCakesReceve = cupCakesReceve;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
}
