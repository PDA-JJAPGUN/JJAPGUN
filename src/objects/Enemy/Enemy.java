package objects.Enemy;

import objects.Bullet;
import objects.Plane;
import objects.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Enemy extends JPanel implements Plane {
    protected Player player;
    protected int x, y;
    protected int width, height;
    protected int life;
    protected int count;
    protected Image image;
    protected boolean collision = false;
    protected boolean crushCheck;
    protected boolean isThreadLife;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isCrushCheck() {
        return crushCheck;
    }

    public void setCrushCheck(boolean crushCheck) {
        this.crushCheck = crushCheck;
    }

    public void commonMove() throws InterruptedException {
        Thread.sleep(5);
        movedown();
        bulletCreate();
        count++;
        if (y > 900) {
            isThreadLife = false;
        }
    }

    @Override
    public void move() {}

    public void crush() {
        Thread t = new Thread(() -> {
            while (!player.getInvincible() && y < 900 && y > -300) {
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
                        explodeEnemy(player, this); // 적기 - 사용자 비행기 충돌
                        break;
                    }

                    if (crushCheck) {
                        explodeEnemy(this); // 사용자 총알이 적기에 crush
                        break;
                    }

                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    @Override
    public void bulletCreate() {}

    @Override
    public void planeDraw(Graphics g) {}

    public void movedown() {
        y++;
        setLocation(x, y);
    }

    public void moveleft() {
        x--;
        setLocation(x, y);
    }

    public void moveright() {
        x++;
        setLocation(x, y);
    }

    public void explodeEnemy(Player player, Enemy enemy) {
        try {
            ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
            player.setIcon(explosionIcon);
            enemy.image = explosionIcon.getImage();
            player.setInvincible(true);
            Thread.sleep(100);
            player.setIcon(player.getPlayerInvincibleIcon());
            player.setLife(player.getLife() - 1);

            player.setX(200);
            player.setY(520);
            Thread.sleep(300);
            player.setIcon(player.getPlayerIcon());
            player.setInvincible(false);

            enemy.y = 1000;
            player.repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void explodeEnemy(Enemy enemyUnit) {
        try {
            ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
            enemyUnit.image = explosionIcon.getImage();
            enemyUnit.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}