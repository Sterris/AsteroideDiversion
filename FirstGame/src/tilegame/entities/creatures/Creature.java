package tilegame.entities.creatures;

import java.awt.Rectangle;

import tilegame.entities.Entity;

public abstract class Creature extends Entity {

	public static final int DEFAULT_HEALTH = 4;
	public static final float DEFAULT_SPEED = 9f; // skal være 5?
	public static final int DEFAULT_CREATURE_WIDTH = 120, DEFAULT_CREATURE_HEIGHT = 100;

	protected int health;
	protected float speed;
	protected float xMove, yMove;

	public Creature(float x, float y, int width, int height) {
		super(x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		bounds = new Rectangle();
	}

	public void move() {
		x += xMove;
		y += yMove;
	}

	public boolean isSolid() {
		return true;
	}

	// Getters and setters

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setBounds() {
		bounds.x = (int) getX();
		bounds.y = (int) getY();
		bounds.height = getHeight();
		bounds.width = width;
	}

}
