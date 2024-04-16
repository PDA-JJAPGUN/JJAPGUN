package objects.Enemy;

import java.awt.*;

public class EnemyEntity {
    public int x,y; // 현재 위치
    public int width, height; // 사진(적의 가로 세로 길이)
    public int life; // 목숨 수

    public Image image;
    public boolean collision = false;
    public boolean crushCheck;
    public int bulletSpeed;

    public boolean isThreadLife; //스레드 생명 - islife에서 수정(모호 이슈)

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public boolean isCrushCheck() {
        return crushCheck;
    }

    public void setCrushCheck(boolean crushCheck) {
        this.crushCheck = crushCheck;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public boolean isThreadLife() {
        return isThreadLife;
    }

    public void setThreadLife(boolean threadLife) {
        isThreadLife = threadLife;
    }

}
