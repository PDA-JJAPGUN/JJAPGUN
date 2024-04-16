package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;


public class GameEnd extends GameTitle {
    public GameEnd(GameFrame gameFrame) {
        super(gameFrame);

        setLayout(null);

        int width = 150;
        int height = 40;

        JButton rankBtn = createButton("RANK", width, height, setX(width) - width, setY(height));
        add(rankBtn);

        JButton exitBtn = createButton("EXIT", width, height, setX(width) + width, setY(height));
        add(exitBtn);


        JLabel gameResult = createLabel((GameController.getInstance().isGameWin())? "YOU WIN" : "YOU LOSE", null, Color.WHITE);
        gameResult.setFont(new Font(null, Font.BOLD, 40));
        gameResult.setBounds(setX(300)-10,  setY(100) - 330, 300, 100);  // 위치와 크기 설정
        add(gameResult);

        JLabel currScore = createLabel("SCORE : 11111", Color.WHITE, Color.BLACK);
        currScore.setBounds(setX(300) - 80, setY(50) - 100, 300, 50);  // 위치와 크기 설정
        currScore.setFont(new Font(null, Font.BOLD, 20));
        currScore.setHorizontalAlignment(JLabel.CENTER);
        add(currScore);

        JLabel bestScore = createLabel("BEST SCORE : 22222", Color.WHITE, Color.BLACK);
        bestScore.setBounds(setX(300) - 80, setY(50) - 200, 300, 50);  // 위치와 크기 설정
        bestScore.setFont(new Font(null, Font.BOLD, 20));
        bestScore.setHorizontalAlignment(JLabel.CENTER);
        add(bestScore);
    }






}

