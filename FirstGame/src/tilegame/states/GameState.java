package tilegame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import tilegame.Game;
import tilegame.entities.creatures.Enemy;
import tilegame.entities.creatures.Player;
import tilegame.entities.creatures.PowerUps;
import tilegame.entities.creatures.SpeedPowerUp;
import tilegame.gfx.Assets;

public class GameState extends State {

	// TODO: SoundEffects not working properly, puts threread to sleep while
	// playing sound..
	// is there a way to go around multhithreading??

	// Creatures, ArrayList with creatures.
	private Player player;
	private SpeedPowerUp test; // Bad name
	public static ArrayList<Enemy> enemyCollection = new ArrayList<>();
	private ArrayList<SpeedPowerUp> powerUpCollection = new ArrayList<>(); // Making
																			// it
																			// an
																			// arraylist
																			// so
																			// its
																			// easy
																			// to
																			// remove
																			// after
																			// hit
																			// or
																			// falling
																			// down.
	private Enemy testExplosion = new Enemy(game, 2000, 500);

	// Game helpers
	public int temp = 0;
	public boolean fromGameOver = false;
	private boolean isPoweredUp = false;
	public static int stoneCounter = 0;
	public static int level = 1;
	private int counter = 0;
	private ArrayList<Integer> starY = new ArrayList<>(); // Arraylist cus
															// laziness.
	private boolean isHit = false;
	private int isHitCounter = 0;
	private boolean hidePlayer = false;
	private boolean isNuked = false;
	// private float dustCounter;

	public GameState(Game game) {
		super(game);

		// Spawning a powerup, adding to ArrayList
		randomYstar(20);
		for (int i = 0; i < 1; i++) {
			test = new SpeedPowerUp(generateRandomXNumber(), generateRandomYNumber(), 3);
			powerUpCollection.add(test);
		}
		player = new Player(game, 600, 500);

		// Spawning 10 enemies.
		generateEnemies(10);

	}

	@Override
	public void tick() {

		// Starting new game, if last state was GameOver
		if (fromGameOver) {
			newGame();
			fromGameOver = false;
		}

		player.tick();

		// Wall actions
		verticalTeleportandHealthDown();
		horizontalTeleport();

		// Checking if powerUp is below screen, then removing.
		// Else, checking collision, then setting vars accordingly and removing
		// powerUp.
		for (int i = 0; i < powerUpCollection.size(); i++) {
			powerUpCollection.get(i).tick();
			if (powerUpCollection.get(i).getY() > 1000) {
				powerUpCollection.remove(i);
			} else if (player.getBounds().intersects(powerUpCollection.get(i).getBounds())) {
				counter = 0; // reset counter so powerup becomes temp.
				isPoweredUp = true;
				temp = powerUpCollection.get(0).getPowerUp(); // getting the
																// randomated
																// number for
																// the powerup
				if (powerUpCollection.get(i).getPowerUp() == 1) {
					isNuked = true;
					PowerUps.nuke(enemyCollection);

				}
				if (powerUpCollection.get(i).getPowerUp() == 2) {
					PowerUps.slowEnemy(enemyCollection);
				}
				if (powerUpCollection.get(i).getPowerUp() == 3) {
					player.setSpeed(13);
				}
				if (powerUpCollection.get(i).getPowerUp() == 4) {
					player.setSpeed(2);
				}
				if (powerUpCollection.get(i).getPowerUp() == 5) {
					PowerUps.fullHealth(player);
				}
				if (powerUpCollection.get(i).getPowerUp() == 6) {
					PowerUps.invisible(player);

				}

				powerUpCollection.remove(i);
			}
		}
		// Help to only make a powerUp temporary.
		if (isPoweredUp) {
			counter = counter + 1;
		}

		// Spawning new powerUp if none exist.
		if (powerUpCollection.isEmpty()) {
			generatePowerUp();
		}
		// Restoring player defaults after 6 sec.
		if ((counter / 60) > 6) {
			player.restoreDefaults();
			isPoweredUp = false;
			isNuked = false;
			if (!PowerUps.nukeExplosion.isEmpty()) {
				while (!PowerUps.nukeExplosion.isEmpty()) {
					PowerUps.nukeExplosion.removeAll(PowerUps.nukeExplosion);
				}
			}
		}

		// Pause
		if (game.getKeyManager().pause) {
			GameState.setState(game.pauseState);
		}

		// Making player untouchable after beeing hit.
		if (isHit) {
			hidePlayer = true;

			if (isHitCounter / 60 < 1) {
				for (int i = 0; i < enemyCollection.size(); i++) {
					enemyCollection.get(i).setSolid(false);
				}
				isHitCounter += 1;
			} else {
				isHitCounter = 0;
				isHit = false;
				hidePlayer = false;
				for (int i = 0; i < enemyCollection.size(); i++) {
					enemyCollection.get(i).setSolid(true);
				}
			}
		}
		// Cheat
		if (game.getKeyManager().space && CreditsState.cheat) {
			PowerUps.fullHealth(player);
		}

		// Return to main menu
		if (game.getKeyManager().escape) {
			GameState.setState(game.menuState);
		}

		// Level system.
		if (enemyCollection.size() == 0) {
			level = level + 1;
			generateEnemies(level * 3 + 10);
		}
		// Removes rocks if y = 900.
		for (int i = 0; i < enemyCollection.size(); i++) {
			enemyCollection.get(i).tick();
			if (enemyCollection.get(i).getY() >= 900) {
				enemyCollection.remove(enemyCollection.indexOf(enemyCollection.get(i)));
				stoneCounter += 1;
				continue;
			}
			// Checking if collision, -1 hp. Sets the state to gameOver if hp =
			// 0.
			if (player.getBounds().intersects(enemyCollection.get(i).getBounds())
					&& enemyCollection.get(i).getSolid()) {

				// Puts thread to sleep... have to multhithread..
				// bombTest.playSound("Res/textures/Bomb_Exploding-Sound_Explorer-68256487.wav");

				testExplosion = enemyCollection.get(i);

				enemyCollection.remove(enemyCollection.indexOf(enemyCollection.get(i)));
				player.setHealth(player.getHealth() - 1);
				isHit = true;
				if (player.getHealth() == 0) {

					fromGameOver = true;
					GameState.setState(game.gameOverState);
				}
			}
		}

	}

