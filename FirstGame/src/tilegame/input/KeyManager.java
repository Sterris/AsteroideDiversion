package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	public boolean up, down, left, right, space, pause, enter, escape;

	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void tick(){
		up = keys [KeyEvent.VK_UP];
		down = keys [KeyEvent.VK_DOWN];
		left = keys [KeyEvent.VK_LEFT];
		right = keys [KeyEvent.VK_RIGHT];
		space = keys [KeyEvent.VK_SPACE];
		pause = keys[KeyEvent.VK_P];
		enter = keys[KeyEvent.VK_ENTER];
		escape = keys[KeyEvent.VK_ESCAPE];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
