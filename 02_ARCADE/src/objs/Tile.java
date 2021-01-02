package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import assets.Animation;
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
	public void update() {
		initAnim();
	}

	@Override
	public void isColliding() {}

	// ------------------------------------------------------------
	// Animate
	// ------------------------------------------------------------

	@Override
	public void anim(Animation anim) {
	}

	@Override
	public void initAnim() {
	}

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
