package ch.heigvd.cc.downfall.modele;

import ch.heigvd.cc.downfall.view.CupCakeView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CupCakeModel {

    private int posX;
    private int posY;

    public int getPosX() {return posX;}
    public int getPosY() {return posY;}

    private int maxSpeed;

    private int gravity;
    private Boolean idExploding = false;
    public Boolean getIsExploding(){
        return idExploding;
    }

    private int vectX;
    private int vectY;

    private int explosionForce = 800;
    public int getExplosionForce(){
        return explosionForce;
    }

    private Boolean leftOrRight;
    public Boolean getLeftOrRight(){
        return leftOrRight;
    }

    public int getVectY(){
        return vectY;
    }
    public void setVectY(int vectY){
        this.vectY = vectY;
    }

    public int getOffsetX(){
        return cupCakeImg.gethitboxOffsetX();
    }

    private int animFrameRate;
    private int animRatePos;

    private Boolean collision = false;
    private Boolean isDestroyed = false;

    private CupCakeView cupCakeImg;
    int imgpos;

    private Boolean copied = false;

    public Boolean isCopied(){
        return copied;
    }

    public void setCopied(Boolean copied){
        this.copied = copied;
    }

    public CupCakeModel(Boolean leftOrRight, int x, int y){
        this.leftOrRight = leftOrRight;
        animFrameRate = 1;
        animRatePos = 0;

        cupCakeImg = new CupCakeView();
        imgpos = 0;

        posX = x;
        posY = y;

        maxSpeed = 300;
        gravity = 5;

        if(this.leftOrRight){
            vectX = 400;
        }else{
            vectX = -400;
        }

        vectY = -50;

        cupCakeImg.getHitbox().x = posX+cupCakeImg.gethitboxOffsetX();
        cupCakeImg.getHitbox().y = posY+cupCakeImg.gethitboxOffsetY();
    }

    public BufferedImage getImg(){
        return cupCakeImg.getCupCakeImg().get(imgpos);
    }
    public Rectangle getHitbox(){return cupCakeImg.getHitbox(); }

    private void updateHitbox(){
        cupCakeImg.getHitbox().x = posX+cupCakeImg.gethitboxOffsetX();
        cupCakeImg.getHitbox().y = posY+cupCakeImg.gethitboxOffsetY();
    }

    public void update(){
        if(!collision) {
            if (vectY < maxSpeed) {
                vectY += gravity;

                if (vectY > maxSpeed) {
                    vectY = maxSpeed;
                }
            }

            posX += vectX / 60;
            posY += vectY / 60;
        }else if(!isDestroyed){
            idExploding = true;
            animRatePos = (animRatePos+1)%animFrameRate;
            if(animRatePos == 0){
                imgpos = (imgpos+1)% cupCakeImg.getCupCakeImg().size();
            }
            if(imgpos == cupCakeImg.getCupCakeImg().size()-1){
                isDestroyed = true;
            }
        }

        updateHitbox();
    }

    public void setCollision(Boolean c){
        collision = c;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getGravity() {
        return gravity;
    }

    public int getVectX() {
        return vectX;
    }

    CupCakeView getCupCakeImg() {
        return cupCakeImg;
    }
}
