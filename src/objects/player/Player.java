package objects.player;

import objects.Enemy.Enemy;
import objects.Enemy.Enemy1;
import objects.Enemy.Enemy2;
import objects.Enemy.Enemy3;
import objects.boss.Boss;
import service.UserService;
import session.UserSession;
import view.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends JLabel {
	public UserService userService;
	public UserSession userSession;

	public Player player = this;

	private GameFrame gameFrame;
	private List<Enemy> enemies = new ArrayList<>();
	private Boss boss;

	private ImageIcon playerIcon; // 기본 아이콘
	private ImageIcon playerInvincibleIcon; // 무적 상태 시 아이콘

	private int width = 70;
	private int height = 65;
	private int x = (GameFrame.SCREEN_WIDTH / 2) - (width / 2);
	private int y = (GameFrame.SCREEN_HEIGHT - (height * 2));
	private int life = 3;
	private int bulletSpeed; // 총알 발사 속도
	private boolean invincible; // 무적 상태

	private int weaponLevel = 0;
	public int fighterType; // player 선택

	private boolean isAlive = true; // 스레드 생명
	public boolean isRight = false;
	public boolean isLeft = false;
	public boolean isUp = false;
	public boolean isDown = false;
	public boolean isAttack = false;
	public boolean isWeaponLevelUp = false;

	public int score = 0;

	private PlayerAttack playerAttack;
	public List<PlayerAttack> playerBullets = new ArrayList<>();

	public Player(GameFrame gameFrame, String PLANE) {

		this.userService = new UserService();
		this.userSession = UserSession.getInstance();

		this.gameFrame = gameFrame;
		playerIcon = new ImageIcon("img/Player" + PLANE + ".png");
		playerInvincibleIcon = new ImageIcon("img/" + PLANE + "무적.png");

		this.invincible = false;

		System.out.println("img/Player" + PLANE + ".png");
		if (PLANE.equals("PLANE1")) {
			fighterType = 1;
		}
		if (PLANE.equals("PLANE2")) {
			fighterType = 2;
		}
		if (PLANE.equals("PLANE3")) {
			fighterType = 3;
		}

		setIcon(playerIcon);
		move();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}


	public boolean getInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public ImageIcon getPlayerIcon() {
		return playerIcon;
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

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}


	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}


	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}


	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}


	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

	public void setAttack(boolean isAttack) {
		this.isAttack = isAttack;
	}


	public ImageIcon getPlayerInvincibleIcon() {
		return playerInvincibleIcon;
	}


	public void move() {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				bulletSpeed = 0; // count를 0으로 설정
				while (isAlive) {
					try {
						Thread.sleep(5);
						gameOver();
						keyProcess();
						playerAttackProcess();
						resolveCrash(); // 총알과 적기의 충돌 처리
						setLocation(x, y); // repaint()
						setSize(width, height);
						bulletSpeed++; // 1씩 늘어난다
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t1.setName("Player");
		t1.start();
	}

	private void keyProcess() {
		if (!invincible && isAttack && bulletSpeed % 30 == 0) { // 총알의 발사 속도를 조절
			if (weaponLevel == 0) { // 총알 한줄만 발사
				// 총알이 생성되는 위치, 각도, 발사속도 조절
				playerAttack = new PlayerAttack(boss, enemies, x + 20, y - 40, 90, 2);
				playerBullets.add(playerAttack); // arrayList에 저장한다
			}
			if (weaponLevel == 1) { // 총알 2줄 발사
				playerAttack = new PlayerAttack(boss, enemies, x + 10, y - 40, 90, 2);
				playerBullets.add(playerAttack);
				playerAttack = new PlayerAttack(boss, enemies, x + 30, y - 40, 90, 2);
				playerBullets.add(playerAttack);
			}
			if (weaponLevel == 2) { // 총알 3줄 발사
				playerAttack = new PlayerAttack(boss, enemies, x + 0, y - 40, 90, 2);
				playerBullets.add(playerAttack);
				playerAttack = new PlayerAttack(boss, enemies, x + 20, y - 40, 90, 2);
				playerBullets.add(playerAttack);
				playerAttack = new PlayerAttack(boss, enemies, x + 40, y - 40, 90, 2);
				playerBullets.add(playerAttack);
			}
			if (weaponLevel == 3) { // 총알 4줄 발사
				playerAttack = new PlayerAttack(boss, enemies, x - 10, y - 40, 90, 2);
				playerBullets.add(playerAttack);
				playerAttack = new PlayerAttack(boss, enemies, x + 10, y - 40, 90, 2);
				playerBullets.add(playerAttack);
				playerAttack = new PlayerAttack(boss, enemies, x + 30, y - 40, 90, 2);
				playerBullets.add(playerAttack);
				playerAttack = new PlayerAttack(boss, enemies, x + 50, y - 40, 90, 2);
				playerBullets.add(playerAttack);
			}
			if (weaponLevel == 4) { // 양 옆 대각선으로 나가는 총알 2줄 추가
				playerAttack = new PlayerAttack(boss, enemies, x - 15, y - 40, 80, 2);
				playerBullets.add(playerAttack);
				playerAttack = new PlayerAttack(boss, enemies, x - 10, y - 40, 90, 2);
				playerBullets.add(playerAttack);
				playerAttack = new PlayerAttack(boss, enemies, x + 10, y - 40, 90, 2);
				playerBullets.add(playerAttack);
				playerAttack = new PlayerAttack(boss, enemies, x + 30, y - 40, 90, 2);
				playerBullets.add(playerAttack);
				playerAttack = new PlayerAttack(boss, enemies, x + 50, y - 40, 90, 2);
				playerBullets.add(playerAttack);
				playerAttack = new PlayerAttack(boss, enemies, x + 55, y - 40, 100, 2);
				playerBullets.add(playerAttack);
			}
		}

		if (isUp) {
			y--;
		}
		if (isDown) {
			y++;
		}
		if (isLeft) {
			x--;
		}
		if (isRight) {
			x++;
		}
	}

	public void playerUpdate(Graphics g) { // 플레이어에 관한 모든 그림은 여기서 정리한다
		playerDraw(g);
	}

	public void playerDraw(Graphics g) {
		for (int i = 0; i < playerBullets.size(); i++) {
			playerAttack = playerBullets.get(i);
			if (fighterType == 1) {
				g.drawImage(playerAttack.playerBulletImg1, (int) playerAttack.getX(), (int) playerAttack.getY(), null);
			}
			if (fighterType == 2) {
				g.drawImage(playerAttack.playerBulletImg2, (int) playerAttack.getX(), (int) playerAttack.getY(), 20, 20,
						null);
			}
			if (fighterType == 3) {
				g.drawImage(playerAttack.playerBulletImg3, (int) playerAttack.getX(), (int) playerAttack.getY(), 20, 20,
						null);
			}
			// PlayaerAttack의 자료형을 double로 두고, drawImage를 돌릴 때만 형변환 해준다 (삼각함수 계산을 위해)
		}
	}

	private void playerAttackProcess() {
		for (int i = 0; i < playerBullets.size(); i++) {
			playerAttack = playerBullets.get(i);
			playerAttack.Fire();
		}
	}

	public void setWeaponLevelUp(boolean isWepponLevelUp) {
		this.isWeaponLevelUp = isWepponLevelUp;
		if (isWepponLevelUp == true && weaponLevel < 6) {
			weaponLevel = weaponLevel + 1;
			System.out.println("무기 레벨 : " + weaponLevel);
			if (weaponLevel == 4) {
				System.out.println("무기 레벨이 최대입니다");
			} else if (weaponLevel == 5) {
				weaponLevel = 0;
			}
		}
	}

	public void addEnemyContext(Enemy enemyUnit) { // 동적으로 생성된 적의 컨텍스트를 받아온다.

		if (enemyUnit != null)
			this.enemies.add(enemyUnit);
	}

	private void resolveCrash() {
		List<Integer> toRemove = new ArrayList<>();
		for (int i = 0; i < playerBullets.size(); i++) {
			PlayerAttack bullet = playerBullets.get(i);
			Enemy crashedEnemy;
			if ((crashedEnemy = bullet.processCrash()) != null) {
				toRemove.add(i);
				addScore(crashedEnemy);
			}
		}

		// 총알 요소 제거
		for (int i : toRemove) {
			playerBullets.remove(i);
		}
	}

	private void addScore(Enemy enemy) {
		if (enemy instanceof Enemy1) {
			score += 100;
		} else if (enemy instanceof Enemy2) {
			score += 200;
		} else if (enemy instanceof Enemy3) {
			score += 300;
		}
	}


	private void gameOver() {
		if (life <= 0) {
			userService.saveBestScore(userSession.getLoggedInUserId(), score);

			isAlive = false; //dispose 해도 안의 쓰레드는 살아있다...  이 명령 추가.. 그냥 완전 다 삭제해주는 함수는 없나...
			gameFrame.isgame = false;
			gameFrame.dispose();
			new GameFrame();
		}

	}

	public void addBossContext(Boss boss) {
		if(boss != null) {
			this.boss = boss;
		}
	}
}
