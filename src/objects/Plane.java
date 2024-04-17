package objects;

import java.awt.*;

public interface Plane {
    int getHp();
    void setHp(int hp);
    void move();
    void createBullet();
    void drawPlane(Graphics g);
}
