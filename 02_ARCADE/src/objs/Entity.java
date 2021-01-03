package objs;

import java.util.ArrayList;

import main.Game;
import math.Vector2;
import objs.enumerators.EntityStats;
import objs.enumerators.ProjectileType;
import objs.properties.Hitbox;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public abstract class Entity extends GameObj {

	protected ProjectileType projectileType;
	protected EntityStats stats;
	protected Projectile projectile;
	protected ArrayList<Projectile> projectiles;
	protected Vector2 shootingVector;
	protected Hitbox hitbox;
	protected boolean isShooting;
	protected double firingRate;

	public Entity(Game game, Hitbox hitbox) {
		super(game, hitbox);
		this.projectiles = new ArrayList<Projectile>();
		this.shootingVector = new Vector2(0, 0);
		this.isShooting = false;
		this.hitbox = hitbox;
		
		initStats();
	}

	public Projectile attack() {
		return new Projectile(game, projectileType.getHitbox(), projectileType, this);
	}

	public abstract void initStats();

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public ProjectileType getProjectileType() { return projectileType; }

	public void setProjectileType(ProjectileType projectileType) { this.projectileType = projectileType; }

	public void setShootingVector(Vector2 shootingVector) { this.shootingVector = shootingVector; }

	public Vector2 getShootingVector() {
		shootingVector.setVector(	shootingVector.getX(),
									shootingVector.getY());
		return shootingVector.getUnitVector();
	}

	public boolean getShootingState() { return isShooting; }

	public double getAttackSpeed() { return projectileType.getRate(); }
}
