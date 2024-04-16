package view;

import javax.swing.*;

import objects.Boss;
import objects.Enemy.Enemy;
import objects.Enemy.Enemy1;
import objects.Enemy.Enemy2;
import objects.Enemy.Enemy3;

import java.awt.*;
import java.util.Vector;

public class GameMap extends JPanel {


    private GameMap gameMap = this;
    private GameFrame gameFrame;
    private Boss boss;

    Vector<Enemy> enemyUnits = new Vector<>();

    Enemy enemy;
    private ImageIcon bossStageIcon = new ImageIcon("images/vsBossStage.png");
    private Image bossStageImg = bossStageIcon.getImage();
    private ImageIcon stageIcon = new ImageIcon("images/Stage.png");
    private Image stageImg = stageIcon.getImage();
    int stageY1 = -stageImg.getHeight(null) + bossStageImg.getHeight(null);
    int stageY2 = -stageImg.getHeight(null) + bossStageImg.getHeight(null);
    int bossStageBY1 = -bossStageImg.getHeight(null);
    int bossStageBY2 = -bossStageImg.getHeight(null) * 2;

    int appear = 1;
    int score = 0;
    JLabel la_score;
    Font font = new Font(null, 1, 40);

    public GameMap(GameFrame gameFrame) {

        this.gameFrame = gameFrame;

        new Thread(new Runnable() {
            @Override
            public void run() {
                gameFrame.isgame = true;
                setLayout(null);

                la_score = new JLabel(score + "");
                la_score.setForeground(Color.WHITE);
                la_score.setFont(font);
                la_score.setBounds(20, 0, 300, 100);
                add(la_score);
                setComponentZOrder(la_score, 0);

                while (gameFrame.isgame) {
                    if (boss == null) {
                        stageY1++;
                        stageY2++;
                        if (stageY1 > stageImg.getHeight(null)) {
                            stageY1 = 0;
                        }
                        if (stageY2 > 0) {
                            stageY2 = -stageImg.getHeight(null);
                        }
                    }
                    if (boss != null) {
                        if (bossStageBY1 < bossStageImg.getHeight(null) &&
                                bossStageBY2 < bossStageImg.getHeight(null)) {
                            stageY1++;
                            stageY2++;
                        }
                        bossStageBY1++;
                        bossStageBY2++;
                        if (bossStageBY1 > bossStageImg.getHeight(null)) {
                            bossStageBY1 = 0;
                        }
                        if (bossStageBY2 > 0) {
                            bossStageBY2 = -bossStageImg.getHeight(null);
                        }
                    }

                    try {
                        appear++;
                        System.out.println(appear);
                        batchEnemy();
                        repaint();
                        Thread.sleep(3);
                    } catch (Exception e) {
                        //Handle exception
                    }
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(stageImg, 0, stageY1, null);
        g.drawImage(stageImg, 0, stageY2, null);
        g.drawImage(bossStageImg, 0, bossStageBY1, null);
        g.drawImage(bossStageImg, 0, bossStageBY2, null);

        for (int i = 0; i < enemyUnits.size(); i++) { // null이 아니면 그려라
            if (enemyUnits.get(i) != null) {
                enemyUnits.get(i).planeDraw(g);
            }
        }

        if (boss != null) {
            boss.bossDraw(g);
        }

        repaint();
    }

    public void batchEnemy() {
        if (boss == null) {
            if(appear % 3000 == 0){
                enemyUnits.add(new Enemy3(600, -200, 100, 100)); // 컨텍스트 넘기기
                enemyUnits.add(new Enemy2(0, 0, 100, 100));
            }
            if (appear % 2000 == 1000 && appear % 3000 != 0) {
                enemyUnits.add(new Enemy1(50, 0, 50, 50));
                enemyUnits.add(new Enemy1(100, -50, 50, 50));
                enemyUnits.add(new Enemy1(150, -100, 50, 50));
                enemyUnits.add(new Enemy1(200, -150, 50, 50));
                enemyUnits.add(new Enemy1(250, -200, 50, 50));
            }
            if (appear % 2000 == 0 && appear % 3000 != 0){
                enemyUnits.add(new Enemy1(500, 0, 50, 50));
                enemyUnits.add(new Enemy1(450, -50, 50, 50));
                enemyUnits.add(new Enemy1(400, -100, 50, 50));
                enemyUnits.add(new Enemy1( 350, -150, 50, 50));
                enemyUnits.add(new Enemy1(300, -200, 50, 50));
            }
        }

        if (appear == 10000) {
            boss = new Boss(0, -300);
        }

    }
}

