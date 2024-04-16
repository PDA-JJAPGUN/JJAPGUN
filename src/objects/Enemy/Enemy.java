package objects.Enemy;

import objects.Plane;

import javax.swing.*;
import java.awt.*;

public class Enemy extends JPanel implements Plane {
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

}
