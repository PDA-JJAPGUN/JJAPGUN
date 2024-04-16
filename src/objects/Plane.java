package objects;

import java.awt.*;

public interface Plane {
    int getLife();
    void setLife(int life);
    void move();
    void bulletCreate();
    void planeDraw(Graphics g);
}
