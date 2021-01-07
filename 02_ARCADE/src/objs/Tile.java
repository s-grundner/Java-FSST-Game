package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.Game;
import objs.properties.Hitbox;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public class Tile extends GameObj {

	public Tile(Game game, Hitbox hitbox) {
		super(game, hitbox);
	}

	// ------------------------------------------------------------
	// update
	// ------------------------------------------------------------

	@Override
	public void update() {
	}

	@Override
	public void colliding() {}

	// ------------------------------------------------------------
	// Draw
	// ------------------------------------------------------------

	@Override
	public void draw(Graphics2D graphics) {}

	@Override
	public AffineTransform transform(Graphics2D graphics) {
		return null;
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public void setImg(BufferedImage img) { this.img = img; }
}
