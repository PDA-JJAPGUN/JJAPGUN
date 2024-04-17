package objects.Enemy;

import objects.Bullet;
import objects.player.Player;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy2 extends Enemy {
    private Enemy enemy2 = this;
    ArrayList<Bullet> bullets = new ArrayList<>();
    private Bullet bullet;

    public Enemy2(Player player, int x, int y, int w, int h) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.image = new ImageIcon("images/enemy4.png").getImage();
        this.hp = 3;
        this.crashCheck = false;
        this.isThreadLife = true;

        this.player.addEnemyContext(enemy2);

        this.move();
        this.explode();
    }

    public void move() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                count = 0;
                while (isThreadLife) {
                    try {
                        commonMove();
                        if (y < 600) {
                            moveright();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void createBullet() {
        if (count % 100 == 0 && !crashCheck) {
            bullet = new Bullet(player, x + 35, y + 55, 270, 5, 20, 20);
            bullets.add(bullet);
        }
    }

    @Override
    public void drawPlane(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
        for (int i = 0; i < bullets.size(); i++) {
            bullet = bullets.get(i);
            g.drawImage(bullet.bulletImg2, bullet.getX(), bullet.getY(), bullet.getWidth(),
                    bullet.getHeight(), null);
        }
    }

}