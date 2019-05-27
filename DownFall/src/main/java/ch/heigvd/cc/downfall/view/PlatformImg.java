package ch.heigvd.cc.downfall.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlatformImg {

    public static int BLOCKSIZE = 30; // taille des block image --> 30px x 30px

    private static BufferedImage baseImage;

    static{
        try{
            baseImage = ImageIO.read(
                    PlatformImg.class.getResourceAsStream("/Tiles/plateform.png")
            );
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static BufferedImage createPlatform(int size){
        BufferedImage head = baseImage.getSubimage(0,0,BLOCKSIZE,BLOCKSIZE);
        BufferedImage body = baseImage.getSubimage(BLOCKSIZE,0,BLOCKSIZE,BLOCKSIZE);
        BufferedImage tail = baseImage.getSubimage(BLOCKSIZE*2,0,BLOCKSIZE,BLOCKSIZE);

        BufferedImage image = new BufferedImage(BLOCKSIZE*(size+2), BLOCKSIZE,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.drawImage(head,null,0,0);
        for(int i = 1; i< size+1; i++){
            g2.drawImage(body,null,BLOCKSIZE*i,0);
        }

        g2.drawImage(tail,null,BLOCKSIZE*(1+size),0);
        g2.dispose();

        return image;
    }

}
