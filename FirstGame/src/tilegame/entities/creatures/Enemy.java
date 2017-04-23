package tilegame.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;

import tilegame.Game;
import tilegame.gfx.Assets;

public class Enemy extends Creature {

	// private Game game;
	private Rectangle bounds;
	public boolean isSolid = true;

	public Enemy(Game game, float x, float y) {
		super(x, y, 50, 50);
		bounds = new Rectangle();
		bounds.x = (int) getX();
		bounds.y = (int) getY();
		bounds.height = getHeight();
		bounds.width = getWidth();
		// this.game = game;

	}

	@Override
	public void tick() {
		setBounds();
		move();

	}

	@Override
	public void render(Graphics g) {
		// Draws the purple rock asteroide.
		g.drawImage(Assets.purpleRock, (int) x, (int) y, width, height, null);

	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds() {
		// Sets the bounds of the asteroide.
		bounds.x = (int) getX();
		bounds.y = (int) getY() - 10;
		bounds.height = (int) getHeight() - 20;
		bounds.width = (int) getWidth() - 50;
	}
	public void setSolid(boolean solid){
		isSolid = solid;
	}
	public boolean getSolid(){
		return isSolid;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}

}
