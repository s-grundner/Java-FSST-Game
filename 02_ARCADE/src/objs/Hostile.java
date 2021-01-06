package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import main.Game;
import objs.enumerators.EntityStats;
import objs.enumerators.ProjectileType;
import objs.properties.Hitbox;
import objs.properties.Position;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Hostile extends Entity {

	public Hostile(Game game, Hitbox hitbox, EntityStats stats) {
		super(game, hitbox);
		this.stats = stats;
		setDefaultName("NA");
		setImg(defaultName);
		setProjectileType(ProjectileType.BULLET);
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
	public void attack() {}

	@Override
	public void colliding() {}

	// ------------------------------------------------------------
	// Draw
	// ------------------------------------------------------------

	@Override
	public AffineTransform transform(Graphics2D graphics) {
		AffineTransform transform;
		transform = AffineTransform.getTranslateInstance(	pos.getX() + size.getWidth() / 2,
															pos.getY() + size.getHeight() / 2);
		transform.setToRotation(vector.getX(),
								vector.getY(),
								pos.getX() + size.getWidth() / 2,
								pos.getY() + size.getHeight() / 2);
		graphics.transform(transform);

		transform.translate(pos.getX(),
							pos.getY());
		graphics.transform(transform);

		return transform;
	}
}
