package ch.heigvd.cc.downfall;

import java.util.LinkedList;

public class PlayerEnvir {
    LinkedList<Plateform> platforms;
    Player player;

    PlayerEnvir(Player player) {
        this.player = player;
    }

    public String getName() {
        return player.getName();
    }

    public void setXSpeed(double xSpeed) {
        player.setxSpeed(xSpeed);
    }

    public void setYSpeed(double ySpeed) {
        player.setySpeed(ySpeed);
    }

    public double getPosX() {
        return player.getPosX();
    }

    public double getPosY() {
        return player.getPosY();
    }
}
