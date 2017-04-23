package tilegame.entities.creatures;

import java.awt.Graphics;

import tilegame.Game;
import tilegame.gfx.Assets;

public class SideEnemy extends Creature {

	public SideEnemy(Game game, float x, float y) {
		super(x, y, 50, 50);

	}

	@Override
	public void tick() {
		xMove = -5;
		move();
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.sideEnemy,(int) x,(int) y, width, height, null);
		
		
	}

}
