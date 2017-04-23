package tilegame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import tilegame.Game;
import tilegame.gfx.Assets;
//import tilegame.sound.SoundEffects;


public class GameOverState extends State {
	private int yMove = -500;
//	public static boolean hasPlayed = false;
	public static int counter = 0;


	public GameOverState(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		yMove += 2;
		if (yMove == 1000) {
			yMove = -500;
		}
		if (game.getKeyManager().space) { // Press space to start new game.
			GameState.setState(game.menuState);
		}
		counter +=1;
	}

	@Override
	public void render(Graphics g) {

		g.drawImage(Assets.gameOver, 0, 0, 1300, 850, null);
		animation(g);
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.black);
		g.drawString("You managed to avoid " + GameState.stoneCounter + " asteroides", game.width / 2 - 400,
				game.height / 2 + 100);
		Font fnt1 = new Font("calibri", Font.PLAIN, 50);
		g.setFont(fnt1);
		g.setColor(Color.white);
		levelText(g, GameState.level);
		g.drawString("<Press space to go to menu>", game.width / 2 - 300, game.height / 2 + 300);
		
	}
	
	//Making players and rocks fall down
	public void animation(Graphics g){
		g.drawImage(Assets.purpleRock, 1000, yMove - 100, null);
		g.drawImage(Assets.playerDie, 1000, yMove, null);
		g.drawImage(Assets.purpleRock, 800, yMove - 40, null);
		g.drawImage(Assets.playerDie, 800, yMove + 60, null);
		g.drawImage(Assets.purpleRock, 600, yMove + 20, null);
		g.drawImage(Assets.playerDie, 600, yMove + 120, null);
		g.drawImage(Assets.purpleRock, 400, yMove + 80, null);
		g.drawImage(Assets.playerDie, 400, yMove + 180, null);
		g.drawImage(Assets.purpleRock, 200, yMove + 140, null);
		g.drawImage(Assets.playerDie, 200, yMove + 240, null);
	}
	
	//Displays text according to level.
	public void levelText(Graphics g, int level){
		switch(level){
		case 1: case 2: case 3: case 4: case 5:
			g.drawString("Argh mate, only  level " + GameState.level + "? First time playing?", game.width / 2 - 400, game.height / 2);
//			if( counter > 60/2 && !hasPlayed){
//				SoundEffects gameOverSound = new SoundEffects();
//				gameOverSound.playSound("res/textures/mik.wav");
//				hasPlayed = true;
//			}
			break;
		case 6: case 7: case 8: case 9:
			g.drawString("You are getting better. Fatality on level " + GameState.level, game.width / 2 - 400, game.height / 2);
			break;
		case 10: case 11: case 12: case 13:
			g.drawString("Good run comrade, you're average. Level: " + GameState.level, game.width / 2 - 400, game.height / 2);
			break;
		case 14: case 15: case 16: case 17:
			g.drawString("Wow, great job! You died on level:  " + GameState.level, game.width / 2 - 400, game.height / 2);
			break;
		case 18: case 19: case 20:
			g.drawString("Amazing!! You are in the top 10%. Level:  " + GameState.level, game.width / 2 - 400, game.height / 2);
			break;
		case 21: case 22: case 23:
			g.drawString("Holy shit!! New world record! Died on level:  " + GameState.level, game.width / 2 - 400, game.height / 2);
			break;
			
		}
	}
}


