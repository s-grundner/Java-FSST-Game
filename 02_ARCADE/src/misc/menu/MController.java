package misc.menu;

import objs.properties.Position;

/**
 * @author Simon Grundner
 *		   3AHEL
 */

public interface MController {

	Position getMousePos();
	boolean getMouseClick();
	boolean getSpace();
}
