package ch.heigvd.cc.downfall.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {

    private BufferedImage image;
    private double x;
    private double y;
    private double dx;
    private double dy;

    public Background(String s){
        try{
            image = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }

    public void update(){
        y = (y+dy)% image.getHeight();
        x = (x+dx) % image.getWidth();
    }

    public void draw(Graphics2D g){
        g.drawImage(image, (int)x, (int)y,null);

        if(x<0){
            g.drawImage(image,(int)x + image.getWidth(), (int)y, null);
        }
        if(x > 0){
            g.drawImage(image, (int)x-image.getWidth(), (int)y, null);
        }

        if(y<0){
            g.drawImage(image,(int)x, (int)y+image.getHeight(), null);
        }
        if(y > 0){
            g.drawImage(image, (int)x, (int)y- image.getHeight(), null);
        }

        if(x < 0 && y <0){
            g.drawImage(image, (int)x+image.getWidth(), (int)y+ image.getHeight(), null);
        }

        if(x > 0 && y >0){
            g.drawImage(image, (int)x-image.getWidth(), (int)y- image.getHeight(), null);
        }

    }
}
