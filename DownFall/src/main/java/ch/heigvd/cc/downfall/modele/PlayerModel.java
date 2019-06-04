package ch.heigvd.cc.downfall.modele;

import ch.heigvd.cc.downfall.view.GameFrame;
import ch.heigvd.cc.downfall.view.PlayerView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

public class PlayerModel {

    // Player Keys
    private int up;
    private int down;
    private int left;
    private int right;
    private int shoot;

    HashSet<Integer> keysPressed;

    public void setPlayerKeyMap(int up, int down, int left, int right, int shoot){
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.shoot = shoot;
    }

    private Boolean shooting = false;
    public Boolean isShooting(){
        return  shooting;
    }
    ArrayList<CupCakeModel> cupCakesThrow;
    ArrayList<CupCakeModel> cupCakesReceve;

    /*public void setCupCakesThrow(ArrayList<CupCakeModel>  cupCakesThrow){
        this.cupCakesThrow = cupCakesThrow;
    }

    public void setCupCakesReceve(ArrayList<CupCakeModel>  cupCakesReceve){
        this.cupCakesThrow = cupCakesReceve;
    }*/

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

    public void setVectX(int vectX){
        this.vectX = vectX;
    }
    public void setVectY(int vectY){
        this.vectY = vectY;
    }

    private int startPosY = -61;
    private int speedy;

    private Boolean okToShoot = false;
    public Boolean getOkToShoot(){
        return okToShoot;
    }

    public void setSpeedy(int speed){
        speedy = speed;
    }
    private Boolean collision = false;

    private PlayerView playerImg;

    private Boolean goright = false;
    public Boolean isGoingRight(){
        return goright;
    }
    //long startTime = System.nanoTime();

    public PlayerModel(String img){
        keysPressed = new HashSet<Integer>();
        playerImg = new PlayerView(img);

        posX = (GameFrame.WIDTH/4)-(playerImg.getPlayerImg().get(0).getWidth()/2);
        posY = startPosY;

        maxSpeed = 300;
        accelereationX = 50;
        accelereationY = 450;
        gravity = 10;
        vectX = 0;
        vectY = 0;

        playerImg.getHitbox().x = posX;
        playerImg.getHitbox().y = posY;

        speedy = 1;

    }

    public BufferedImage getImg(){
        if(goright){
            return playerImg.getPlayerImg().get(0);
        }else{
            return playerImg.getPlayerImg().get(1);
        }

    }
    public Rectangle getHitbox(){return playerImg.getHitbox(); }

    private void updateHitbox(){
        playerImg.getHitbox().x = posX+playerImg.gethitboxOffsetX();
        playerImg.getHitbox().y = posY+playerImg.gethitboxOffsetY();
    }

    public void update(){
        if(vectX > 0) goright = true;
        if(vectX < 0) goright = false;
        if(keysPressed.contains(up)){
            jump();
        }
        if(keysPressed.contains(left)){
            goLeft();
        }
        if(keysPressed.contains(right)){
            goRight();
        }
        if(keysPressed.contains(shoot)){
            if(okToShoot){
                shooting = true;
            }else{
                shooting = false;
            }
            okToShoot = false;
        }else{
            shooting = false;
            okToShoot = true;
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
        }else{
            vectX = (int) ((double)vectX * 0.95);
        }
        posY += vectY / 60;
        posY += speedy;
        if(posX<0){
            posX = 0;
            vectX *= -1;
        }
        if(posX+playerImg.getPlayerImg().get(0).getWidth() > GameFrame.WIDTH/2){
            posX = GameFrame.WIDTH/2 - playerImg.getPlayerImg().get(0).getWidth();
            vectX *= -1;
        }
        posX += vectX / 60;
        updateHitbox();
    }

    public void resetPlayer(){
        posX = (GameFrame.WIDTH/4)-(playerImg.getPlayerImg().get(0).getWidth()/2);
        posY = startPosY;
        vectY = 0;
        vectX = 0;
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

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }
}
