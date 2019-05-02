package ch.heigvd.cc.downfall;

public class Player {
    String name;
    double posX;
    double posY;

    Player(String name, double posX, double posY) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
}
