package objects.Enemy;

import java.awt.*;

public class EnemyEntity {
    protected int x,y; // 현재 위치
    protected int width, height; // 사진(적의 가로 세로 길이)
    protected int life; // 목숨 수

    protected Image image;
    protected boolean collision = false;
    protected boolean crushCheck;
    protected int bulletSpeed;

    protected boolean isThreadLife; //스레드 생명 - islife에서 수정(모호 이슈)

}
