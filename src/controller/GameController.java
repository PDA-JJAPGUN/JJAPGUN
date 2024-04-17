package controller;

import dao.impl.UserDAOImpl;
import entity.UserEntity;
import service.UserService;
import view.GameFrame;
import view.Panel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {
    private static final GameController instance = new GameController();

    // private 생성자를 통해 외부에서 인스턴스 생성을 방지
    public GameController() {}

    // 싱글톤 인스턴스를 반환하는 메소드
    public static GameController getInstance() {
        return instance;
    }

    
    GameFrame gameFrame;

    public boolean isGameWin = false;

    public boolean getIsGameWin() {
        return isGameWin;
    }

    public void gameStart() {
        new GameFrame();
    }

    public void setGameWin(boolean gameWin) {
        isGameWin = gameWin;
    }

    public void gameEnd() {
        gameFrame.change(Panel.GAME_END.name());
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }


    public int getFinalScore() {
        return gameFrame.player.score;
    }

    public void gameOver(boolean isGameWin) {
        setGameWin(isGameWin);
        if (gameFrame != null) {
            gameFrame.change(Panel.GAME_END.name());
        } else {
            System.out.println(gameFrame);
        }
    }
}
