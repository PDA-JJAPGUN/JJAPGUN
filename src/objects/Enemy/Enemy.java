package objects.Enemy;

import objects.Plane;
import objects.player.Player;

import javax.swing.*;
import java.awt.*;

public class Enemy extends JPanel implements Plane {
    protected Player player;
    protected int x, y; // 현재 위치
    protected int width, height; // 사진(적의 가로 세로 길이)
    protected int life; // 목숨 수
    protected int count;
    protected Image image;
    protected boolean collision = false;
    protected boolean crushCheck;
    protected int bulletSpeed;

    protected boolean isThreadLife; //스레드 생명 - islife에서 수정(모호 이슈)

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public void move() {

    }

    @Override
    public void bulletCreate() {

    }

    @Override
    public void planeDraw(Graphics g) {

    }

    public void movedown() {
        y++;
        setLocation(x, y);
    }

    public void moveleft() {
        x--;
        setLocation(x, y);

    }

    public void moveup() {
        y--;
        setLocation(x, y);

    }

    public void moveright() {
        x++;
        setLocation(x, y);

    }

    public void exploseEnemy(Player player, Enemy enemy) { // 충돌후 이미지 변경 및 목숨카운트
        try {
            ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
            player.setIcon(explosionIcon);
            enemy.image = explosionIcon.getImage();
            System.out.println("적기와 아군비행기 충돌");
            player.setInvincible(true); // 무적상태
            Thread.sleep(500);

            player.setIcon(player.getPlayerInvincibleIcon());
            player.setLife(player.getLife() - 1);
            System.out.println("남은목숨:" + player.getLife());
            player.setX(200);
            player.setY(520);
            Thread.sleep(1500);

            player.setIcon(player.getPlayerIcon());
            player.setInvincible(false);
            enemy.y = 1000; // 맵 바깥으로 적 던짐
            player.repaint();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void exploseEnemy(Enemy enemyUnit) { // 적기가 아군총알에 충돌시 구현, 오버로딩

        try {
            ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
            enemyUnit.image = explosionIcon.getImage();

            System.out.println("적기와 아군비행기 총알 충돌");
//			Thread.sleep(1000);
//			enemyUnit.y = 1000; // Thread 강제종료 방법이 마땅히 안 떠오름 대충 이렇게

            enemyUnit.repaint();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}