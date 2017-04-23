package tilegame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import tilegame.Game;
import tilegame.gfx.Assets;

public class CreditsState extends State {
	private int rulletekstSpeed = 600;
	public static boolean cheat = false;

	public CreditsState(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		rulletekstSpeed -=1;
		if(game.getKeyManager().escape){
			CreditsState.setState(game.menuState);
		}
		
		if(game.getKeyManager().space){
			rulletekstSpeed -=8;
		}
		if(rulletekstSpeed <= -4900){
			rulletekstSpeed = 0;
			
			CreditsState.setState(game.menuState);
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.background, 0,0 , 1300, 850, null);
		Font fnt1 = new Font("calibri", Font.ROMAN_BASELINE, 60);
		g.setFont(fnt1);
		g.setColor(Color.yellow);
		g.drawString("Programming", game.width/2-150, 250+ rulletekstSpeed);
		g.drawString("-Jakob Sterri", game.width/2-150, 320+ rulletekstSpeed);
		g.drawString("Graphical designer", game.width/2-150, 450+ rulletekstSpeed );
		g.drawString("-Google", game.width/2-150, 520 + rulletekstSpeed);
		g.drawString("Music composer", game.width/2-150, 650 + rulletekstSpeed);
		g.drawString("-Youtube", game.width/2-150, 720 + rulletekstSpeed);
		g.drawString("Female voice actor artist composer", game.width/2-400, 1000 + rulletekstSpeed);
		g.drawString("-Mikael Wenger", game.width/2-150, 1070 + rulletekstSpeed);
		g.drawString("Current world record holder", game.width/2 -300, 1700 + rulletekstSpeed );
		g.drawString("-Fakur mader (level 177)", game.width/2-300, 1770 + rulletekstSpeed );
		g.drawString("-Still here?", game.width/2-300, 3400 + rulletekstSpeed );
		g.drawString("Hold down <enter> to unlock cheat", game.width/2-400,  4700+ rulletekstSpeed );
		if(game.getKeyManager().enter){
			cheat = true;
			g.drawString("Cheat unlocked!", game.width/2-200,  4800+ rulletekstSpeed );
			g.drawString("While in-game press <space> for full health", game.width/2-500, 4900 + rulletekstSpeed  );
		}
	
		
		Font fnt2 = new Font("calibri", Font.PLAIN, 30);
		g.setFont(fnt2);
		g.setColor(Color.white);
		g.drawString("Press <escape> to go back", 0, 30);
		
	}

}
