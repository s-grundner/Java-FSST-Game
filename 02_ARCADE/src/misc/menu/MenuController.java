package misc.menu;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import movement.Input;
import objs.properties.Position;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class MenuController implements MController {

	private Input input;

	public MenuController(Input input) {
		this.input = input;
	}

	@Override
	public Position getMousePos() { return input.getMousePos(); }

	@Override
	public boolean getMouseClick() { return input.isMousePressed(MouseEvent.BUTTON1); }

	@Override
	public boolean getSpace() { return input.isPressed(KeyEvent.VK_SPACE); }

	@Override
	public boolean exit() {
		return input.isPressed(KeyEvent.VK_ESCAPE);
	}
}
