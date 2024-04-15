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
    int bossStageBY1 = 0;
    int bossStageBY2 = -bossStageImg.getHeight(null);

    int appear = 1;
    int score = 0;
    JLabel la_score;
    Font font = new Font(null,1,40);

    public GameMap(GameFrame gameFrame){

        this.gameFrame = gameFrame;

        new Thread(new Runnable() {
            @Override
            public void run() {
                gameFrame.isgame = true;
                setLayout(null);

                la_score = new JLabel(score+"");
                la_score.setForeground(Color.WHITE);
                la_score.setFont(font);
                la_score.setBounds(20, 0, 300, 100);
                add(la_score);
                setComponentZOrder(la_score,0);

                while(gameFrame.isgame){


                    if(boss != null){
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

//        g.drawImage(stageImg, 0, stageY, null);
        g.drawImage(bossStageImg, 0, bossStageBY1, null);
        g.drawImage(bossStageImg, 0, bossStageBY2, null);


        if (boss != null){
            boss.bossDraw(g);
        }

        repaint();
    }

    public void batchEnemy(){
        if(appear == 1000){
            boss = new Boss(0, -300);
        }
        if(appear == 2000*30){
            boss = new Boss(0, -300);
        }
        if(appear == 3000*30){
            boss = new Boss(0, -300);
        }
        if(appear == 4000*30){
            boss = new Boss(0, -300);
        }
        if(appear == 5000*30){
            boss = new Boss(0, -300);
        }
    }
}

