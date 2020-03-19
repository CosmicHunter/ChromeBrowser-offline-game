import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle extends  Enemy{
    private BufferedImage obstacle_image;
    private int posx,posy;
    private Rectangle rect;
    private Agent agent;
    private boolean score_update_status;
    public Obstacle(Agent agent){
          obstacle_image = Resource.getImageContainer("imgs/cactus1.png");
          posx = 200;
          posy = 65;
          rect = new Rectangle();
          this.agent = agent;
          score_update_status = false;
    }

    public void setObstacle_image(BufferedImage obstacle_image) {
        this.obstacle_image = obstacle_image;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public void update(){
        posx  = posx -2;
        rect.x = posx;
        rect.y = posy;
        rect.width = obstacle_image.getWidth();
        rect.height = obstacle_image.getHeight();
    }

    public Rectangle getBounds(){
        return rect;
    }
    public boolean isOutofBounds(){
          if(this.posx+obstacle_image.getWidth()<0)
              return true;
          return false;
    }

    @Override
    public boolean is_no_longer_threat() {
        if(agent.getX() > posx){
            return true;
        }
        return false;
    }

    @Override
    public boolean is_score_updated() {
        return score_update_status;
    }

    @Override
    public void set_score_update_status(Boolean b) {
        score_update_status = b;
    }

    public void draw(Graphics g){
        g.drawImage(obstacle_image,posx,posy,null);
    }
}
