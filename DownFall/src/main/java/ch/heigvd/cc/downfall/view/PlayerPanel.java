package ch.heigvd.cc.downfall.view;

import ch.heigvd.cc.downfall.modele.CupCakeModel;
import ch.heigvd.cc.downfall.modele.PlatformModel;
import ch.heigvd.cc.downfall.modele.PlayerModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerPanel extends JPanel {

    private Background bg;

    PlayerModel player;

    ArrayList<PlatformModel> platforms;
    ArrayList<CupCakeModel> cupCakesThrow;
    ArrayList<CupCakeModel> cupCakesReceve;


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
            //g2.fillRect(player.getHitbox().x, player.getHitbox().y, player.getHitbox().width, player.getHitbox().height);
            g2.drawImage(player.getImg(), player.getPosX(), player.getPosY(), null);
        }
        for(int i=0; i< cupCakesThrow.size(); i++){
            //g2.fillRect(cupCakesThrow.get(i).getHitbox().x,cupCakesThrow.get(i).getHitbox().y,cupCakesThrow.get(i).getHitbox().width,cupCakesThrow.get(i).getHitbox().height );
            //System.out.println("Hit x = "+cupCakesThrow.get(i).getHitbox().x+"  y = "+cupCakesThrow.get(i).getHitbox().y);
            g2.drawImage(cupCakesThrow.get(i).getImg(),cupCakesThrow.get(i).getPosX(), cupCakesThrow.get(i).getPosY(), null);
            //cupCakesThrow.get(i).
        }

        g2.setColor(Color.RED);
        for(int i=0; i< cupCakesReceve.size(); i++){
            //g2.fillRect(cupCakesReceve.get(i).getHitbox().x,cupCakesReceve.get(i).getHitbox().y,cupCakesReceve.get(i).getHitbox().width,cupCakesReceve.get(i).getHitbox().height );
            g2.drawImage(cupCakesReceve.get(i).getImg(),cupCakesReceve.get(i).getPosX(), cupCakesReceve.get(i).getPosY(), null);
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
    public void setCupCakesThrow(ArrayList<CupCakeModel> cupCakesThrow){this.cupCakesThrow = cupCakesThrow;}
    public void setCupCakesReceve(ArrayList<CupCakeModel> cupCakesReceve){this.cupCakesReceve = cupCakesReceve;}

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
