package objects;

import javax.swing.*;
import java.awt.*;

public class Boss {

    private Boss boss = this;

    ImageIcon icBoss = new ImageIcon("images/bossSizeup.gif");
    Image imgBoss = icBoss.getImage();

    private int x;
    private int y;
    private int width = imgBoss.getWidth(null);
    private int height = imgBoss.getHeight(null);
    private int hp;
    private double bulletAngle;
    private int bulletCount;

    public Boss(int x, int y){
        this.x = x;
        this.y = y;
        this.hp = 100;
    }

    public int getX() { return x;}
    public void setX(int x){this.x = x;}

    public int getY(){return y;}
    public void setY(int y){this.y = y;}

    public int getWidth(){return width;}
    public void setWidth(int width){this.width = width;}
    public int getHeight(){return height;}
    public void setHeight(){this.height = height;}
    public int getHp(){ return hp;}
    public void setHp(){this.hp = hp;}


    public void move(){

    }

    public void bossDraw(Graphics g){
        g.drawImage(imgBoss,x,y,width,height,null);
    }

}
