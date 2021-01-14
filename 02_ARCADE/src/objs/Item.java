package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import assets.Animation;
import main.Game;
import objs.enumerators.ItemType;
import objs.enumerators.Objects;
import objs.properties.Animated;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public class Item extends GameObj implements Animated {

	private ItemType itemType;

	public Item(Game game, ItemType itemType) {
		super(game);
		this.itemType = itemType;
	}

	@Override
	public void assignType() {
		object = Objects.ITEM;
	}

	// ------------------------------------------------------------
	// update
	// ------------------------------------------------------------

	@Override
	public void update() {
		initAnim();
	}

	@Override
	public void colliding() {}

	// ------------------------------------------------------------
	// Animate
	// ------------------------------------------------------------

	@Override
	public void anim(Animation anim) {}

	@Override
	public void initAnim() {}

	// ------------------------------------------------------------
	// Draw
	// ------------------------------------------------------------

	@Override
	public AffineTransform transform(Graphics2D graphics) {
		return null;
	}
	
	@Override
	public void drawStats(Graphics2D graphics) {}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public ItemType getItemType() { return itemType; }
}
