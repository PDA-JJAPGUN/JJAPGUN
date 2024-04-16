package objects.Enemy;

import objects.Bullet;
import objects.player.Player;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy3 extends Enemy{
    private Enemy enemy3 = this;
    ArrayList<Bullet> bullets = new ArrayList<>();
    private Bullet bullet;

    public Enemy3(Player player, int x, int y, int w, int h) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.image = new ImageIcon("images/enemy33.png").getImage();
        this.life = 3;
        this.crushCheck = false;
        this.isThreadLife = true;

        this.player.addEnemyContext(enemy3);

        this.move();
        this.crush();
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
                            moveleft();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void bulletCreate() {
        if (count % 100 == 0 &&  !crushCheck) {
            bullet = new Bullet(player, x + 35, y + 55, 270, 5, 20, 20);
            bullets.add(bullet);
        }
    }

    public void planeDraw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
        for (int i = 0; i < bullets.size(); i++) {
            bullet = bullets.get(i);
            g.drawImage(bullet.bulletImg2, bullet.getX(), bullet.getY(), bullet.getWidth(),
                    bullet.getHeight(), null);
        }
    }

}