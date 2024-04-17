package view;

import controller.GameController;
import controller.UserController;
import entity.UserEntity;

import javax.swing.*;
import java.awt.*;


public class GameEnd extends GameTitle {

    UserController userController;
    GameController gameController;

    public GameEnd(GameFrame gameFrame) {
        super(gameFrame);
        this.userController = UserController.getInstance();
        this.gameController = GameController.getInstance();

        setLayout(null);

        int width = 150;
        int height = 40;

        JButton rankBtn = createButton("RANK", width, height, setX(width) - width, setY(height));
        add(rankBtn);

        JButton exitBtn = createButton("EXIT", width, height, setX(width) + width, setY(height));
        add(exitBtn);


        JLabel gameResult = createLabel(gameController.isGameWin ? "YOU WIN" : "YOU LOSE", null, Color.WHITE);
        gameResult.setFont(new Font(null, Font.BOLD, 40));
        gameResult.setBounds(180,  400, 300, 100);  // 위치와 크기 설정
        add(gameResult);

        UserEntity user = gameController.getUser();
        JLabel currScore = createLabel(String.format("SCORE: %d", gameController.getFinalScore()), Color.WHITE, Color.BLACK);
        currScore.setBounds(130, setY(50) - 100, 300, 50);  // 위치와 크기 설정
        currScore.setFont(new Font(null, Font.BOLD, 20));
        currScore.setHorizontalAlignment(JLabel.CENTER);
        add(currScore);

        JLabel bestScore = createLabel(String.format("BEST SCORE: %d", user.getBestScore()), Color.WHITE, Color.BLACK);
        bestScore.setBounds(90, setY(50) - 200, 400, 50);  // 위치와 크기 설정
        bestScore.setFont(new Font(null, Font.BOLD, 20));
        bestScore.setHorizontalAlignment(JLabel.CENTER);
        bestScore.setText("BEST SCORE: " + user.getBestScore());
        add(bestScore);
    }
}

