import javax.swing.*;

public class GameWindow extends JFrame {
    private MainScreen mainScreen;

    public GameWindow(){
        super("T-Rex Game");
        this.setSize(600,175);
        this.setLocation(400,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen = new MainScreen();
        this.add(mainScreen);
        this.addKeyListener(mainScreen);
    }

    public void startGame(){
         mainScreen.startGame();
    }

    public static void main(String [] args){
          GameWindow gameWindow = new GameWindow();
          gameWindow.setVisible(true);
          gameWindow.setResizable(false);
          gameWindow.startGame();

    }

}
