package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import config.Config;
import main.Game;
import objs.enumerators.ProjectileType;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public class Projectile extends GameObj {

	private ProjectileType projectile;
	private Entity host;

	public Projectile(Game game, ProjectileType projectile, Entity host) {
		super(game);
		this.host = host;
		this.projectile = projectile;
		this.vector = host.getShootingVector();
		this.pos.setPos(host.getPos().getX() + Config.TILESIZE * Math.cos(vector.getAngleToXAxis()),
						host.getPos().getY() - Config.TILESIZE * Math.sin(vector.getAngleToXAxis()));

		setImg(projectile.getFileName());
		img = spritesheet.getImage(	projectile.getStage(),
									projectile.getMode());
	}

	// ------------------------------------------------------------
	// update
	// ------------------------------------------------------------

	@Override
	public void update() {
		isColliding();
		move(projectile.getSpeed());
	}

	@Override
	public void isColliding() {
		if (!host.equals(getCurrentCollision())) {
			if (colliding) {
				setAlive(false);
			} else {
			}
		}
	}

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
		drawOrigin(graphics2);
		graphics2.dispose();
	}

	public Entity getHost() { return host; }

	public void setHost(Entity host) { this.host = host; }

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------
}
