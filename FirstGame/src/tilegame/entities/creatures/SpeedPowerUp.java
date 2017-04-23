package tilegame.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

import tilegame.gfx.Assets;

public class SpeedPowerUp extends Creature {
	private Rectangle bounds;
	
	public int test;

	public SpeedPowerUp(float x, float y,int randoms) {
		super(x, y, 100, 100);
		bounds = new Rectangle();
		bounds.x = (int) getX();
		bounds.y = (int) getY();
		bounds.height = getHeight();
		bounds.width = getWidth();
		test = randoms;
//		random = generateRandomPowerUp();
		

	}
	@Override
	public void tick() {
		yMove = 5;
		move();
		setBounds();

	}

	@Override
	public void render(Graphics g) {
		if (test == 1) {
			g.drawImage(Assets.nuke, (int) x, (int) y, width, height, null);
		} else if (test == 2) {
			g.drawImage(Assets.timeSlow, (int) x, (int) y, width, height, null);
		} else if (test == 3) {
			g.drawImage(Assets.lightning, (int) x, (int) y, width, height, null);
		} else if (test == 4) {
			g.drawImage(Assets.snail, (int) x, (int) y, width, height, null);
		} else if (test == 5) {
			g.drawImage(Assets.health, (int) x, (int) y, width, height, null);
		}else if(test == 6){
			g.drawImage(Assets.ghost, (int) x, (int) y, width, height, null);
		}
			
		// TODO : finn bilder for alle powerups.
	}

	public void setBounds() {
		bounds.x = (int) getX();
		bounds.y = (int) getY() -30;
		bounds.height = (int) getHeight() - 20;
		bounds.width = (int) getWidth() - 10;
	}

	public boolean isSolid(boolean i) {
		if (i) {
			return true;

		} else {
			return false;
		}
	}
	public static BufferedImage getPowerUpSymbol(int i){
		if (i == 1) {
			return Assets.nuke;
		} else if (i == 2) {
			return Assets.timeSlow;
		} else if (i == 3) {
			return Assets.lightning;
		} else if (i == 4) {
			return Assets.snail;
		} else if (i == 5) {
			return Assets.health;
		}else if (i == 6){
			return Assets.ghost;
		}else{
			return Assets.questionMark;
		}
	}

	public int getPowerUp() {
		return test;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int generateRandomPowerUp() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 6);
		return randomNum;
	}

}
