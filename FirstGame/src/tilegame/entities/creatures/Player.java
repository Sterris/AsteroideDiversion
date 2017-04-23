package tilegame.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;

import tilegame.Game;
import tilegame.gfx.Assets;

public class Player extends Creature {

	private Game game;
	private Rectangle bounds;
	private boolean right;

	//Sets the bounds accordingly to the players width and height.
	public Player(Game game, float x, float y) {
		super(400, 300, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		bounds = new Rectangle();
		bounds.x = (int) getX();
		bounds.y = (int) getY();
		bounds.height = getHeight();
		bounds.width = width;
		this.game = game;

	}

	@Override
	public void tick() {
		getInput();
		move();
		setBounds();

	}

	private void getInput() {
		xMove = 0;
		yMove = 2; //Making player fall with constant speed 2.

		//Input gives speed.
		if (game.getKeyManager().up)
			yMove = -speed;
		if (game.getKeyManager().down)
			yMove = speed;
		if (game.getKeyManager().right)
			xMove = speed;
		if (game.getKeyManager().left)
			xMove = -speed;
	}

	public void render(Graphics g) {
		// Correctly displaying player
		if (xMove > 0) {
			right = true;
		}
		if (yMove < 0 && !right) {
			g.drawImage(Assets.flyLeft, (int) x, (int) y, 150, 150, null);
		} else if (yMove < 0) {
			g.drawImage(Assets.playerFly, (int) x, (int) y, 150, 150, null);
		} else if (xMove < 0) {
			g.drawImage(Assets.playerRight, (int) x, (int) y, 150, 150, null);
			right = false;

		} else if (!right) {
			g.drawImage(Assets.playerRight, (int) x, (int) y, 150, 150, null);
		} else {
			g.drawImage(Assets.player, (int) x, (int) y, 150, 150, null);
			
			
		}

	}

	public Rectangle getBounds() {
		return bounds;
	}

	//Fine tuning bounds for collision.
	public void setBounds() {
		bounds.x = (int) getX() + 10;
		bounds.y = (int) getY();
		bounds.height = (int) getHeight() - 10;
		bounds.width = (int) getWidth() - 30; //-23
	}

	public void restoreDefaults() {
		setSpeed(DEFAULT_SPEED);
		setHeight(DEFAULT_CREATURE_HEIGHT);
		setWidth(DEFAULT_CREATURE_WIDTH);
		// setBounds();
	}
	public void restoreBounds(){
		setHeight(DEFAULT_CREATURE_HEIGHT);
		setWidth(DEFAULT_CREATURE_WIDTH);
	}

}