	@Override
	public void render(Graphics g) {
		// Background
		g.drawImage(Assets.background, 0, 0, 1300, 1300, null);

		// Moving dust downwards
		// drawDust(g);

		// Draw stars
		drawStars(g);

		// Draws explosion if player is hit
		if (isHit) {
			drawExplosion(g, (int) testExplosion.getX(), (int) testExplosion.getY(), testExplosion.getWidth(),
					testExplosion.getHeight());
		}

		// Draws healthbar
		drawHearts(g);

		// Rendering player, enemies and power up
		if (isHit) {
			if (isHitCounter < 0) {
				player.render(g);
			} else {
				switch (isHitCounter / 10) {
				case 0:
					break;
				case 1:
					player.render(g);
					break;
				case 2:
					break;
				case 3:
					player.render(g);
					break;
				case 4:
					break;
				case 5:
					player.render(g);
					break;
				case 6:
					break;
				case 7:
					player.render(g);
					break;
				case 8:
					break;
				}
			}
		}
		// Draws explosions if nuked
		if (isNuked) {
			drawNuke(PowerUps.nukeExplosion, g);
		}

		//if not hit, render normal, if hit render with blink.
		if (!hidePlayer) {
			player.render(g);
		}

		for (int i = 0; i < powerUpCollection.size(); i++) {
			powerUpCollection.get(i).render(g);
		}
		for (int i = 0; i < enemyCollection.size(); i++) {
			enemyCollection.get(i).render(g);
		}

		// Showing fps, levelcounter, temporary powerup, and asteroids left.
		if (isPoweredUp) {
			g.drawImage(SpeedPowerUp.getPowerUpSymbol(temp), 20, 730, 80, 80, null);
			// if (counter >= 360 && ((powerUpCollection.get(0).getPowerUp() ==
			// 6)
			// || (powerUpCollection.get(0).getPowerUp() == 3) ||
			// (powerUpCollection.get(0).getPowerUp() == 4))) {
			// g.setColor(Color.red);
			// Font fntMik = new Font("Cambria", Font.BOLD, 24);
			// g.setFont(fntMik);
			// g.drawString(420 - counter + "", 120, 750);
			// }

		}
		g.setColor(Color.white);
		g.drawString("FPS:" + Game.fpser, 0, 20);
		Font fnt1 = new Font("calibri", Font.PLAIN, 28);
		g.setFont(fnt1);
		g.drawString("Asteroids left: " + enemyCollection.size(), 1090, 730);
		g.drawString("Level " + level, 20, 730);

	}

