package view;

import javax.swing.*;
import java.awt.*;

public class GameEnd extends GameTitle {
    public GameEnd(GameFrame gameFrame, boolean isGameWin) {
        super(gameFrame);

        setLayout(null);

        int width = 150;
        int height = 40;
        int x = (SCREEN_WIDTH - width) / 2;
        int y = SCREEN_HEIGHT - height - 50;
        JButton rankBtn = createButton("RANK", width, height, x - width, y);
        add(rankBtn);

        JButton exitBtn = createButton("EXIT", width, height, x + width, y);
        add(exitBtn);


        JLabel gameResult = createLabel((isGameWin)? "YOU WIN" : "YOU LOSE", null, Color.WHITE);
        gameResult.setFont(new Font(null, Font.BOLD, 40));
        gameResult.setBounds(x-10,  0, 300, 100);  // 위치와 크기 설정
        add(gameResult);

        JLabel currScore = createLabel("SCORE : 11111", Color.WHITE, Color.BLACK);
        currScore.setBounds(x - 80, y - 100, 300, 50);  // 위치와 크기 설정
        currScore.setFont(new Font(null, Font.BOLD, 20));
        currScore.setHorizontalAlignment(JLabel.CENTER);
        System.out.println(x+ "," + y);
        add(currScore);

        JLabel bestScore = createLabel("BEST SCORE : 22222", Color.WHITE, Color.BLACK);
        bestScore.setBounds(x - 80, y - 200, 300, 50);  // 위치와 크기 설정
        bestScore.setFont(new Font(null, Font.BOLD, 20));
        bestScore.setHorizontalAlignment(JLabel.CENTER);
        add(bestScore);
    }




}

