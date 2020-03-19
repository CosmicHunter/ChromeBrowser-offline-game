package util;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator {
     private ArrayList<BufferedImage> imageFrames;
     private int imgframeindex;
     private int threshold_delay_frameChange;
     private long previousTime;

     public Animator(int thresholdtime){
         threshold_delay_frameChange = thresholdtime;
         imageFrames = new ArrayList<BufferedImage>();
     }
     public void update_image(){
         if(System.currentTimeMillis() - previousTime >= threshold_delay_frameChange){
             imgframeindex++;
             if(imgframeindex>imageFrames.size()-1){
                 imgframeindex=0;
             }
             previousTime = System.currentTimeMillis();
         }
     }
     public void add_image_frame(BufferedImage img){
          imageFrames.add(img);
     }

     public BufferedImage get_image_frame(){
         if(imageFrames.size()>0){
             return imageFrames.get(imgframeindex);
         }
         return null;
     }

}
