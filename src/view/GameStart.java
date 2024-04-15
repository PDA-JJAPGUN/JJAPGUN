package view;

import javax.swing.*;

public class GameStart extends GameTitle {
    public GameStart(GameFrame gameFrame) {
        super(gameFrame);
        setLayout(null);
        int width = 150;
        int height = 40;
        JButton startBtn = createButton("START", width, height, (SCREEN_WIDTH - width) / 2, SCREEN_HEIGHT - height - 50);
        add(startBtn);
    }


}

