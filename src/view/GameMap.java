package view;

import objects.Enemy.*;
import objects.boss.Boss;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class GameMap extends JPanel {


    private GameMap gameMap = this;
    private GameFrame gameFrame;

    private Enemy enemyUnit;
    private Boss boss;

    private JLabel laLifecount, laLifecount2, laLifecount3; // lifecount 라벨
    private ImageIcon lifeCounticon;

    Vector<Enemy> enemyUnits = new Vector<Enemy>();

    Enemy enemy;
    private ImageIcon bossStageIcon = new ImageIcon("images/vsBossStage.png");
    private Image bossStageImg = bossStageIcon.getImage();

    private ImageIcon stageIcon = new ImageIcon("images/Stage.jpg");
    private Image stageImg = stageIcon.getImage();
    int stageY1 = 0;
    int stageY2 = -stageImg.getHeight(null);


    int appear = 1;
    JLabel la_score;
    Font font = new Font(null,1,20);


    public GameMap(GameFrame gameFrame){

        this.gameFrame = gameFrame;

        new Thread(new Runnable() {
            @Override
            public void run() {
                gameFrame.isgame = true;

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

                la_score = new JLabel("SCORE: 0");
                la_score.setForeground(Color.WHITE);
                la_score.setFont(font);
                la_score.setBounds(350, 0, 150, 50);
                la_score.setHorizontalAlignment(JLabel.RIGHT);
                add(la_score);
                setComponentZOrder(la_score, 0);


                while (gameFrame.isgame) {
                    stageY1++;
                    stageY2++;
                    if (stageY1 > stageImg.getHeight(null)) {
                        stageY1 = 0;
                    }
                    if (stageY2 > 0) {
                        stageY2 = -stageImg.getHeight(null);
                    }
                    try {

                        appear++;
                        countLife();
                        updateScore();
                        batchEnemy();
                        crushBorder();

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
        changeBgImg();
        g.drawImage(stageImg, 0, stageY1, null);
        g.drawImage(stageImg, 0, stageY2, null);

        if (gameFrame.player != null) {
            gameFrame.player.playerUpdate(g);
        }

        for (int i = 0; i < enemyUnits.size(); i++) { // null이 아니면 그려라
            if (enemyUnits.get(i) != null) {
                enemyUnits.get(i).planeDraw(g);
            }
        }
        if (boss != null){
            boss.planeDraw(g);
        }

        repaint();
    }

    private void lifeLaInit() {
        lifeCounticon = new ImageIcon("images/LifeCount.png");
        laLifecount = new JLabel(lifeCounticon);
        laLifecount2 = new JLabel(lifeCounticon);
        laLifecount3 = new JLabel(lifeCounticon);

    }

    public void countLife() {
        if (gameFrame.player.getLife() == 3) {
            laLifecount.setVisible(true);
            laLifecount2.setVisible(true);
            laLifecount3.setVisible(true);
        } else if (gameFrame.player.getLife() == 2) {
            laLifecount.setVisible(true);
            laLifecount2.setVisible(true);
            laLifecount3.setVisible(false);
        } else if (gameFrame.player.getLife() == 1) {
            laLifecount.setVisible(true);
            laLifecount2.setVisible(false);
            laLifecount3.setVisible(false);
        } else {
            laLifecount.setVisible(false);
            laLifecount2.setVisible(false);
            laLifecount3.setVisible(false);
        }

        laLifecount.setLocation(0, 0);
        laLifecount2.setLocation(50, 0);
        laLifecount3.setLocation(100, 0);
        repaint();
    }

    public void updateScore() {
        la_score.setText(String.format("SCORE: %d", gameFrame.player.score));
        la_score.setLocation(350, 0);
        repaint();
    }

    public void changeBgImg(){
        if(appear == 5000){
            stageIcon = new ImageIcon("images/Stage2.png");
            stageImg = stageIcon.getImage();
            stageY1 = 0;
            stageY2 = -stageImg.getHeight(null);
        }else if(appear == 10000){
            stageIcon = new ImageIcon("images/vsBossStage.jpg");
            stageImg = stageIcon.getImage();
            stageY1 = 0;
            stageY2 = -stageImg.getHeight(null);
        }

    }

    public void batchEnemy() {
        if (boss == null) {
            if (appear % 3000 == 0) {
                enemyUnits.add(new Enemy3(gameFrame.player, 600, -200, 100, 100)); // 컨텍스트 넘기기
                enemyUnits.add(new Enemy2(gameFrame.player, 0, 0, 100, 100));
            }
            if (appear % 2000 == 1000 && appear % 3000 != 0) {
                enemyUnits.add(new Enemy1(gameFrame.player, 50, 0, 50, 50));
                enemyUnits.add(new Enemy1(gameFrame.player, 100, -50, 50, 50));
                enemyUnits.add(new Enemy1(gameFrame.player, 150, -100, 50, 50));
                enemyUnits.add(new Enemy1(gameFrame.player, 200, -150, 50, 50));
                enemyUnits.add(new Enemy1(gameFrame.player, 250, -200, 50, 50));
            }
            if (appear % 2000 == 0 && appear % 3000 != 0) {
                enemyUnits.add(new Enemy1(gameFrame.player, 500, 0, 50, 50));
                enemyUnits.add(new Enemy1(gameFrame.player, 450, -50, 50, 50));
                enemyUnits.add(new Enemy1(gameFrame.player, 400, -100, 50, 50));
                enemyUnits.add(new Enemy1(gameFrame.player, 350, -150, 50, 50));
                enemyUnits.add(new Enemy1(gameFrame.player, 300, -200, 50, 50));
            }
        }
        if (appear == 10000) {
            boss = new Boss(gameFrame.player,0, -300);
        }
    }


    public void crushBorder() { // 벽에 충돌하는 조건함수 >> Map 스레드 안에 적용
        if (gameFrame.player.getX() <= 0) {
            gameFrame.player.setX(0);
            repaint();
        } else if (gameFrame.player.getX() >= 550) {
            gameFrame.player.setX(550);
            repaint();
        }
        if (gameFrame.player.getY() <= 0) {
            gameFrame.player.setY(0);
            repaint();
        } else if (gameFrame.player.getY() >= 720) {
            gameFrame.player.setY(720);
            repaint();
        }
    }

}