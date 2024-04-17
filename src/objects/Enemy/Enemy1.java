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
        this.hp = 1;
        this.crashCheck = false;
        this.isThreadLife = true;

        this.player.addEnemyContext(enemy1);

        this.move();
        this.explode();
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
                            isThreadLife = false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
    }

    public void drawPlane(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}