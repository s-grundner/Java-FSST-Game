package math;

import config.Config;
import objs.properties.Size;


/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public class GameScaler {

	private Size size;
	private double scaleX;
	private double scaleY;
	private double dx;
	private double dy;

	public GameScaler(Size size) {
		this.size = size;
		rescale();
	}

	public void rescale() {
		scaleX = Config.CANVAS_WIDTH / (Config.TILESIZE * size.getWidth());
		scaleY = Config.CANVAS_HEIGHT / (Config.TILESIZE * size.getHeight());

		Config.SCALE = Math.min(scaleX,
								scaleY);
		compareXY();
	}

	public void compareXY() {
		if (scaleX < scaleY) {
			dx = 0;
			dy = (Config.CANVAS_HEIGHT / (2 * Config.SCALE) - (Config.TILESIZE * size.getHeight()) / 2) / 2;
		} else {
			dx = (Config.CANVAS_WIDTH / (2 * Config.SCALE) - (Config.TILESIZE * size.getWidth()) / 2) / 2;
			dy = 0;
		}
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public Size getSize() { return size; }

	public void setSize(Size size) { this.size = size; }

	public double getScaleX() { return scaleX; }

	public void setScaleX(double scaleX) { this.scaleX = scaleX; }

	public double getScaleY() { return scaleY; }

	public void setScaleY(double scaleY) { this.scaleY = scaleY; }

	public double getDx() { return dx; }

	public void setDx(double dx) { this.dx = dx; }

	public double getDy() { return dy; }

	public void setDy(double dy) { this.dy = dy; }
}
