package view;

import javax.swing.*;
import objects.Boss;

import java.awt.*;

public class GameMap extends JPanel {


    private GameMap gameMap = this;
    private GameFrame gameFrame;
    private Boss boss;
    private ImageIcon bossStageIcon = new ImageIcon("images/vsBossStage.png");

    private Image bossStageImg = bossStageIcon.getImage();

    int appear = 1;

    public GameMap(GameFrame gameFrame){
        this.gameFrame = gameFrame;

        new Thread(new Runnable() {
            @Override
            public void run() {
                gameFrame.isgame = true;

                while(gameFrame.isgame){
                    setLayout(null);
                    try{
                        appear++;
                        batchEnemy();
                        repaint();
                        Thread.sleep(3);
                    }catch(Exception e){
                        //Handle exception
                    }
                }
            }
        }).start();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);


        g.drawImage(bossStageImg, 0, 0, null);


        if (boss != null){
            boss.bossDraw(g);
        }

        repaint();
    }

    public void batchEnemy(){
        if(appear == 5){
            boss = new Boss(0, -300);
        }
    }
}

