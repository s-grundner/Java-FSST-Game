package objs;

import java.util.ArrayList;
import java.util.Iterator;

import main.Game;
import math.Vector2;
import objs.enumerators.EntityStats;
import objs.enumerators.ProjectileType;

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
	protected boolean isShooting;
	protected double firingRate;
	protected double nextBulletTime;

	public Entity(Game game) {
		super(game);
		this.projectiles = new ArrayList<Projectile>();
		this.shootingVector = new Vector2(0, 0);
		this.isShooting = false;

		initStats();
	}

	public void attack() {
		Projectile proj = new Projectile(game, projectileType, this);
		projectiles.add(proj);
		game.addObjs(proj);
	}

	public void removeProjectile(Projectile projectile) {
		projectile.setAlive(false);
		
	}

	public abstract void initStats();

	@Override
	public void update() {
		if ((System.currentTimeMillis() > nextBulletTime) && getShootingState()) {
			attack();
			nextBulletTime = System.currentTimeMillis() + getAttackSpeed();
		}
		
		for (Iterator<Projectile> proji = projectiles.iterator(); proji.hasNext();) {
			Projectile proj = proji.next();
			if (!proj.isAlive()) {
				proji.remove();
			}
		}
	}

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

	public ArrayList<Projectile> getProjectiles() { return projectiles; }
}
