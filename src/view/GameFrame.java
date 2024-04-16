package view;

import controller.GameController;
import objects.player.Player;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GameFrame extends JFrame implements ScreenSize {
    private GameFrame gameFrame = this;
    public GameMap gameMap;
    public GameStart gameStart;
    public GameEnd gameEnd;
    public GameRank gameRank;
    public SelectPlayer selectPlayer;
    public Player player;
    public boolean isgame;

    public GameFrame() {

        setFocusable(true);
        init();
        setting();
        listener();

        setVisible(true);
        GameController.getInstance().setGameFrame(this);
    }

    public void init() {
        change(Panel.GAME_START.name());
    }

    public void setting() {
        setTitle("JJAPGUN");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
                gameEnd = new GameEnd(gameFrame);
                getContentPane().add(gameEnd);
                System.out.println("sssssssssssssss");
                break;
            case GAME_RANK:
                gameRank = new GameRank(gameFrame);
                getContentPane().add(gameRank);
                break;
            case GAME_SELECT_PLAYER:
                selectPlayer = new SelectPlayer(gameFrame);
                getContentPane().add(selectPlayer);
                break;
        }

        revalidate();
        repaint();
    }

    public void listener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        player.setAttack(true);
                        break;
                    case KeyEvent.VK_UP:
                        player.setUp(true);
                        break;
                    case KeyEvent.VK_DOWN:
                        player.setDown(true);
                        break;
                    case KeyEvent.VK_LEFT:
                        player.setLeft(true);
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.setRight(true);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_1:
                        player.setWeaponLevelUp(false);
                        break;
                    case KeyEvent.VK_SPACE:
                        player.setAttack(false);
                        break;
                    case KeyEvent.VK_UP:
                        player.setUp(false);
                        break;
                    case KeyEvent.VK_DOWN:
                        player.setDown(false);
                        break;
                    case KeyEvent.VK_LEFT:
                        player.setLeft(false);
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.setRight(false);
                        break;
                }
            }
        });
    }

}
