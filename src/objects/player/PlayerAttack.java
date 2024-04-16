package objects.player;

import objects.Enemy.EnemyEntity;
import objects.boss.Boss;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerAttack implements Runnable {
	private PlayerAttack playerAttack = this;
	private List<EnemyEntity> enemyUnits;
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
	private boolean islife; // Thread를 삭제시키기 위한 구문

	public PlayerAttack() {
		// TODO Auto-generated constructor stub
	}

	public PlayerAttack(Boss boss, List<EnemyEntity> enemyUnits, int x, int y, double bulletAngle, double bulletSpeed) {

		if (boss != null) {
			this.boss = boss;
		}

		this.enemyUnits = enemyUnits;
		this.x = x;
		this.y = y;
		this.angle = bulletAngle;
		this.speed = bulletSpeed;

		collision = false;

		Thread bulletthread = new Thread(this); // 총알 충돌 thread 생성, 실행
		bulletthread.setName("PlayerBullet");
		bulletthread.start();

	}

	public PlayerAttack getPlayerAttack() {
		return playerAttack;
	}

	public void setPlayerAttack(PlayerAttack playerAttack) {
		this.playerAttack = playerAttack;
	}


	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
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

	public void setY(int y) {
		this.y = y;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
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
		while (boss != null && boss.getHp() > 0) { // 생명이 0보다 크면

			crash();

			try {
				if (collision) {
					y = -100;
					boss.setHp(boss.getHp() - 1);

				}

				if (boss.getHp() == 0) {
					explosePlayer(boss); // 충돌 폭발 메서드
				}

				Thread.sleep(10);

				if (x > 1000 || x < -500 || y < -100 || y > 1000) {
					// System.out.println("bullet thread terminate");
					return; // Thread 종료구문
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public boolean processCrash() {
		List<Integer> toRemove = new ArrayList<>();
		for (int i = 0; i < enemyUnits.size(); i++) {
			EnemyEntity unit = enemyUnits.get(i);

			if (crashed(unit)) {

				System.out.println("하기 전: " + unit.crushCheck);
				unit.setLife(unit.getLife() - 1);

				if (unit.life >= 0) {
					System.out.println("적 hp 0 됨");
					toRemove.add(i);
					unit.crushCheck = true;

				}
			}
		}

		for (int i : toRemove) {
			enemyUnits.remove(i);
		}
		return !toRemove.isEmpty();
	}

	// 플레이어 총알이 적의 비행기에 닿았는지 탐지하는 연산
	private boolean crashed(EnemyEntity unit) {
		// x,y : 위치값 , w,h : 이미지의 높이와 길이.
		if (Math.abs((this.getX() + this.getWidth() / 2) - (unit.x + unit.width / 2)) < (unit.width / 2 + this.getWidth() / 2)
				&& Math.abs((this.getY() + this.getHeight() / 2) - (unit.y + unit.height / 2)) < (unit.height / 2 + this.getHeight() / 2))
			return true;
		else
			return false;
	}


	public void crash() { // 플레이어 총알이 보스에 부딪쳤을 시 충돌연산
		if (Math.abs(((boss.getX()) + boss.getWidth() / 2) - (x + width / 3)) < (width / 3 + boss.getWidth() / 3)
				&& Math.abs(((boss.getY()) + boss.getHeight() / 2) - (y + height / 3)) < (height / 3
						+ boss.getHeight() / 3)) {
			collision = true;
		} else {
			collision = false;
		}
	}

	public void explosePlayer(Boss boss) { // 충돌후 이미지 변경 및 목숨카운트
		try {
			ImageIcon explosionIcon = new ImageIcon("img/explosion.gif");
			boss.imgBoss = explosionIcon.getImage();
			Thread.sleep(3000);

			System.out.println("보스 처치!!");
			System.exit(1); // 프로그램 종료

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
