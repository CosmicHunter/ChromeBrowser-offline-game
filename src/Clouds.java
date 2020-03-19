import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Clouds {
    private BufferedImage cloud_img;
    private ArrayList<cloudPosition> list_clouds;
    public Clouds(){
          cloud_img = Resource.getImageContainer("imgs/cloud.png");
          list_clouds = new ArrayList<cloudPosition>();
          cloudPosition cloud1 = new cloudPosition();
          cloud1.x = 100;
          cloud1.y = 50;
          list_clouds.add(cloud1);

        cloud1 = new cloudPosition();
        cloud1.x = 200;
        cloud1.y = 20;
        list_clouds.add(cloud1);

        cloud1 = new cloudPosition();
        cloud1.x = 300;
        cloud1.y = 80;
        list_clouds.add(cloud1);

        cloud1 = new cloudPosition();
        cloud1.x = 340;
        cloud1.y = 40;
        list_clouds.add(cloud1);

        cloud1 = new cloudPosition();
        cloud1.x = 450;
        cloud1.y = 60;
        list_clouds.add(cloud1);

        cloud1 = new cloudPosition();
        cloud1.x = 600;
        cloud1.y = 60;
        list_clouds.add(cloud1);
    }
    public void update_clouds(){
        for(cloudPosition i : list_clouds){
            i.x--;
        }
        cloudPosition firstcloud = list_clouds.get(0);
        if(firstcloud.x + cloud_img.getWidth() < 0){
             firstcloud.x = 600;
             list_clouds.remove(firstcloud);
             list_clouds.add(firstcloud);
        }
        
    }

    public void draw(Graphics g){
        for(cloudPosition i : list_clouds){
        g.drawImage(cloud_img,(int)i.x,(int)i.y,null);
        }
    }


    private class cloudPosition{
        float x;
        float y;
    }
}
