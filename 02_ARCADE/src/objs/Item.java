package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import assets.Animation;
import main.Game;
import objs.enumerators.ItemType;
import objs.properties.Animated;
import objs.properties.Hitbox;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public class Item extends GameObj implements Animated {

	public Item(Game game, Hitbox hitbox, ItemType itemType) {
		super(game, hitbox);
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

	@Override
	public BufferedImage getImg() { return null; }
}
