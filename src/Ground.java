import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Ground {
    private BufferedImage ground1,ground2,ground3;
    private ArrayList<data> groundImageData;
    private Random rand;
    public Ground(){
        rand = new Random();
        ground1 = Resource.getImageContainer("imgs/land1.png");
        ground2 = Resource.getImageContainer("imgs/land2.png");
        ground3 = Resource.getImageContainer("imgs/land3.png");
        groundImageData = new ArrayList<data>();
        int noofgrnd = 600 / ground1.getWidth() +2;
        for(int i=0;i<noofgrnd;i++){
            data image = new data();
            image.x = (int)(i*ground1.getWidth());
            image.img = give_random_groundImages();
            groundImageData.add(image);
        }
    }
    public void updateLand(){
        for(data i : groundImageData){
            i.x-=2;
        }

        data firstgrndimg = groundImageData.get(0);
        if(firstgrndimg.x+ground1.getWidth()<0){
            firstgrndimg.x = groundImageData.get(groundImageData.size()-1).x + ground1.getWidth();
            groundImageData.add(firstgrndimg);
            groundImageData.remove(0);
        }
    }

    public void draw(Graphics g){
        for(data i : groundImageData){
            g.drawImage(i.img,i.x,(int) MainScreen.baseliney -20,null);
        }

    }

    private BufferedImage give_random_groundImages(){
        int index  = rand.nextInt(5);
        switch (index){
            case 0: return ground1;
            case 1: return ground3;
            default: return ground2;
        }
    }
    private class data{
        int x;
        BufferedImage img;
    }

}


