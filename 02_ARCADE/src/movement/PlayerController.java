package movement;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import objs.properties.Position;

/**
 * @author Simon Grundner
 *         3AHEL
 */

public class PlayerController implements Controller {

	private Input input;

	public PlayerController(Input input) {
		this.input = input;
	}

	// ------------------------------------------------------------
	// Input Configuration
	// ------------------------------------------------------------

	@Override
	public Position getMousePos() {
		return input.getMousePos();
	}

	@Override
	public boolean reqUp() {
		return input.isPressed(KeyEvent.VK_W);
	}

	@Override
	public boolean reqDown() {
		return input.isPressed(KeyEvent.VK_S);
	}

	@Override
	public boolean reqLeft() {
		return input.isPressed(KeyEvent.VK_A);
	}

	@Override
	public boolean reqRight() {
		return input.isPressed(KeyEvent.VK_D);
	}

	@Override
	public boolean exit() {
		return input.isPressed(KeyEvent.VK_ESCAPE);
	}

	@Override
	public boolean reset() {
		return input.isPressed(KeyEvent.VK_R);
	}

	@Override
	public boolean shoot() {
		return input.isMousePressed(MouseEvent.BUTTON1);
	}

	@Override
	public boolean evade() {
		return input.isPressed(KeyEvent.VK_SPACE);
	}

	@Override
	public boolean charge() {
		return input.isMousePressed(MouseEvent.BUTTON3);
	}

	@Override
	public boolean refresh() {
		return input.isPressed(KeyEvent.VK_F5);
	}

	@Override
	public boolean swMap() {
		return input.isPressed(KeyEvent.VK_F1);
	}

	@Override
	public boolean resetCollision() {
		return input.isPressed(KeyEvent.VK_F2);
	}
}
