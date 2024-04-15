package view;

import javax.swing.*;

public class GameFrame extends JFrame implements ScreenSize {
    private GameFrame gameFrame = this;

    public GameFrame() {
    public boolean isgame;
    public GamePanel gamePanel;
    public GameFrame() {
        setTitle("JJAP GUN");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        init();
    }

    public void init() {
        change("gameTitle");
    }
    // 패널 바꾸기 함수
    public void change(String panelName) {
        if (panelName.equals("gameTitle")) {
            GameTitle gameTitle = new GameTitle(gameFrame);
            getContentPane().removeAll();
            getContentPane().add(gameTitle);
            revalidate();
            repaint();
        }
    }

}