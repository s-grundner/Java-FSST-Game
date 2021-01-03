package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.Game;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Tile extends GameObj {

	public Tile(Game game) {
		super(game);
	}

	// ------------------------------------------------------------
	// update
	// ------------------------------------------------------------

	@Override
	public void update() {}

	@Override
	public void isColliding() {}

	// ------------------------------------------------------------
	// Draw
	// ------------------------------------------------------------

	@Override
	public void draw(Graphics2D graphics) {
	}

	@Override
	public AffineTransform transform(Graphics2D graphics) {
		return null;
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public void setImg(BufferedImage img) { this.img = img; }
}
