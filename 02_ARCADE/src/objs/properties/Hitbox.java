package objs.properties;

import config.Config;
import objs.enumerators.CustomHitbox;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public class Hitbox {

	private Size size;
	private Position pos;

	public Hitbox(Size size, Position pos) {
		this.size = size;
		this.pos = pos;
	}

	public Hitbox(CustomHitbox cHitbox) {
		this.size = new Size(cHitbox.getWidth(), cHitbox.getWidth());
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public Position getOffset() {
		return new Position((Config.TILESIZE - size.getWidth()) / 2, (Config.TILESIZE - size.getHeight()) / 2);
	}
	
	public Size getSize() { return size; }

	public void setSize(Size size) { this.size = size; }

	public Position getPos() {
		return new Position(pos.getX(), pos.getY());
	}

	public void setPos(Position pos) { this.pos = pos; }
}
