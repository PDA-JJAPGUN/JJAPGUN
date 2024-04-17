package objects.player;

import controller.GameController;
import objects.Enemy.Enemy;
import objects.boss.Boss;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerAttack implements Runnable {
	private List<Enemy> enemies;
	private Boss boss;

	Image playerBulletImg1 = new ImageIcon("images/playerBullet1.png").getImage();
	Image playerBulletImg2 = new ImageIcon("images/bullet1.png").getImage();
	Image playerBulletImg3 = new ImageIcon("images/bullet3.png").getImage();
	private int x;
	private int y;
	private double angle;
	private double speed;
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

	public void fire() {
		x -= Math.cos(Math.toRadians(angle)) * speed;
		y -= Math.sin(Math.toRadians(angle)) * speed;
	}

	@Override
	public void run() {
		while (boss != null && boss.getHp() > 0) {
			try {
				if (isCrahsedBoss()) {
					y = -100;
					boss.setHp(boss.getHp() - 1);
				}

				if (boss.getHp() == 0) {
					explodeBoss(boss);
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

			if (isCrashedEnemy(unit)) {
				unit.setHp(unit.getHp() - 1);

				if (unit.getHp() <= 0) {
					toRemove.add(i);
					unit.setCrashCheck(true);
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
	public boolean isCrashedEnemy(Enemy unit) {
		if (Math.abs((this.getX() + this.getWidth() / 2) - (unit.getX() + unit.getWidth() / 2)) < (unit.getWidth() / 2 + this.getWidth() / 2)
				&& Math.abs((this.getY() + this.getHeight() / 2) - (unit.getY() + unit.getHeight() / 2)) < (unit.getHeight() / 2 + this.getHeight() / 2))
			return true;
		else
			return false;
	}

	public boolean isCrahsedBoss() {
		if (Math.abs(((boss.getX()) + boss.getWidth() / 2) - (x + width / 3)) < (width / 3 + boss.getWidth() / 3)
				&& Math.abs(((boss.getY()) + boss.getHeight() / 2) - (y + height / 3)) < (height / 3
						+ boss.getHeight() / 3)) {
			return true;
		} else {
			return false;
		}
	}

	public void explodeBoss(Boss boss) {
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
