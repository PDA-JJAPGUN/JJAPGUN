package view;

import javax.swing.*;

public class GameView extends JFrame implements ScreenSize {
    private GameView gameView = this;
    public GameMap gameMap;
    public GameTitle gameTitle;

    public GameView() {
        setTitle("JJAP GUN");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        init();
    }

    public void init() {
        change("gameMap");
    }
    // 패널 바꾸기 함수
    public void change(String panelName) {
        if (panelName.equals("gameTitle")) {
            gameTitle = new GameTitle(gameView);
            getContentPane().removeAll();
            getContentPane().add(gameTitle);
            revalidate();
            repaint();
        } else if(panelName.equals("gameMap")){
            gameMap = new GameMap(gameView);
            getContentPane().removeAll();
            getContentPane().add(gameMap);
            revalidate();
            repaint();
        }
    }

}
