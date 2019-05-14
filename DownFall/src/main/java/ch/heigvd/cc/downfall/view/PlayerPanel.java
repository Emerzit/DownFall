package ch.heigvd.cc.view;

import ch.heigvd.cc.modele.PlatformModel;
import ch.heigvd.cc.modele.PlayerModel;
import ch.heigvd.cc.modele.PlayerPannelModel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {

    private Background bg;

    PlayerModel player;

    //image
    //private BufferedImage image;

    ArrayList<PlatformModel> platforms;


    public PlayerPanel(){

        this.bg = new Background("/Backgrounds/menubg.gif");
        bg.setVector(0,-0.5);

        new JPanel();
        setFocusable(true);
        requestFocus();
        //getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
        //repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        bg.update();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        bg.draw(g2);
        g2.setColor(Color.ORANGE);
        for(int i = 0; i<platforms.size(); i++ ){
            g2.drawImage(platforms.get(i).getImg(),platforms.get(i).getX(), platforms.get(i).getY(),null );
            //g2.fillRect(platforms.get(i).getHitbox().x,platforms.get(i).getHitbox().y,platforms.get(i).getHitbox().width, platforms.get(i).getHitbox().height);
        }
        if(player != null){
            g2.fillRect(player.getHitbox().x, player.getHitbox().y, player.getHitbox().width, player.getHitbox().height);
            g2.drawImage(player.getImg(), player.getPosX(), player.getPosY(), null);
        }
    }

    public void setBg(Background bg){
        this.bg = bg;
    }

    public void setBgVector(int x, int y){
        bg.setVector(x,y);
    }

    public void setPlatforms(ArrayList<PlatformModel> platforms){
        this.platforms = platforms;
    }

    public void init(){};

    public void update(){
        bg.update();
        repaint();
    };

    public void setPlayer(PlayerModel player){
        this.player = player;
    }
/*
    public void keyPressed(int k){};
    public void keyReleased(int k){};
*/
}
