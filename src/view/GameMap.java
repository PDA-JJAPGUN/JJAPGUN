package view;

import objects.Enemy.Enemy1;
import objects.Enemy.Enemy2;
import objects.Enemy.Enemy3;
import objects.Enemy.EnemyEntity;
import objects.boss.Boss;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class GameMap extends JPanel {


    private GameMap gameMap = this;
    private GameFrame gameFrame;

    private EnemyEntity enemyUnit;
    private Boss boss;

    private JLabel laLifecount, laLifecount2, laLifecount3; // lifecount 라벨
    private ImageIcon lifeCounticon;

    private ImageIcon stageIcon = new ImageIcon("images/Stage.png");
    private Image stageImg = stageIcon.getImage();

    private ImageIcon bossStageIcon = new ImageIcon("images/vsBossStage.png");
    private Image bossStageImg = bossStageIcon.getImage();

    private ImageIcon titleIcon = new ImageIcon("images/GameTitle.gif");
    private Image titleImg = titleIcon.getImage();

    int stageY1 = -stageImg.getHeight(null) + bossStageImg.getHeight(null);
    int stageY2 = -stageImg.getHeight(null) + bossStageImg.getHeight(null);
    int bossStageBY1 = -bossStageImg.getHeight(null);
    int bossStageBY2 = -bossStageImg.getHeight(null)*2;

    int appear = 1;
    int score = 0;
    JLabel la_score;
    Font font = new Font(null,1,40);

    Vector<EnemyEntity> enemyUnits = new Vector<>();

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

                la_score = new JLabel(score+"");
                la_score.setForeground(Color.WHITE);
                la_score.setFont(font);
                la_score.setBounds(425, 0, 150, 50);
                la_score.setHorizontalAlignment(JLabel.RIGHT);
                add(la_score);
                setComponentZOrder(la_score, 0);

                while(gameFrame.isgame){
                    setLayout(null);

                    if(boss == null){
                        stageY1++;
                        stageY2++;
                        if (stageY1 > stageImg.getHeight(null)) {
                            stageY1 = 0;
                        }
                        if (stageY2 > 0) {
                            stageY2 = -stageImg.getHeight(null);
                        }
                    }
                    if(boss != null){
                        if(bossStageBY1 < bossStageImg.getHeight(null) &&
                                bossStageBY2 < bossStageImg.getHeight(null)){
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

                    try{
                        appear++;
                        lifeCounting();
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

        g.drawImage(stageImg, 0, stageY1, null);
        g.drawImage(stageImg, 0, stageY2, null);
        g.drawImage(bossStageImg, 0, bossStageBY1, null);
        g.drawImage(bossStageImg, 0, bossStageBY2, null);

        if (gameFrame.player != null) {
            gameFrame.player.playerUpdate(g);
        }

        if (boss != null){
            boss.bossDraw(g);
        }

        repaint();
    }

    private void lifeLaInit() { // 당장 안 써요. 괜히 만들어달라 했나..
        lifeCounticon = new ImageIcon("images/LifeCount.png");
        laLifecount = new JLabel(lifeCounticon);
        laLifecount2 = new JLabel(lifeCounticon);
        laLifecount3 = new JLabel(lifeCounticon);

    }

    public void lifeCounting() {
        if (gameFrame.player.getLife() == 3) {
            laLifecount.setVisible(true);
            laLifecount2.setVisible(true);
            laLifecount3.setVisible(true);
            repaint();
        } else if (gameFrame.player.getLife() == 2) {
            laLifecount.setVisible(true);
            laLifecount2.setVisible(true);
            laLifecount3.setVisible(false);
            repaint();
        } else if (gameFrame.player.getLife() == 1) {
            laLifecount.setVisible(true);
            laLifecount2.setVisible(false);
            laLifecount3.setVisible(false);
            repaint();
        } else {
            laLifecount.setVisible(false);
            laLifecount2.setVisible(false);
            laLifecount3.setVisible(false);
            repaint();
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

    public void batchEnemy(){
    }
}