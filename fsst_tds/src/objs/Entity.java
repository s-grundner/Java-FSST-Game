package objs;

import java.util.ArrayList;
import java.util.Iterator;

import main.Game;
import math.Vector2;
import objs.enumerators.ProjectileType;
import objs.properties.Position;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public abstract class Entity extends GameObj {

	protected ProjectileType projectileType;
	protected Projectile projectile;
	protected ArrayList<Projectile> projectiles;
	protected Vector2 shootingVector;
	protected Position prevPos;
	protected boolean isShooting;
	protected double firingRate;
	protected double speed;
	protected int hp;
	protected int score;
	private double nextBulletTime;
	

	public Entity(Game game) {
		super(game);
		this.projectiles = new ArrayList<Projectile>();
		this.shootingVector = new Vector2(0, 0);
		this.isShooting = false;
		prevPos = pos;
	}

	public void attack() {
		Projectile proj = new Projectile(game, projectileType, this);
		projectiles.add(proj);
		game.addObjs(proj);
	}

	public void removeProjectile(Projectile projectile) {
		projectile.setAlive(false);
	}

	public void incScore() {
		score++;
	}

	@Override
	public void update() {
		if ((System.currentTimeMillis() > nextBulletTime) && isShooting) {
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
	
	// ------------------------------------------------------------
	// Debug
	// ------------------------------------------------------------
	
	public void displayProjectile() {
		projectiles.forEach(System.out::println);
	}
	
}


