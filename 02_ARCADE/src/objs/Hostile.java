package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.Game;
import objs.enumerators.EntityStats;
import objs.properties.Position;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public class Hostile extends Entity {

	public Hostile(Game game, EntityStats stats) {
		super(game);
		this.stats = stats;
		setDefaultName("NA");
		setImg(defaultName);
		setPos(game.getMap().getSpawn());
	}

	@Override
	public void initStats() {}

	// ------------------------------------------------------------
	// update
	// ------------------------------------------------------------

	@Override
	public void update() {
		pos = new Position(200, 200);
	}

	@Override
	public Projectile attack() {
		return null;
	}

	@Override
	public void isColliding() {}

	// ------------------------------------------------------------
	// Draw
	// ------------------------------------------------------------

	@Override
	public AffineTransform transform(Graphics2D graphics) {
		AffineTransform transform;
		transform = AffineTransform.getTranslateInstance(	pos.getX() + size.getWidth() / 2,
															pos.getY() + size.getHeight() / 2);
		transform.translate(pos.getX(),
							pos.getY());
		graphics.transform(transform);

		return transform;
	}

	@Override
	public void draw(Graphics2D graphics) {
		Graphics2D graphics2 = (Graphics2D) graphics.create();
		AffineTransform latch = graphics2.getTransform();
		graphics.drawImage(	getImg(),
							transform(graphics2),
							null);

		graphics.setTransform(latch);
		graphics.dispose();
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	@Override
	public BufferedImage getImg() { return null; }
}
