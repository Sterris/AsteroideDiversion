package tilegame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import tilegame.Game;
import tilegame.gfx.Assets;

public class PauseState extends State {

	public PauseState(Game game) {
		
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {

		if(game.getKeyManager().space){
			GameState.setState(game.gameState);
		}
		if (game.getKeyManager().escape) {
			State.setState(game.menuState);
		}
	
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.paused , 0, 0, 1300, 850, null);
		g.setColor(Color.white);
		g.drawString("Press <escape> to go to main menu", 5, 20);
		Font fnt0 = new Font("calibri", Font.PLAIN, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("<Press space to continue>", game.width/2-275, game.height/2);
		
		
	}
	

}
