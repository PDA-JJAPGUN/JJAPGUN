package view;

import javax.swing.*;

public class GameView extends JFrame implements ScreenSize {
    private GameView gameView = this;
    public boolean isgame;
    public GamePanel gamePanel;
    public GameView() {
        setTitle("JJAP GUN");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void change(String panelName){

    }
}
