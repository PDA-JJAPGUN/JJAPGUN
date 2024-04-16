package objects.player;

import controller.GameController;
import objects.Enemy.Enemy;
import objects.boss.Boss;
import view.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerAttack implements Runnable {
	private PlayerAttack playerAttack = this;
	private List<Enemy> enemies;
	private Boss boss;

	Image playerBulletImg1 = new ImageIcon("img/playerBullet1.png").getImage();
	Image playerBulletImg2 = new ImageIcon("img/bullet1.png").getImage();
	Image playerBulletImg3 = new ImageIcon("img/bullet3.png").getImage();

	private boolean collision;
	private int x;
	private int y;
	private double angle; // 총알 각도
	private double speed; // 총알 속도
	private int width;
	private int height;

	public PlayerAttack(Boss boss, List<Enemy> enemies, int x, int y, double bulletAngle, double bulletSpeed) {

		if (boss != null) {
			this.boss = boss;
		}

		this.enemies = enemies;
		this.x = x;
		this.y = y;
		this.angle = bulletAngle;
		this.speed = bulletSpeed;

		collision = false;

		Thread bulletthread = new Thread(this);
		bulletthread.setName("PlayerBullet");
		bulletthread.start();

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

	public void Fire() {
		x -= Math.cos(Math.toRadians(angle)) * speed;
		y -= Math.sin(Math.toRadians(angle)) * speed;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (boss != null && boss.getHp() > 0) {

			crash();

			try {
				if (collision) {
					y = -100;
					boss.setHp(boss.getHp() - 1);
				}

				if (boss.getHp() == 0) {
					explosePlayer(boss); // 충돌 폭발 메서드
					GameController.getInstance().gameOver(true);
				}

				Thread.sleep(10);

				if (x > 1000 || x < -500 || y < -100 || y > 1000) {
					return; // Thread 종료
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public Enemy processCrash() {
		List<Integer> toRemove = new ArrayList<>();
		for (int i = 0; i < enemies.size(); i++) {
			Enemy unit = enemies.get(i);

			if (crashed(unit)) {
				unit.setLife(unit.getLife() - 1);

				if (unit.getLife() <= 0) {
					toRemove.add(i);
					unit.setCrushCheck(true);
				}
			}
		}

		Enemy removedEnemy = null;
		for (int i : toRemove) {
			removedEnemy = enemies.remove(i);
		}
		return removedEnemy;
	}

	// 사용자 총알이 적기에 닿았는지
	private boolean crashed(Enemy unit) {
		if (Math.abs((this.getX() + this.getWidth() / 2) - (unit.getX() + unit.getWidth() / 2)) < (unit.getWidth() / 2 + this.getWidth() / 2)
				&& Math.abs((this.getY() + this.getHeight() / 2) - (unit.getY() + unit.getHeight() / 2)) < (unit.getHeight() / 2 + this.getHeight() / 2))
			return true;
		else
			return false;
	}

	public void crash() {
		if (Math.abs(((boss.getX()) + boss.getWidth() / 2) - (x + width / 3)) < (width / 3 + boss.getWidth() / 3)
				&& Math.abs(((boss.getY()) + boss.getHeight() / 2) - (y + height / 3)) < (height / 3
						+ boss.getHeight() / 3)) {
			collision = true;
		} else {
			collision = false;
		}
	}

	public void explosePlayer(Boss boss) {
		try {
			ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
			boss.imgBoss = explosionIcon.getImage();
			Thread.sleep(3000);

			System.out.println("보스 die~");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
