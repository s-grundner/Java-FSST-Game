package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import assets.Animation;
import config.Config;
import main.Game;
import objs.enumerators.ProjectileType;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Projectile extends GameObj {

	private ProjectileType projectile;

	public Projectile(Game game, ProjectileType projectile, Entity obj) {
		super(game);
		this.vector = obj.getShootingVector();
		this.pos.setPos(obj.getPos().getX() + Config.TILESIZE * Math.cos(vector.getAngleToXAxis()),
						obj.getPos().getY() - Config.TILESIZE * Math.sin(vector.getAngleToXAxis()));
		this.projectile = projectile;
		setImg(projectile.getFileName());
		img = spritesheet.getImage(	projectile.getStage(),
									projectile.getMode());
	}

	// ------------------------------------------------------------
	// update
	// ------------------------------------------------------------

	@Override
	public void update() {
		initAnim();
		if (colliding) {
			isColliding();
		} else {}
		pos.setPos(	pos.getX() + vector.getX() * projectile.getSpeed(),
					pos.getY() + vector.getY() * projectile.getSpeed());
	}

	@Override
	public void isColliding() {
		setAlive(false);
	}

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
		AffineTransform transform = new AffineTransform();
		transform = AffineTransform.getTranslateInstance(	pos.getX() + size.getWidth() / 2,
															pos.getY() + size.getHeight() / 2);

		transform.setToRotation(vector.getX(),
								vector.getY(),
								pos.getX() + size.getWidth() / 2,
								pos.getY() + size.getHeight() / 2);
		transform.translate(pos.getX(),
							pos.getY());

		return transform;
	}

	@Override
	public void draw(Graphics2D graphics) {
		AffineTransform latch = graphics.getTransform();
		Graphics2D graphics2 = (Graphics2D) graphics.create();
		graphics2.drawImage(getImg(),
							transform(graphics2),
							null);
		graphics2.setTransform(latch);
		graphics2.dispose();
	}
}
