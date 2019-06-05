package ch.heigvd.cc.downfall.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerView {
    private ArrayList<BufferedImage> sprite;
    private Rectangle hitbox;
    private int hitboxOffsetX;
    private int hitboxOffsetY;

    public PlayerView(String imgPath){
        sprite = new ArrayList<BufferedImage>();
        try{
            BufferedImage baseImage = ImageIO.read(ch.heigvd.cc.downfall.view.PlatformImg.class.getResourceAsStream(imgPath));
            for(int i = 0; i< 2; i++){
                sprite.add(baseImage.getSubimage(i*baseImage.getWidth()/2,0,baseImage.getWidth()/2 ,baseImage.getHeight()));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        hitboxOffsetX = 5;
        hitboxOffsetY = 0;
        hitbox = new Rectangle(hitboxOffsetX,hitboxOffsetY,sprite.get(0).getWidth()-hitboxOffsetX*2, sprite.get(0).getHeight());
    }

    public ArrayList<BufferedImage> getPlayerImg(){
        return sprite;
    }
    public Rectangle getHitbox(){return hitbox; }
    public int gethitboxOffsetX(){
        return hitboxOffsetX;
    }
    public int gethitboxOffsetY(){
        return hitboxOffsetY;
    }
}
