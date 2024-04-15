package view;

import javax.swing.*;


public class GameFrame extends JFrame implements ScreenSize {
    private GameFrame gameFrame = this;
    public GameMap gameMap;
    public GameStart gameStart;
    public GameEnd gameEnd;
    public boolean isgame;

    public GameFrame() {
        setTitle("JJAP GUN");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        init();
    }

    public void init() {
        change(Panel.GAME_START.name());
//        change(Panel.GAME_END.name());
    }

    // 패널 바꾸기 함수
    public void change(String panelName) {

        getContentPane().removeAll();

        switch (Panel.valueOf(panelName)) {
            case GAME_START:
                gameStart = new GameStart(gameFrame);
                getContentPane().add(gameStart);
                break;
            case GAME_MAP:
                gameMap = new GameMap(gameFrame);
                getContentPane().add(gameMap);
                break;
            case GAME_END:
                gameEnd = new GameEnd(gameFrame, true);
                getContentPane().add(gameEnd);
                break;
        }

        revalidate();
        repaint();
    }

}
