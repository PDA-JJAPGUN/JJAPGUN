package objects.Enemy;

import objects.player.Player;

import javax.swing.*;
import java.awt.*;

public class Enemy1 extends Enemy {
    private Enemy enemy1 = this;
    public Enemy1(Player player, int x, int y, int w, int h) {

        this.player = player;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.image = new ImageIcon("images/enemy1.png").getImage();
        this.life = 1;
        this.crushCheck = false;
        this.isThreadLife = true;

        this.player.addEnemyContext(enemy1); // 동적으로 생성때마다 컨텍스트 플레이어에게 넘기기

        this.move();
        this.crush(); // 충돌탐지용


    }

    public void move() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isThreadLife) {
                    try {
                        Thread.sleep(2);

                        movedown();

                        if (y > 900) {
                            System.out.println("enemy1MoveThread 종료");
                            isThreadLife = false;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.setName("enemy1Move");
        t1.start();

    }

    public void crush() { // 적비행기-Player 충돌

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                while (!player.getInvincible() && y < 900) {

                    if (Math.abs((player.getX() + player.getWidth() / 2) - (x + player.getWidth() / 2)) < (width / 2
                            + player.getWidth() / 2)
                            && Math.abs((player.getY() + player.getHeight() / 2) - (y + height / 2)) < (height / 2
                            + player.getHeight() / 2)) {
                        collision = true;
                    } else {
                        collision = false;
                    }

                    try {
                        if (collision) {
                            exploseEnemy(player, enemy1); // 플레이어와 적기 충돌시
                            break;
                        }

                        if (crushCheck) {
                            exploseEnemy(enemy1); // 플레이어 총알이 적기 충돌시
                            break;
                        }
                        Thread.sleep(10);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        t1.setName("enemy1Crash");
        t1.start();

    }


    public void planeDraw(Graphics g) { // 그림그리기
        g.drawImage(image, x, y, width, height, null);

    }
}