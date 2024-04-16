package objects;

import objects.player.Player;
import view.GameFrame;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet implements Runnable{
    private Player player;
    private boolean collision;

    public Image bulletImg1 = new ImageIcon("images/bullet1.png").getImage();
    public Image bulletImg2 = new ImageIcon("images/bullet2.png").getImage();
    public Image bulletImg4 = new ImageIcon("images/bullet4.png").getImage();

    private int x;
    private int y;
    private double angel = 270;
    private double speed = 2;
    private int width;
    private int height;
    private int b1Width = bulletImg1.getWidth(null);
    private int b1Height = bulletImg1.getHeight(null);

    private boolean isThreadLife;

    public Bullet(Player player, int x, int y, double angel, double speed, int width, int height) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.angel = angel;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.isThreadLife = true;
        this.collision = false;

       Thread bulletthread = new Thread(this);
       bulletthread.setName("EnemyBullet");
       bulletthread.start();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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

    public void setHeight(int height) {
        this.height = height;
    }

    public int getB1Width() {
        return b1Width;
    }

    public int getB1Height() {
        return b1Height;
    }

    public void fire() throws InterruptedException {
        x -= Math.cos(Math.toRadians(angel)) * speed;
        y -= Math.sin(Math.toRadians(angel)) * speed;
        Thread.sleep(10);
    }

    @Override
    public void run() {
        while (isThreadLife) {
            try {
                fire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (x > 1000 || x < -500 || y < -500 || y > 1000) {
                isThreadLife = false;
            }

            if (!player.getInvincible()) {
                crash();
                try {
                    if (collision) {
                        explodePlayer(player);
                    }
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 적 총알이 사용자 비행기에 닿았는지
    public void crash() {
        if (Math.abs(
                ((player.getX() - 11) + player.getWidth() / 3) - (x + width / 3)) < (width / 3 + player.getWidth() / 3)
                && Math.abs(((player.getY() - 5) + player.getHeight() / 3) - (y + height / 3)) < (height / 3
                + player.getHeight() / 3)) {
            collision = true;
        } else {
            collision = false;
        }
    }

    public void explodePlayer(Player player) {
        try {
            ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
            player.setIcon(explosionIcon);
            y = 1000;
            player.setInvincible(true);
            Thread.sleep(100);
            player.setIcon(player.getPlayerInvincibleIcon());
            player.setLife(player.getLife() - 1);

            player.setX(200);
            player.setY(520);
            Thread.sleep(300);
            player.setIcon(player.getPlayerIcon());
            player.setInvincible(false);

            player.repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

