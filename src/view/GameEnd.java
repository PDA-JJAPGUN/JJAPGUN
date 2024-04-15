package view;

import javax.swing.*;

public class GameEnd extends GameTitle {
    public GameEnd(GameFrame gameFrame) {
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
    }


}

