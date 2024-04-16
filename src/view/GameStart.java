package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameStart extends GameTitle {
    public GameStart(GameFrame gameFrame) {
        super(gameFrame);
        setLayout(null);

        JLabel welcomeMsg = createLabel("Welcome, " + GameController.getInstance().getUser().getNickname() + "!", null, Color.WHITE);
        welcomeMsg.setFont(new Font(null, Font.BOLD, 30));
        welcomeMsg.setBounds(setX(300),  setY(100) - 50, 1000, 100);  // 위치와 크기 설정
        add(welcomeMsg);

        int width = 150;
        int height = 40;
        JButton startBtn = createButton("START", width, height, setX(width), setY(height));
        add(startBtn);
    }


}

