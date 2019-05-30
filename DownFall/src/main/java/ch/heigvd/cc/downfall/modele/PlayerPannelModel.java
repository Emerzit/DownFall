package ch.heigvd.cc.downfall.modele;

import ch.heigvd.cc.downfall.view.Background;
import ch.heigvd.cc.downfall.view.GameFrame;
import ch.heigvd.cc.downfall.view.PlatformImg;
import ch.heigvd.cc.downfall.view.PlayerPanel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import static java.lang.Math.abs;

public class PlayerPannelModel extends PannelModel {
    PlayerPanel pannel;
    Background bg;
    PlayerModel player;

    Random rand;

    ArrayList<PlatformModel> platforms;
    ArrayList<CupCakeModel> cupCakesThrow;
    ArrayList<CupCakeModel> cupCakesReceve;
    ArrayList<CupCakeModel> cupCakesOther;

    public void setCupCakesOther(ArrayList<CupCakeModel> cupCakesOther){
        this.cupCakesOther = cupCakesOther;
    }
    public void setCupCakesThrow(ArrayList<CupCakeModel> cupCakesThrow){
        this.cupCakesThrow = cupCakesThrow;
    }

    int distBetweenPlatforms;
    int maxPlatformLength;

    int yspeed;

    long startTime = System.nanoTime();

    private int maxShots = 5;
    private int currentShots = 0;

    private Boolean gameStart = false;
    private Boolean pause = false;

    public void setPause(Boolean pause){
        this.pause = pause;
        pannel.setPause(pause);
    }

    public PlayerPannelModel(String playerName){

        cupCakesThrow = new ArrayList<CupCakeModel>();
        cupCakesReceve = new ArrayList<CupCakeModel>();
        cupCakesOther = new ArrayList<CupCakeModel>();

        rand = new Random();
        distBetweenPlatforms = 100;
        yspeed = 1;
        bg = new Background("/Backgrounds/menubg.gif");
        bg.setVector(0, 0);
        pannel = new PlayerPanel();
        pannel.setPlayerName(playerName);
        pannel.setBg(bg);
        platforms = new ArrayList<PlatformModel>();
        maxPlatformLength = 5;
        player = new PlayerModel("/Player/unicorn1.png");
        pannel.setPlayer(player);
    }

    void initPlatforms(){
        for(int i=PlatformImg.BLOCKSIZE + distBetweenPlatforms; i<GameFrame.HEIGHT; i+=distBetweenPlatforms){
            addPlateform(i-PlatformImg.BLOCKSIZE);
        }
    }

    void addPlateform(int y){
        int size = rand.nextInt(maxPlatformLength+1);
        int x = (rand.nextInt(GameFrame.WIDTH / 2)- ( PlatformImg.BLOCKSIZE * (size+1)));
        platforms.add(new PlatformModel(x, y, PlatformImg.createPlatform(size)));
    }

    void updatePlatforms(){
        for(int i=0; i<platforms.size(); i++){
            platforms.get(i).updateY(yspeed);
        }
        if(platforms.get(platforms.size()-1).getY() > distBetweenPlatforms){
            addPlateform(-PlatformImg.BLOCKSIZE);
        }

        if(platforms.get(0).getY() > GameFrame.HEIGHT){
            platforms.remove(0);
        }

    }

    public void init(){
        initPlatforms();
        pannel.setPlatforms(platforms);
        pannel.setCupCakesReceve(cupCakesReceve);
        pannel.setCupCakesThrow(cupCakesThrow);
        pannel.setGameStart(gameStart);
    }

    public void update(){
        if(pause){
            bg.setVector(0,0);
            return;}
        pannel.setLivesLeft(player.getLivesLeft());
        if(!gameStart) gameStart = pannel.getGameStart();
        if(!gameStart) return;

        if(player.getPosY() > GameFrame.HEIGHT - player.getImg().getHeight() ){
            player.resetPlayer();
            destroyCupCakes();
            currentShots = 0;
        }

        if(player.isShooting() && currentShots < maxShots){
            shoot();
            currentShots++;
        }
        if(System.nanoTime() - startTime > 1000000000 && currentShots > 0){
            currentShots--;
            startTime = System.nanoTime();
            System.out.println(currentShots);
        }
        pannel.setCurPrimaryShots(currentShots);
        pannel.setMaxPrimaryShots(maxShots);
        detectCollision();
        updatePlatforms();

        player.update();
        updateCupCakes();
        if(player.getPosY() < 100){
            yspeed = (100-player.getPosY())/30 + 1;
        }else{
            yspeed = 1;
        }
        player.setSpeedy(yspeed);
        bg.setVector(0, (yspeed/4.0));
    }

    private void destroyCupCakes() {
        for(int i = cupCakesOther.size(); i>0; i--){
            cupCakesOther.get(i-1).setCollision(true);
        }
        for(int i = cupCakesReceve.size(); i>0; i--){
            cupCakesReceve.get(i-1).setCollision(true);
        }
        for(int i = cupCakesThrow.size(); i>0; i--){
            cupCakesThrow.get(i-1).setCollision(true);
        }
    }

