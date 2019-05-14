package ch.heigvd.cc.modele;

import ch.heigvd.cc.view.GameFrame;
import ch.heigvd.cc.view.PlayerView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public class PlayerModel {

    // Player Keys
    private int up;
    private int down;
    private int left;
    private int right;

    HashSet<Integer> keysPressed;

    public void setPlayerKeyMap(int up, int down, int left, int right){
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public void KeyPress(int key){
        if(key == up){
            jump();
            //System.out.println("UP");
        }
        if(key == down){
            //System.out.println("DOWN");
        }
        if(key == left){
            goLeft();
            //System.out.println("LEFT");
        }
        if(key == right){
            goRight();
            //System.out.println("RIGHT");
        }
    }

    private int posX;
    private int posY;

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getAccelereationX() {
        return accelereationX;
    }

    public void setAccelereationX(int accelereationX) {
        this.accelereationX = accelereationX;
    }

    public int getAccelereationY() {
        return accelereationY;
    }

    public void setAccelereationY(int accelereationY) {
        this.accelereationY = accelereationY;
    }

    private int maxSpeed;
    private int accelereationX;
    private int accelereationY;

    private int gravity;

    private int vectX;
    private int vectY;

    private int startPosY = -61;

    private Boolean collision = false;

    private PlayerView playerImg;

    PlayerModel(String img){
        keysPressed = new HashSet<Integer>();
        playerImg = new PlayerView(img);

        posX = (GameFrame.WIDTH/4)-(playerImg.getPlayerImg().getWidth()/2);
        posY = startPosY;

        maxSpeed = 300;
        accelereationX = 50;
        accelereationY = 450;
        gravity = 10;
        vectX = 0;
        vectY = 0;

        playerImg.getHitbox().x = posX;
        playerImg.getHitbox().y = posY;
    }

    public BufferedImage getImg(){
        return playerImg.getPlayerImg();
    }
    public Rectangle getHitbox(){return playerImg.getHitbox(); }

    private void updateHitbox(){
        playerImg.getHitbox().x = posX+playerImg.gethitboxOffsetX();
        playerImg.getHitbox().y = posY+playerImg.gethitboxOffsetY();
    }

    public void update(){
        if(keysPressed.contains(up)){
            jump();
        }
        if(keysPressed.contains(left)){
            goLeft();
        }
        if(keysPressed.contains(right)){
            goRight();
        }
        if( !keysPressed.contains(right) || !keysPressed.contains(left)){
            vectX = (int) ((double)vectX * 0.95);
        }
        if(!collision) {
            if (vectY < maxSpeed) {
                vectY += gravity;

                if (vectY > maxSpeed) {
                    vectY = maxSpeed;
                }
            }
            //posY += vectY / 60;
        }else{
            vectX = (int) ((double)vectX * 0.95);
        }
        posY += vectY / 60;
        posY++;
        if(posX<=0){
            posX = 0;
            vectX *= -1;
        }
        if(posX+playerImg.getPlayerImg().getWidth() >= GameFrame.WIDTH/2){
            posX = GameFrame.WIDTH/2 - playerImg.getPlayerImg().getWidth();
            vectX *= -1;
        }
        posX += vectX / 60;

        // reset player position when fallen out of frame
        if(posY > GameFrame.HEIGHT-playerImg.getPlayerImg().getHeight()){
            posX = (GameFrame.WIDTH/4)-(playerImg.getPlayerImg().getWidth()/2);
            posY = startPosY;
            vectY = 0;
            vectX = 0;
        }
        updateHitbox();
    }

    void jump(){
        if(collision){
            vectY = - accelereationY;
        }
    }

    void goLeft(){
        if(vectX < 0){
            vectX -= accelereationX;
        }else{
            vectX -= accelereationX*2;
        }

        if(vectX < -maxSpeed){
            vectX = -maxSpeed;
        }
    }

    void goRight(){
        if(vectX > 0){
            vectX += accelereationX;
        }else{
            vectX += accelereationX*2;
        }

        if(vectX > maxSpeed){
            vectX = maxSpeed;
        }
    }


    public void setCollision(Boolean c){
        collision = c;
    }

    public void setKeyPressedList(HashSet keysPressed){
        this.keysPressed = keysPressed;
    }

}
