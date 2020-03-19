import util.Animator;
import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;


public class Agent {
    private float x=0;
    private float y=0;
    private float vx=0;
    private float vy=0;
    private float baseliney;
    private float Gravity;
    private int width,height;
    private Rectangle rectange_of_agent;
    private boolean is_agent_online;

    private Animator animation;
    public Agent(float baseliney,float gravity,int width,int height){
        this.baseliney = baseliney;
        this.Gravity = gravity;
        this.width  = width;
        this.height = height;
        this.animation = new Animator(100);
        this.rectange_of_agent = new Rectangle();
        this.animation.add_image_frame(Resource.getImageContainer("imgs/main-character1.png"));
        this.animation.add_image_frame(Resource.getImageContainer("imgs/main-character2.png"));
        is_agent_online = true;
    }
    public boolean chk_agent_status(){
        return is_agent_online;
    }
    public void set_agent_status(boolean b){
        is_agent_online = b;
    }


    public void update_agent_pos(){
        animation.update_image();
        if(y>=baseliney-this.animation.get_image_frame().getHeight()){
            y=baseliney-this.animation.get_image_frame().getHeight();
            vy = 0;
        }
        else{
            vy+=Gravity;
            y = y + vy;
        }
        rectange_of_agent.x = (int)x;
        rectange_of_agent.y = (int)y;
        rectange_of_agent.width = animation.get_image_frame().getWidth();
        rectange_of_agent.height= animation.get_image_frame().getHeight();
    }
    public Rectangle get_Agent_bounds(){
        return rectange_of_agent;
    }

    public void drawAgent(Graphics g){
        g.setColor(Color.BLACK);
        g.drawRect((int)x,(int)y,this.animation.get_image_frame().getWidth(),this.animation.get_image_frame().getHeight());
        g.drawImage(this.animation.get_image_frame(),(int)x,(int)y,null);
    }

    public void jump(){
        vy = -4;
        y = y + vy;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }
}
