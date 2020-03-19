import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public abstract class Enemy {
     public abstract Rectangle getBounds();
     public abstract void draw(Graphics g);
     public abstract void update();
     public abstract boolean isOutofBounds();
     public abstract boolean is_no_longer_threat();
     public abstract boolean is_score_updated();
     public abstract void set_score_update_status(Boolean b);
}

class EnemyControl{
    private ArrayList<Enemy> enemiesList;
    private Random rand;
    private BufferedImage obimg1;
    private BufferedImage obimg2;
    private Agent agent;
    private MainScreen ms;


    public EnemyControl(Agent a,MainScreen ms){
        obimg1 = Resource.getImageContainer("imgs/cactus1.png");
        obimg2 = Resource.getImageContainer("imgs/cactus2.png");
        enemiesList = new ArrayList<Enemy>();
        rand = new Random();
        this.agent = a;
        enemiesList.add(get_random_obstacle());
        this.ms = ms;
    }
    public void updateAll(){
        for(Enemy e : enemiesList){
            e.update();
            if(e.is_no_longer_threat() && !e.is_score_updated()){
                ms.update_game_score();
                e.set_score_update_status(true);
            }
            if(e.getBounds().intersects(agent.get_Agent_bounds())){
                agent.set_agent_status(false);
            }
        }
        Enemy fe = enemiesList.get(0);
        if(fe.isOutofBounds()){
            enemiesList.remove(fe);
            enemiesList.add(get_random_obstacle());
        }


    }
    public void drawAll(Graphics g){
        for(Enemy e : enemiesList){
             e.draw(g);
        }
    }

    public void reset(){
        enemiesList.clear();
        enemiesList.add(get_random_obstacle());
    }
    private Obstacle get_random_obstacle(){
         Obstacle ob = new Obstacle(this.agent);
         ob.setPosx(600);
         if(rand.nextBoolean()){
             ob.setObstacle_image(obimg1);
         }
         else{
             ob.setObstacle_image(obimg2);
         }
         return ob;
    }
}


/* Note : Enemy is an Abstract Class , Obstacle is a specific class that extends Enemy class. Enemy is General. Enemy Control is simply a
         Class that contains list of enemy type objects and specific controls for them. Now obstacle is class that contains obstacle cactus images
         that is a child of enemy.
 */