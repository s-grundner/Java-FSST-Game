package objs.properties;

import objs.enumerators.CustomHitbox;

/**
 * @author	Simon Grundner
 * 			3AHEL
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
	
	public Size getSize() { return size; }
	
	public void setSize(Size size) { this.size = size; }
	
	public Position getPos() { return pos; }

	public void setPos(Position pos) { this.pos = pos; }
}
