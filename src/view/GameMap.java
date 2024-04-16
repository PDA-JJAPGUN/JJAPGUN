package view;

import javax.swing.*;
import objects.boss.Boss;

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
    private JLabel laLifecount, laLifecount2, laLifecount3; // lifecount 라벨
    private ImageIcon lifeCounticon;


    Vector<Enemy> enemyUnits = new Vector<>();

    Enemy enemy;
    private ImageIcon bossStageIcon = new ImageIcon("images/vsBossStage.png");
    private Image bossStageImg = bossStageIcon.getImage();
    private ImageIcon stageIcon = new ImageIcon("images/Stage.jpg");
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

                lifeLaInit();
                add(laLifecount);
                add(laLifecount2);
                add(laLifecount3);
                laLifecount.setBounds(0, 0, 50, 50);
                laLifecount2.setBounds(50, 0, 50, 50);
                laLifecount3.setBounds(100, 0, 50, 50);
                setComponentZOrder(laLifecount,0);
                setComponentZOrder(laLifecount2,0);
                setComponentZOrder(laLifecount3,0);

                la_score = new JLabel(score+"");
                la_score.setForeground(Color.WHITE);
                la_score.setFont(font);
                la_score.setBounds(425, 0, 150, 50);
                la_score.setHorizontalAlignment(JLabel.RIGHT);
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
                        lifeCounting();
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
        changeBgImg();
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


    private void lifeLaInit() {
        lifeCounticon = new ImageIcon("images/LifeCount.png");
        laLifecount = new JLabel(lifeCounticon);
        laLifecount2 = new JLabel(lifeCounticon);
        laLifecount3 = new JLabel(lifeCounticon);
    }


    public void lifeCounting() {
//        if (gameFrame.player.getLife() == 3) {
//            laLifecount.setVisible(true);
//            laLifecount2.setVisible(true);
//            laLifecount3.setVisible(true);
//            repaint();
//        } else if (gameFrame.player.getLife() == 2) {
//            laLifecount.setVisible(true);
//            laLifecount2.setVisible(true);
//            laLifecount3.setVisible(false);
//            repaint();
//        } else if (gameFrame.player.getLife() == 1) {
//            laLifecount.setVisible(true);
//            laLifecount2.setVisible(false);
//            laLifecount3.setVisible(false);
//            repaint();
//        } else {
//            laLifecount.setVisible(false);
//            laLifecount2.setVisible(false);
//            laLifecount3.setVisible(false);
//            repaint();
//        }
    }

    public void changeBgImg(){
        if(appear == 1000){

            stageIcon = new ImageIcon("images/Stage2.png");
            stageImg = stageIcon.getImage();
        }
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

