package tilegame;

import tilegame.sound.Sound;



public class Launcher {
	public int ticks;
	

	public static void main(String[] args) {
		Game game = new Game("Asteroide diversion", 1300, 850);
		game.start();
		
		//Sound not working when exporting to jar
		Sound background = new Sound();
		background.play();

		game.title ="Asteroide diversion";
		

	}
	
	

}
