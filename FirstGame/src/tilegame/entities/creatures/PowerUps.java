package tilegame.entities.creatures;

import java.util.ArrayList;

import tilegame.Game;
import tilegame.states.GameState;

public abstract class PowerUps extends Creature {
	public static ArrayList<Enemy> nukeExplosion = new ArrayList<>();

	public PowerUps(Game game, float x, float y) {
		super(x, y, 50, 50);

	}
	
	//Test, skal egentlig ikke være her?
	public static void gameOver(Player player, ArrayList<Enemy> enemies){
		player.setyMove(-3);
		player.setSpeed(0);

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).setyMove(0);
		}
	}

	public static void speedDownPowerUp(Player player) {
		player.setSpeed(3);
	}

	public static void speedUpPowerUp(Player player) {
		player.setSpeed(15);
	}

	public static void invisible(Player player) {
		player.setHeight(1);
		player.setWidth(1);
	}

	public static void fullHealth(Player player) {
		player.setHealth(4);
	}

	public static void slowEnemy(ArrayList<Enemy> enemies) {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).setyMove(2);
		}
	}
	public static void invicibleEnemies(ArrayList<Enemy> enemies){
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).setBounds();
		}
	}

	public static void nuke(ArrayList<Enemy> enemies) {
		for (int i = 0; i < enemies.size(); i++) {
			nukeExplosion.add(enemies.get(i));
			enemies.remove(i);
			GameState.stoneCounter +=1;

		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.remove(i);

		}

	}

}
