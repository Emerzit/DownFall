package ch.heigvd.cc.downfall.modele;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlatformModel {
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public Rectangle getHitbox(){
        return hitbox;
    }

    public void updateX(int x){
        this.x+=x;
        hitbox.x = this.x;
    }

    public void updateY(int y){
        this.y+=y;
        hitbox.y = this.y;
    }

    private int x;
    private int y;
    private BufferedImage img;

    private Rectangle hitbox;

    public PlatformModel(int x, int y, BufferedImage img){
        this.x = x;
        this.y = y;
        this.img = img;
        hitbox = new Rectangle(this.x, this.y, img.getWidth(), img.getHeight());
    }

}
