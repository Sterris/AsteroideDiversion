package tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage player, background, enemy, groundRock, purpleRock, hexagons, kerning, herman, playerFly, playerRight;
	public static BufferedImage sideEnemy, healthBarFull, healthBarLow, healthBarMedL, healthBarMedH, mamma, nuke, blank;
	public static BufferedImage heart, playerDie, gameOver, health, snail, lightning, timeSlow, questionMark, ghost, paused;
	public static BufferedImage flyLeft, keys, star, hedvig, explosion, purpleExplosion, animatedExplosion, blueExplosion, blueRock;
	public static BufferedImage dust;
	public static void init(){
		player = ImageLoader.loadImage("/textures/char_idle_1.png");
		background = ImageLoader.loadImage("/textures/background.png");
		enemy = ImageLoader.loadImage("/textures/enemy_1.png");
		groundRock = ImageLoader.loadImage("/textures/groundRock.png");
		purpleRock = ImageLoader.loadImage("/textures/purple_rock.png");
		hexagons = ImageLoader.loadImage("/textures/hexagons.png");
		kerning = ImageLoader.loadImage("/textures/kerning.png");
		herman = ImageLoader.loadImage("/textures/herman.png");
		playerFly = ImageLoader.loadImage("/textures/fly.png");
		playerRight = ImageLoader.loadImage("/textures/playerRight.png");
		sideEnemy = ImageLoader.loadImage("/textures/side_enemy.png");
//		healthBarFull = ImageLoader.loadImage("/textures/h_bar_full.png");
//		healthBarLow = ImageLoader.loadImage("/textures/h_bar_low.png");
//		healthBarMedL = ImageLoader.loadImage("/textures/h_bar_half-.png");
//		healthBarMedH = ImageLoader.loadImage("/textures/h_bar_half+.png");
		mamma = ImageLoader.loadImage("/textures/mamma.jpg");
		nuke =ImageLoader.loadImage("/textures/nuke.png");
		heart = ImageLoader.loadImage("/textures/heart.png");
		playerDie = ImageLoader.loadImage("/textures/char_die.png");
		gameOver = ImageLoader.loadImage("/textures/Game_Over.png");
		snail = ImageLoader.loadImage("/textures/snail.png");
		lightning = ImageLoader.loadImage("/textures/lightning.png");
		health = ImageLoader.loadImage("/textures/health.png");
		timeSlow = ImageLoader.loadImage("/textures/timeSlow.png");
		questionMark = ImageLoader.loadImage("/textures/questionMark.png");
		ghost = ImageLoader.loadImage("/textures/ghost.png");
		paused = ImageLoader.loadImage("/textures/Paused.png");
		flyLeft = ImageLoader.loadImage("/textures/fly_left.png");
		blank = ImageLoader.loadImage("/textures/Blank.png");
		keys = ImageLoader.loadImage("/textures/keys.png");
		star = ImageLoader.loadImage("/textures/star.png");
		hedvig = ImageLoader.loadImage("/textures/hedvig.jpg");
		explosion = ImageLoader.loadImage("/textures/explosion.png");
		purpleExplosion = ImageLoader.loadImage("/textures/pinkExplosion.png");
		animatedExplosion = ImageLoader.loadImage("/textures/animatedExplosion.png");
		blueExplosion = ImageLoader.loadImage("/textures/blueExplosion.png");
		blueRock = ImageLoader.loadImage("/textures/blueRock.png");
		dust = ImageLoader.loadImage("/textures/dust2.png");
	}

}
