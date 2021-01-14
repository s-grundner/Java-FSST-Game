package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import config.Config;
import main.Game;
import math.Vector2;
import objs.enumerators.HostileType;
import objs.enumerators.Objects;
import objs.enumerators.ProjectileType;
import objs.properties.Position;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Hostile extends Entity {

	private HostileType type;
	private Player target;
	private long nextVectorTime;

	public Hostile(Game game, HostileType type) {
		super(game);
		this.type = type;

		init();
	}

	private void init() {
		setDefaultName("GAME_Enemy");
		setImg(defaultName);
		setProjectileType(ProjectileType.INTELLIJ);
		setPos(new Position(200, 100));
		vector = new Vector2(Vector2.getRandom().getUnitVector());
		speed = type.getSpeed();
		hp = type.getHp();
		isShooting = true;
		target = searchPlayer();
	}

	@Override
	public void assignType() {
		object = Objects.HOSTILE;
	}

	// ------------------------------------------------------------
	// update
	// ------------------------------------------------------------

	@Override
	public void update() {
		super.update();
		shootingVector = new Vector2(pos, target.getPos());
		colliding();
		if (System.currentTimeMillis() > nextVectorTime) {
			vector = new Vector2(Vector2.getRandom().getUnitVector());
			nextVectorTime = System.currentTimeMillis() + type.getNextVectorTime();
		}
		if (pos.getX() >= (game.getMap().getWidth() - 1) * Config.TILESIZE) {
			pos.setX((game.getMap().getWidth() - 2) * Config.TILESIZE);
			vector = new Vector2(Vector2.getRandom(vector).getUnitVector());
		} else if (pos.getY() >= (game.getMap().getHeight() - 1) * Config.TILESIZE) {
			pos.setY((game.getMap().getHeight() - 2) * Config.TILESIZE);
			vector = new Vector2(Vector2.getRandom(vector).getUnitVector());
		} else if (pos.getX() <= Config.TILESIZE) {
			pos.setX(2 * Config.TILESIZE);
			vector = new Vector2(Vector2.getRandom(vector).getUnitVector());
		} else if (pos.getY() <= Config.TILESIZE) {
			pos.setY(2 * Config.TILESIZE);
			vector = new Vector2(Vector2.getRandom(vector).getUnitVector());
		}
		move(type.getSpeed());
	}

	@Override
	public void colliding() {
		if (colliding) {
			if (getCurrentCollision() instanceof Projectile) {
				Projectile hitMark = (Projectile) getCurrentCollision();
				if (hit) {
					hit = false;
					hp -= hitMark.getProjectile().getDmg();
					if (hp < 0) {
						setAlive(false);
						hitMark.getHost().incScore();
						hitMark.getHost().removeProjectile(hitMark);
						;
					}
				}
			} else {
				pos = prevPos;
				vector = new Vector2(Vector2.getRandom(vector).getUnitVector());
			}
		} else {
			currentCollision = null;
			prevPos = pos;
			hit = true;
		}
	}

	private Player searchPlayer() {
		for (GameObj obj : game.parseObjs()) {
			if (obj instanceof Player) { return (Player) obj; }
		}
		return null;
	}

	// ------------------------------------------------------------
	// Draw
	// ------------------------------------------------------------

	@Override
	public AffineTransform transform(Graphics2D graphics) {
		AffineTransform transform = new AffineTransform();
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

	public HostileType getType() { return type; }
}
