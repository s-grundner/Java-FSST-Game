package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import main.Game;
import math.Vector2;
import objs.enumerators.HostileType;
import objs.enumerators.ProjectileType;
import objs.properties.Position;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Hostile extends Entity {

	private HostileType type;
	private long nextVectorTime;

	public Hostile(Game game, HostileType type) {
		super(game);
		this.type = type;

		init();
	}

	private void init() {
		setDefaultName("NA");
		setImg(defaultName);
		setProjectileType(ProjectileType.BULLET);
		setPos(new Position(200, 200));
		vector = new Vector2(Vector2.getRandom().getUnitVector());
	}

	// ------------------------------------------------------------
	// update
	// ------------------------------------------------------------

	@Override
	public void update() {
//		if (System.currentTimeMillis() > nextVectorTime) {
//			vector = new Vector2(Vector2.getRandom().getUnitVector());
//			nextVectorTime = System.currentTimeMillis() + type.getNextVectorTime();
//		}
		move(type.getSpeed());
		colliding();
	}

	@Override
	public void attack() {}

	@Override
	public void colliding() {
		if (colliding) {
			pos = prevPos; 
//			vector = vector.getReverseVec();
//			vector = Vector2.getRandom(vector);
			

			if (getCurrentCollision() instanceof Projectile) {
				Projectile hitMark = (Projectile) getCurrentCollision();
				if (hit) {
					hit = false;
					type.setHp(type.getHp() - hitMark.getProjectile().getDmg());
				}
				if (type.getHp() < 0) {
					setAlive(false);
				}
			} else {
				
				
				vector = new Vector2(Vector2.getRandom(vector).getUnitVector());
				
			}
		} else {
			prevPos = pos;
			hit = true;
		}
	}

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
