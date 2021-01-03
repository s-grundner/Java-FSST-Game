package movement;

import objs.properties.Position;

/**
 * @author	Simon Grundner
 * 			3AHEL
 */

public interface Controller {

	boolean reqUp();
	boolean reqDown();
	boolean reqLeft();
	boolean reqRight();
	boolean exit();
	boolean reset();
	boolean evade();
	boolean shoot();
	boolean charge();
	boolean refresh();
	boolean swMap();
	boolean resetCollision();
	Position getMousePos();
}