    void detectCollision(){
        for(int i = 0; i<platforms.size(); i++ ){
            if(platforms.get(i).getHitbox().intersects(player.getHitbox()) && player.getHitbox().y+player.getHitbox().height < platforms.get(i).getHitbox().y+10){
                player.setCollision(true);
                player.setPosY(platforms.get(i).getY()-platforms.get(i).getImg().getHeight());
                return;
            }
        }
        player.setCollision(false);
    }

    void updateCupCakes(){

        for(int i=0; i<cupCakesOther.size(); i++ ){
            if(cupCakesOther.get(i).getPosX() < 0 && !cupCakesOther.get(i).isCopied()){
                CupCakeModel sh = new CupCakeModel(false,
                        cupCakesOther.get(i).getPosX()+ GameFrame.WIDTH / 2,
                        cupCakesOther.get(i).getPosY());
                sh.setVectY(cupCakesOther.get(i).getVectY());
                cupCakesReceve.add(sh);
                cupCakesOther.get(i).setCopied(true);
            }
            if(cupCakesOther.get(i).getPosX() + cupCakesOther.get(i).getImg().getWidth() > GameFrame.WIDTH / 2 && !cupCakesOther.get(i).isCopied()){
                CupCakeModel sh = new CupCakeModel(true,
                        cupCakesOther.get(i).getPosX() - GameFrame.WIDTH / 2,
                        cupCakesOther.get(i).getPosY());
                sh.setVectY(cupCakesOther.get(i).getVectY());
                cupCakesReceve.add(sh);
                cupCakesOther.get(i).setCopied(true);
            }
        }

        for(int i =0;i<cupCakesReceve.size(); i++){
            if(cupCakesReceve.get(i).isDestroyed() ||
                    cupCakesReceve.get(i).getPosX() + cupCakesReceve.get(i).getImg().getWidth() < 0 ||
                    cupCakesReceve.get(i).getPosX() - cupCakesReceve.get(i).getImg().getWidth() > GameFrame.WIDTH / 2){
                cupCakesReceve.remove(i);
                i--;
            }
        }
        for(int i =0;i<cupCakesThrow.size(); i++) {
            if (cupCakesThrow.get(i).isDestroyed() ||
                    cupCakesThrow.get(i).getPosX() + cupCakesThrow.get(i).getImg().getWidth() < 0 ||
                    cupCakesThrow.get(i).getPosX() - cupCakesThrow.get(i).getImg().getWidth() > GameFrame.WIDTH / 2
            ) {
                cupCakesThrow.remove(i);
                i--;
            }
        }

        for(int i = 0; i<cupCakesThrow.size();i++){
            cupCakesThrow.get(i).update();
        }
        for(int i = 0; i<cupCakesReceve.size();i++){
            if(cupCakesReceve.get(i).getPosX() + cupCakesReceve.get(i).getOffsetX()<=0 && !cupCakesReceve.get(i).getLeftOrRight()){
                cupCakesReceve.get(i).setCollision(true);
            }
            if(cupCakesReceve.get(i).getPosX() + cupCakesReceve.get(i).getImg().getWidth() - cupCakesReceve.get(i).getOffsetX()  >=  GameFrame.WIDTH / 2 && cupCakesReceve.get(i).getLeftOrRight()){
                cupCakesReceve.get(i).setCollision(true);
            }
            if(cupCakesReceve.get(i).getHitbox().intersects(player.getHitbox()) && !cupCakesReceve.get(i).getIsExploding()){
                cupCakesReceve.get(i).setCollision(true);
                double distX = (player.getPosX() + player.getImg().getWidth()/2) - (cupCakesReceve.get(i).getPosX() + cupCakesReceve.get(i).getImg().getWidth()/2);
                double distY = (player.getPosY() + player.getImg().getHeight()/2) - (cupCakesReceve.get(i).getPosY() + cupCakesReceve.get(i).getImg().getHeight()/2);
                double explX = ( distX/(abs(distX)+abs(distY)))*cupCakesReceve.get(i).getExplosionForce();
                double explY = (distY/(abs(distX)+abs(distY)))*cupCakesReceve.get(i).getExplosionForce();
                player.setVectX((int)explX);
                player.setVectY((int)explY);
            }
            cupCakesReceve.get(i).update();
        }
    }

    void shoot(){
        int x = player.getPosX() - player.getImg().getWidth();
        if(player.isGoingRight()){
            x =player.getPosX();
        }
        int y = player.getPosY() - player.getImg().getHeight();
        this.cupCakesThrow.add(new CupCakeModel(player.isGoingRight(),x,y));
        System.out.println("--> shoot : "+ cupCakesThrow.size()+ " receve : "+cupCakesReceve.size() + " Other : "+ cupCakesOther.size());
    }
/*
    public void draw(){
        pannel.update();
    }*/

    public PlayerPanel getPlayerPannel(){
        return pannel;
    }

    public void setPlayerKeyMap(int up, int down, int left, int right, int shoot){
        player.setPlayerKeyMap(up, down, left, right, shoot);
    }

    public void setKeyPressedList(HashSet keysPressed){
        player.setKeyPressedList(keysPressed);
    }

    public void repaint(){
        pannel.repaint();
    }

}
