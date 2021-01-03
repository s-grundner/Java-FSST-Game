package movement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import objs.properties.Position;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public class Input implements KeyListener, MouseListener, MouseMotionListener {

	private boolean[] pressed;
	private boolean[] mousePressed;
	private Position mousePos;

	public Input() {
		pressed = new boolean[1024];
		mousePressed = new boolean[8];
		mousePos = new Position(0.0, 0.0);
	}

	public boolean isPressed(int keyCode) {
		return pressed[keyCode];
	}

	public boolean isMousePressed(int keyCode) {
		return mousePressed[keyCode];
	}

	public Position getMousePos() { return mousePos; }

	// ------------------------------------------------------------
	// Key Listening
	// ------------------------------------------------------------

	@Override
	public void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressed[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	// ------------------------------------------------------------
	// Mouse Listening
	// ------------------------------------------------------------

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed[e.getButton()] = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePos.setX(e.getX());
		mousePos.setY(e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos.setX(e.getX());
		mousePos.setY(e.getY());
	}
}
