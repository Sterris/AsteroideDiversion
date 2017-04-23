package tilegame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import tilegame.Game;
import tilegame.entities.creatures.SpeedPowerUp;
import tilegame.gfx.Assets;

public class HelpState extends State {
	int counter = 0;
	private int yMove=0;
	private int xMove=0;

	public HelpState(Game game) {
		super(game);
	}

	@Override
	public void tick() {
		
		xMove +=2;
		yMove -= 2;
		if(yMove < -200){
			yMove =0;
		}
		if(xMove > 200){
			xMove = 0;
		}

		if (game.getKeyManager().escape) {
			State.setState(game.menuState);
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.blank, 0, 0, 1300, 850, null);
		g.drawImage(Assets.keys, 600, 650,150,100, null);
		g.setColor(Color.WHITE);

		counter = 0;
		for (int i = 1; i < 7; i++) {
			g.drawImage(SpeedPowerUp.getPowerUpSymbol(i), 120 + counter, 250, 100, 100, null);

			if (i == 1) {
				g.drawString("Nukes a bunch of asteroides", 120 + counter - 30, 380);
			} else if (i == 2) {
				g.drawString("Slows down falling asteroids", 120 + counter - 30, 380);
				
			} else if (i == 3) {
				g.drawString("Temporary increases", 120 + counter - 20, 380);
				g.drawString("the players speed", 120 + counter - 20, 400);
			} else if (i == 4) {
				g.drawString("Temporary decreases", 120 + counter - 20, 380);
				g.drawString("the players speed", 120 + counter - 20, 400);
			} else if (i == 5) {
				g.drawString("Gives full health to player", 120 + counter - 30, 380);
			} else if (i == 6) {
				g.drawString("Temporary untouchable", 120 + counter - 20, 380);
				g.drawString("from asteroides", 120 + counter - 20, 400);
			}
			
			if (counter < 1400) {
				counter += 200;
			}
			g.drawImage(SpeedPowerUp.getPowerUpSymbol(6), 20, 730, 80, 80, null);
			g.drawImage(Assets.heart, 1240, 750, 50, 50, null);
			g.drawImage(Assets.heart, 1190, 750, 50, 50, null);
			g.drawImage(Assets.heart, 1140, 750, 50, 50, null);
			g.drawImage(Assets.heart, 1090, 750, 50, 50, null);
			g.drawString("Shows players current health", 1100, 740);
			g.drawString("Shows active power up", 10, 730);
			
			//		g.drawImage(Assets.keys, 600, 650,200,135, null);
			g.drawImage(Assets.playerFly, 620, 550 +yMove, 100,100 ,null);
			g.drawImage(Assets.player, 720 + xMove, 650, 100, 100, null);
			g.drawImage(Assets.playerRight, 520 - xMove, 650, 100, 100, null);
			g.drawString("Game controls", 630 , 760);
		}
		// TODO Auto-generated method stub
		Font fnt1 = new Font("calibri", Font.PLAIN, 70);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("Power ups", game.width / 2 - 150, 150);
		Font fnt2 = new Font("calibri", Font.PLAIN, 25);
		g.setFont(fnt2);
		g.drawString("While playing: press <P> for pause", 930, 20);
		g.drawString("Press <escape> to go back", 0, 20);
		g.drawString("Game objective:", 95, 500);
		g.drawString("Avoid falling asteroids and", 95, 530);
		g.drawString("survive as long as possible!", 95, 560);
		g.drawString("Use the walls to teleport!", 900, 500);
		g.drawString("If you fall out of the screen", 900, 530);
		g.drawString("you will lose one hp", 900, 560);
	}

}
