package objs.properties;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public class Size {

	private double width;
	private double height;

	public Size(double width, double height) {
		this.width = width;
		this.height = height;
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public double getWidth() { return width; }

	public double getHeight() { return height; }
}
