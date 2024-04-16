package objects.Enemy;

import objects.Bullet;
import objects.Enemy.Enemy;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy2 extends Enemy {

    private Enemy2 enemy2 = this;
    private static final String TAG = "Enemy4 : ";

    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private Bullet bullet;

    public Enemy2(int x, int y, int w, int h) {
//        this.player = player;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.image = new ImageIcon("images/enemy4.png").getImage();
        this.life = 3;
        this.crushCheck = false;
        this.isThreadLife = true;

//        this.player.contextAdd(enemy2);

        this.move();
//        this.crush();

    }

    public void move() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                count = 0;
                while (isThreadLife) {
                    try {
                        Thread.sleep(5);

                        movedown();
                        y++; // down가속

                        if (y < 400) {
                            moveright();

                        }

                        bulletCreate();
                        count++;

                        if (y > 900) {
                             System.out.println("enemy4 쓰레드 종료");
                            isThreadLife =false;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

//    public void crush() { // 적비행기-Player 충돌
//
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//
//                while (!player.getInvincible() && y < 900 && y > -300) {
//
//                    if (Math.abs((player.getX() + player.getWidth() / 2) - (x + player.getWidth() / 2)) < (width / 2
//                            + player.getWidth() / 2)
//                            && Math.abs((player.getY() + player.getHeight() / 2) - (y + height / 2)) < (height / 2
//                            + player.getHeight() / 2)) {
//                        collision = true;
//                    } else {
//                        collision = false;
//                    }
//
//                    try {
//                        if (collision) {
//                            exploseEnemy(player, enemy2); // 충돌 폭발 메서드
//                        }
//                        Thread.sleep(10);
//
//                        if (crushCheck) {
//                            exploseEnemy(enemy2);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//        }).start();
//
//    }



    public void bulletCreate() {
        if (count % 100 == 0 && count < 300) {
            bullet = new Bullet(x + 35, y + 55, 270, 5, 20, 20);
            bullets.add(bullet);
            System.out.println(bullets.size());
        }
    }

    @Override
    public void planeDraw(Graphics g) { // 그림그리기
        g.drawImage(image, x, y, width, height, null);
        for (int i = 0; i < bullets.size(); i++) {
            bullet = bullets.get(i);
            g.drawImage(bullet.bulletImg1, bullet.getX(), bullet.getY(), bullet.getWidth(),
                    bullet.getHeight(), null);


        }
    }

}
