package objs.properties;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Position {

	protected double x;
	protected double y;

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public void setPos(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() { return x; }

	public void setX(double x) { this.x = x; }

	public double getY() { return y; }

	public void setY(double y) { this.y = y; }

	// ------------------------------------------------------------
	// Debug
	// ------------------------------------------------------------

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}
