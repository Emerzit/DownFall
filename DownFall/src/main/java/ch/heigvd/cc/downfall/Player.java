package ch.heigvd.cc.downfall;

public class Player {
    String name;
    double posX;
    double posY;
    double xSpeed;
    double ySpeed;

    Player(String name, double posX, double posY) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        xSpeed = 0;
        ySpeed = 0;
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

    public double getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
        posX+=xSpeed;
        System.out.println(name+" xSpeed: " + xSpeed);
    }

    public double getySpeed() {
        return ySpeed;
    }

    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
        posY+=ySpeed;
        System.out.println(name+" ySpeed: " + ySpeed);
    }
}
