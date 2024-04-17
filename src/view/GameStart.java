package view;

import controller.GameController;
import controller.UserController;

import javax.swing.*;
import java.awt.*;

public class GameStart extends GameTitle {
    public GameStart(GameFrame gameFrame) {
        super(gameFrame);
        setLayout(null);

        JLabel welcomeMsg = createLabel("Welcome, " +  UserController.getInstance().getLogginedUser().getNickname() + "!", null, Color.WHITE);
        welcomeMsg.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeMsg.setBounds(setX(300),  setY(100) - 50, 1000, 100);  // 위치와 크기 설정
        add(welcomeMsg);

        int width = 150;
        int height = 40;
        JButton startBtn = createButton("START", width, height, (SCREEN_WIDTH - width) / 2, SCREEN_HEIGHT - height - 50);
        add(startBtn);
    }
}

