package ch.heigvd.cc.downfall.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CupCakeView {
    private ArrayList<BufferedImage> images;
    private Rectangle hitbox;
    private int hitboxOffsetX;
    private int hitboxOffsetY;

    public CupCakeView(){
        images = new ArrayList<BufferedImage>();
        try{
            BufferedImage baseImage = ImageIO.read(
                    PlatformImg.class.getResourceAsStream("/Weapons/cupCake.png")
            );

            for(int i = 0; i< baseImage.getWidth(); i += baseImage.getHeight() ){
                images.add(baseImage.getSubimage(i,0,baseImage.getHeight(),baseImage.getHeight()));
            }

            hitboxOffsetX = 30;
            hitboxOffsetY = 28;
            hitbox = new Rectangle(hitboxOffsetX,hitboxOffsetY,baseImage.getHeight()-hitboxOffsetX*2, baseImage.getHeight()-hitboxOffsetY*2);

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public ArrayList<BufferedImage> getCupCakeImg(){
        return images;
    }
    public Rectangle getHitbox(){return hitbox; }
    public int gethitboxOffsetX(){
        return hitboxOffsetX;
    }
    public void sethitboxOffsetX(int x){
        hitboxOffsetX = x;
    }
    public int gethitboxOffsetY(){
        return hitboxOffsetY;
    }
    public void sethitboxOffsetY(int y){ hitboxOffsetY = y;}

}
