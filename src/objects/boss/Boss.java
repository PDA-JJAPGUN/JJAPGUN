package objects.boss;

import objects.Bullet;
import objects.Plane;
import objects.player.Player;
import view.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Boss implements Plane {
    private Boss boss = this;
    private Player player;
    public ImageIcon icBoss = new ImageIcon("images/bossSizeup.gif");
    public Image imgBoss = icBoss.getImage();
    private int x;
    private int y;
    private int width = imgBoss.getWidth(null);
    private int height = imgBoss.getHeight(null);
    private int hp;
    private boolean isThreadLife = true;
    private int count = 0;
    ArrayList<Bullet> bullets = new ArrayList<>();
    private Bullet bullet;

    public Boss(Player player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.hp = 100;

        player.addBossContext(boss);
        move();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getLife() {
        return 0;
    }

    @Override
    public void setLife(int life) {}

    @Override
    public void move() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isThreadLife) {
                    try {
                        Thread.sleep(5);
                        count++;
                        bulletCreate();

                        if (hp == 0) {
                            isThreadLife = false;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void bulletCreate() {
        if (count % 50 == 0) {
            Random random = new Random();
            int angle = 220 + random.nextInt(90);
            int speed = random.nextInt(5) + 3;

            Bullet bullet = new Bullet(player, 280, 150, angle, speed, 20, 20);
            bullets.add(bullet);
        }
    }

    @Override
    public void planeDraw(Graphics g) {
        g.drawImage(imgBoss, x, y, width, height, null);
        for (int i = 0; i < bullets.size(); i++) {
            bullet = bullets.get(i);
            g.drawImage(bullet.bulletImg4, bullet.getX(), bullet.getY(), bullet.getB1Width() / 3,
                    bullet.getB1Height() / 3, null);
        }
    }

}