package ch.heigvd.cc.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerView {
    private BufferedImage image;
    private Rectangle hitbox;
    private int hitboxOffsetX;
    private int hitboxOffsetY;

    public PlayerView(String imgPath){
        try{
            image = ImageIO.read(
                    PlatformImg.class.getResourceAsStream(imgPath)
            );

        }catch(Exception e){
            e.printStackTrace();
        }
        hitboxOffsetX = 5;
        hitboxOffsetY = 0;
        hitbox = new Rectangle(hitboxOffsetX,0,image.getWidth()-hitboxOffsetX*2, image.getHeight());
    }

    public BufferedImage getPlayerImg(){
        return image;
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
    public void sethitboxOffsetY(int y){
        hitboxOffsetY = y;
    }
}
