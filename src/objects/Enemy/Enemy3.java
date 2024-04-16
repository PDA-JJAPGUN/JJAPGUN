package objects.Enemy;

import objects.Bullet;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy3 extends Enemy {

    private Enemy3 enemy3 = this;
    private static final String TAG = "Enemy3 : ";

//    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
//    private Bullet bullet;

    public Enemy3(int x, int y, int w, int h) {
//        this.player = player;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.image = new ImageIcon("images/enemy3.png").getImage();
        this.life = 3;
        this.crushCheck = false;
        this.isThreadLife = true;

//        this.player.contextAdd(enemy3);

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
                        y++;

                        if (y < 400) {
                            moveleft();
                        }

//                        bulletCreate();
                        count++;

                        if (y > 900) {
                             System.out.println("enemy3 쓰레드 종료");
                            isThreadLife = false;
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
//                            exploseEnemy(player, enemy3); // 충돌 폭발 메서드
//                        }
//                        Thread.sleep(10);
//
//                        if (crushCheck) {
//                            exploseEnemy(enemy3);
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

//    public void bulletCreate() {
//        if (count % 100 == 0) {
//            bullet = new Bullet( x + 20, y + 40, 270, 5, 20, 20);
//            bullets.add(bullet);
//
//        }
//    }
//
    public void planeDraw(Graphics g) { // 그림그리기
        g.drawImage(image, x, y, width, height, null);
//        for (int i = 0; i < bullets.size(); i++) {
//            bullet = bullets.get(i);
//            g.drawImage(bullet.bulletImg1, bullet.getX(), bullet.getY(), bullet.getWidth(),
//                    bullet.getHeight(), null);
//
//        }
    }

}

