package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import config.Config;
import main.Game;
import objs.enumerators.Objects;
import objs.enumerators.ProjectileType;
import objs.properties.Position;

/**
 * @author	Simon Grundner
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
		this.pos = new Position(host.getPos().getX() + Config.TILESIZE * Math.cos(vector.getAngleToXAxis()),
								host.getPos().getY() - Config.TILESIZE * Math.sin(vector.getAngleToXAxis()));

		setImg(projectile.getFileName());
		img = spritesheet.getImage(	projectile.getStage(),
									projectile.getMode());
	}

	@Override
	public void assignType() {
		object = Objects.PROJECTILE;
	}

	// ------------------------------------------------------------
	// update
	// ------------------------------------------------------------

	@Override
	public void update() {
		if ((getPos().getX() < 0 || getPos().getY() < 0)
				|| (getPos().getX() > Config.CANVAS_WIDTH || getPos().getY() > Config.CANVAS_HEIGHT)) {
			setAlive(false);
		}
		colliding();
		move(projectile.getSpeed());
	}

	@Override
	public void colliding() {
		if (colliding) {
			if (!host.equals(currentCollision)) {
				boolean x = false;
				for (Projectile proj : host.getProjectiles()) {
					if (proj.equals(currentCollision)) {
						x = true;
						break;
					}
				}
				if (!x) {
					host.removeProjectile(this);
				}
			}
		} else {
			currentCollision = null;
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

	@Override
	public void drawStats(Graphics2D graphics) {}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public Entity getHost() { return host; }

	public void setHost(Entity host) { this.host = host; }

	public ProjectileType getProjectile() { return projectile; }

	// ------------------------------------------------------------
	// Debug
	// ------------------------------------------------------------

	@Override
	public String toString() {
		return String.valueOf(projectile.getDmg());
	}
}
