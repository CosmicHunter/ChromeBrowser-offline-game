import util.Resource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

public class MainScreen extends JPanel implements Runnable, KeyListener {
    public static final float Gravity = 0.1f;
    public static final float baseliney = 105 ;

    public static final int game_init_mode = 0;
    public static final int game_play_mode = 1;
    public static final int game_over_mode = 2;

    private Agent agent;
    private Ground ground;
    private Clouds clouds;
    private EnemyControl enemyControl;
    private int gamemode;
    private BufferedImage gameoverlabel;
    private BufferedImage replayimg;
    private int game_score;

    private JButton bt1;

    private AudioClip scoreupSound,deadSound,jumpsound;


    private Thread t1;
    public MainScreen()  {
           t1 = new Thread(this);
           gamemode = game_init_mode;
           agent = new Agent(baseliney,Gravity,this.getWidth(),this.getHeight());
           agent.setX(50);
           agent.setY(62);
           agent.set_agent_status(true);
           ground = new Ground();
           clouds  = new Clouds();
           enemyControl = new EnemyControl(agent,this);
           gameoverlabel = Resource.getImageContainer("imgs/gameover_text.png");
           replayimg = Resource.getImageContainer("imgs/replay_button.png");
           game_score = 0;
           try{
           scoreupSound = Applet.newAudioClip(new URL("file","","imgs/scoreup.wav"));
           deadSound = Applet.newAudioClip(new URL("file","","imgs/dead.wav"));
           jumpsound = Applet.newAudioClip(new URL("file","","imgs/jump.wav"));

           }catch (Exception e){
               e.printStackTrace();
           }
    }
    public void startGame(){
        t1.start();
    }
    @Override
    public void run() {
        while (true){
            try {
                update();
                repaint();
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){
        if(gamemode==game_play_mode){
            agent.update_agent_pos();
            ground.updateLand();
            clouds.update_clouds();
            enemyControl.updateAll();
            if(agent.chk_agent_status()==false){
                gamemode = game_over_mode;
                deadSound.play();


            }
        }
    }
    public void update_game_score(){
        game_score = game_score + 5;
        if(game_score % 50 ==0 && game_score!=0)
                   scoreupSound.play();
    }

    public void paint(Graphics g){
       g.setColor(Color.decode("#f7f7f7"));
       g.fillRect(0,0,getWidth(),getHeight());
       //g.setColor(Color.RED);
       //g.drawLine(0,(int)baseliney,getWidth(),(int)baseliney);

       switch (gamemode) {
           case game_init_mode:
               agent.drawAgent(g);
               break;
           case game_play_mode:
               clouds.draw(g);
               ground.draw(g);
               agent.drawAgent(g);
               enemyControl.drawAll(g);
               g.drawString("Score : "+String.valueOf(game_score),510,20);
               break;
           case game_over_mode:
               clouds.draw(g);
               ground.draw(g);
               agent.drawAgent(g);
               enemyControl.drawAll(g);
               g.drawImage(gameoverlabel,198,28,null);
               g.drawImage(replayimg,280,48,null);
               break;
       }


    }
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Key Typed");;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gamemode== game_play_mode){
               agent.jump();
               jumpsound.play();
        }
        System.out.println("KeyPressed");;
    }

    public void resetGame(){
        agent.setX(50);
        agent.setY(62);
        agent.set_agent_status(true);
        enemyControl.reset();
        game_score = 0;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("KeyReleased");
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            if(gamemode == game_init_mode) {
                gamemode = game_play_mode;
            }
            else if (gamemode==game_over_mode){
                resetGame();
                gamemode = game_play_mode;
                System.out.println(gamemode);;

            }
        }
    }
}
