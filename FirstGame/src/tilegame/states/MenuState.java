package tilegame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


import tilegame.Game;
import tilegame.gfx.Assets;
import tilegame.input.MouseManager;


public class MenuState extends State {
	
	//Fake buttons
	private Rectangle playButton = new Rectangle((game.width / 2) - 170, 350, 100, 50);
	private Rectangle helpButton = new Rectangle((game.width / 2) - 170, 450, 200, 200);
	private Rectangle creditButton = new Rectangle((game.width/2) - 170 , 550, 200 , 200);

	public MenuState(Game game) {
		super(game);
	}

	@Override
	public void tick() {
		
		//Mouse coordinates
		float musX = MouseManager.mouseX;
		float musY = MouseManager.mouseY;
		
		//Enter shortcut, must change.
		if(game.getKeyManager().enter){
			MenuState.setState(game.gameState);
		}

		// Checking if mouse clicked and inside playbutton.
		if (game.getMouseManager().isLeftPressed()) {
			if (musX > 500 && musX < 785) {
				if (musY < 365 && musY > 315) {
					MenuState.setState(game.gameState);
				}
			}
		} //Same for menu
		if(game.getMouseManager().isLeftPressed()){
			if(musX>500 && musX < 700){
				if(musY > 420 && musY < 501) {
					MenuState.setState(game.helpState);
				}
			}
		}
		if(game.getMouseManager().isLeftPressed()){
			if(musX>500 && musX <700){
				if(musY > 500 && musY < 610){
					MenuState.setState(game.creditsState);
				}
			}
		}
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.blank, 0, 0, 1300, 850, null);
		g.setColor(Color.white);
		g.drawString("FPS:" + Game.fpser, 0, 20);
		Font fnt0 = new Font("calibri", Font.PLAIN, 65);
		g.setFont(fnt0);
		g.setColor(Color.YELLOW);
		g.drawString("Start game", playButton.x + 19, playButton.y + 19);
		g.drawString("Controls", helpButton.x + 19, helpButton.y + 19);
		g.drawString("Credits", creditButton.x +19, creditButton.y+ 19);
		
//		g.fillRect(500, 500, 200, 60);
		
//		g.fillRect(500, 420, 200 ,60 );
		g.setColor(Color.red);
//		g.fillRect(game.getMouseManager().getMouseX(), game.getMouseManager().getMouseY(), 8, 8);
		g.drawImage(Assets.playerRight, game.getMouseManager().getMouseX()-10, game.getMouseManager().getMouseY()-10, 50, 50, null);
	}

}
