package view;

import javax.swing.*;
import objects.Boss;

import java.awt.*;

public class GamePanel extends JPanel {


    private Boss boss;
    private GameView gameView;

    GamePanel(GameView gameView){
        this.gameView = gameView;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        boss.bossDraw(g);

        repaint();
    }
}
