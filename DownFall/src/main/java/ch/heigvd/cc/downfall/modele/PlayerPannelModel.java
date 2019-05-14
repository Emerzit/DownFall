package ch.heigvd.cc.modele;

import ch.heigvd.cc.view.Background;
import ch.heigvd.cc.view.GameFrame;
import ch.heigvd.cc.view.PlatformImg;
import ch.heigvd.cc.view.PlayerPanel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class PlayerPannelModel {
    PlayerPanel pannel;
    Background bg;
    PlayerModel player;

    Random rand;

    ArrayList<PlatformModel> platforms;

    int distBetweenPlatforms;
    int maxPlatformLength;

    int yspeed;

    public PlayerPannelModel(){
        rand = new Random();
        distBetweenPlatforms = 100;
        yspeed = 1;
        bg = new Background("/Backgrounds/menubg.gif");
        bg.setVector(0, -(yspeed/4.0));
        pannel = new PlayerPanel();
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
        int x = (rand.nextInt(GameFrame.WIDTH / 2)- (PlatformImg.BLOCKSIZE*size));
        platforms.add(new PlatformModel(x, y, PlatformImg.createPlatform(size)));
    }

    void updatePlatforms(){
        for(int i=0; i<platforms.size(); i++){
            platforms.get(i).updateY(yspeed);
        }
        if(platforms.get(platforms.size()-1).getY() > distBetweenPlatforms){
            addPlateform(-PlatformImg.BLOCKSIZE);
            //System.out.println( platforms.get(platforms.size()-1).getY() + " > " + distBetweenPlatforms);
        }

        if(platforms.get(0).getY() > GameFrame.HEIGHT){
            platforms.remove(0);
        }

    }

    public void init(){
        initPlatforms();
        pannel.setPlatforms(platforms);
    }

    public void update(){
        detectCollision();
        updatePlatforms();

        player.update();


        draw();
    }

    public void detectCollision(){
        for(int i = 0; i<platforms.size(); i++ ){
            if(platforms.get(i).getHitbox().intersects(player.getHitbox()) && player.getHitbox().y+player.getHitbox().height < platforms.get(i).getHitbox().y+10){
                player.setCollision(true);
                player.setPosY(platforms.get(i).getY()-platforms.get(i).getImg().getHeight());
                return;
            }
        }
        player.setCollision(false);
    }

    public void draw(){
        pannel.update();
    }

    public PlayerPanel getPlayerPannel(){
        return pannel;
    }

    public void setPlayerKeyMap(int up, int down, int left, int right){
        player.setPlayerKeyMap(up, down, left, right);
    }

    public void KeyPress(int key){
        player.KeyPress(key);
    }

    public void setKeyPressedList(HashSet keysPressed){
        player.setKeyPressedList(keysPressed);
    }

}