	// Generate enemies, powerup, and random numbers
	private ArrayList<Enemy> generateEnemies(int i) {
		for (int j = 0; j < i; j++) {
			Enemy tracker = new Enemy(game, generateRandomXNumber(), generateRandomYNumber() - 2000);
			tracker.setyMove(generateRandomSpeed() + level / 3);
			int size = generateRandomSize();
			tracker.setWidth(size);
			tracker.setHeight(size);
			enemyCollection.add(tracker);
		}
		return enemyCollection;
	}

	private ArrayList<SpeedPowerUp> generatePowerUp() {
		SpeedPowerUp randomPowerUp = new SpeedPowerUp(generateRandomXNumber(), generateRandomYNumber() - 2000,
				generateRandomPowerUp());
		powerUpCollection.add(randomPowerUp);
		return powerUpCollection;
	}

	private int generateRandomSize() {
		int randomNum = ThreadLocalRandom.current().nextInt(50, 165); // 50,180
		return randomNum;
	}

	private int generateRandomSpeed() {
		int randomNum = ThreadLocalRandom.current().nextInt(4, 9);
		return randomNum;
	}

	private int generateRandomYNumber() {
		int randomNum = ThreadLocalRandom.current().nextInt(-3500, -300);
		return randomNum;
	}

	// private int generateRandomDustY() {
	// int randomYDust = ThreadLocalRandom.current().nextInt(-200, 800);
	// return randomYDust;
	// }

	private int generateRandomYStar() {
		int starY = ThreadLocalRandom.current().nextInt(0, 200);
		return starY;
	}

	private int generateRandomXNumber() {
		int randomNum = ThreadLocalRandom.current().nextInt(-50, 1200);
		return randomNum;
	}

	private int generateRandomPowerUp() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 7);
		return randomNum;
	}

	private ArrayList<Integer> randomYstar(int i) {

		for (int j = 0; j < i; j++) {
			starY.add(generateRandomYStar());
		}
		return starY;
	}

	// Removing all rocks, and restoring everything
	public void newGame() {
		while (!enemyCollection.isEmpty()) {
			enemyCollection.removeAll(enemyCollection);
		}
		player.restoreDefaults();
		player.setHealth(4);
		player.setX(600);
		player.setY(500);
		stoneCounter = 0;
		// GameOverState.hasPlayed = false;
		// GameOverState.counter = 0;
		level = 1;
		powerUpCollection.remove(0);
		generateEnemies(10);
		generatePowerUp();
	}

	// Teleports

	// Horizontal teleport
	private void horizontalTeleport() {

		if (player.getX() < -50) {
			player.setX(1191);
		}
		if (player.getX() > 1200) {
			player.setX(-30);
		}

	}

	// Verical teleport and health down
	// on player when player touches ground.
	private void verticalTeleportandHealthDown() {
		if (player.getY() <= -300) {
			player.setY(700);
		}
		if (player.getY() > 820 + player.getHeight()) {
			player.setY(500);
			player.setHealth(player.getHealth() - 1);
			testExplosion.setX(2000);
			if (player.getHealth() == 0) {
				fromGameOver = true;
				GameState.setState(game.gameOverState);

			}
			isHit = true;
		}
	}

	// Draw methods
	private void drawExplosion(Graphics g, int x, int y, int width, int height) {
		g.drawImage(Assets.animatedExplosion, x, y, width, height, null); // Width
																			// og
																			// height
																			// var
																			// +40
	}

	private void drawNuke(ArrayList<Enemy> nukedRocks, Graphics g) {

		for (int r = 0; r < nukedRocks.size(); r++) {
			g.drawImage(Assets.explosion, (int) PowerUps.nukeExplosion.get(r).getX(),
					(int) PowerUps.nukeExplosion.get(r).getY(), PowerUps.nukeExplosion.get(r).getWidth(),
					PowerUps.nukeExplosion.get(r).getHeight(), null);
		}
	}

	private void drawStars(Graphics g) {

		for (int i = 0; i < 19; i++) {
			g.drawImage(Assets.star, i * 65, starY.get(i), 20, 20, null);
		}
	}

	// private void drawDust(Graphics g) {
	// dustCounter += (1 + level / 1200);
	// for (int numberOfDust = 0; numberOfDust > 20; numberOfDust++) {
	// g.drawImage(Assets.dust, generateRandomXNumber(), generateRandomDustY() +
	// (int) dustCounter, 200, 200, null);
	// }
	// }

	private void drawHearts(Graphics g) {
		int health = player.getHealth();
		int heartSpace = 0;
		for (int i = 0; i < health; i++) {
			g.drawImage(Assets.heart, 1240 + heartSpace, 750, 50, 50, null);
			heartSpace -= 50;
		}
	}
}
