package view;

import javax.swing.*;

public class GameView extends JFrame implements ScreenSize {
    private GameView gameView = this;

    public GameView() {
        setTitle("JJAP GUN");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
