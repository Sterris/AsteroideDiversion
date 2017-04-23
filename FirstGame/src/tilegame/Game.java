package tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import tilegame.display.Display;
import tilegame.gfx.Assets;
import tilegame.input.KeyManager;
import tilegame.input.MouseManager;
import tilegame.states.CreditsState;
import tilegame.states.GameOverState;
import tilegame.states.GameState;
import tilegame.states.HelpState;

import tilegame.states.MenuState;
import tilegame.states.PauseState;
import tilegame.states.State;

public class Game implements Runnable {


	//Game
	private boolean running;
	public String title;
	public static int fpser;
	private Thread thread;

	//Graphics
	private BufferStrategy bs;
	private Graphics g;
	private Display display;

	// States
	public State gameState;
	public State menuState;
	public State gameOverState;
	public State pauseState;
	public State helpState;
	public State creditsState;
	//TODO: create creditState, wich shows music and gamedev?


	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	public int width, height;

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();

	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
	
		Assets.init();
		
		//Setting states
		gameState = new GameState(this);
		menuState = new MenuState(this);
		gameOverState = new GameOverState(this);
		pauseState = new PauseState(this);
		helpState = new HelpState(this);
		creditsState = new CreditsState(this);
	
		State.setState(menuState); 
	}


	private void tick() {
		keyManager.tick();

		if (State.getState() != null) {
			State.getState().tick();
		}
		

	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw here

		if (State.getState() != null) {
			State.getState().render(g);
		}
		// end drawing
		bs.show();
		g.dispose();
	}

	public void run() {

		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				fpser = ticks;
				ticks = 0;
				timer = 0;
			}

		}

		stop();

	}

	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
