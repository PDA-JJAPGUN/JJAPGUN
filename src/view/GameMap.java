package view;

import javax.swing.*;
import objects.Boss;

import java.awt.*;

public class GameMap extends JPanel {


    private GameMap gameMap = this;
    private GameView gameView;

    private Boss boss = new Boss(0,-300);
    private ImageIcon bossStageIcon = new ImageIcon("images/vsBossStage.png");

    private Image bossStageImg = bossStageIcon.getImage();

    int appear = 1;

    public GameMap(GameView gameView){
        this.gameView = gameView;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);


        g.drawImage(bossStageImg, 0, 0, null);
        boss.bossDraw(g);


        repaint();
    }
}
