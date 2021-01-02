package objs;

import main.Game;
import math.VectorR2;
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
	protected VectorR2 shootingVector;
	protected boolean isShooting;
	protected double firingRate;
	
	public Entity(Game game) {
		super(game);
		
		shootingVector = new VectorR2(0, 0);
		isShooting = false;
		initStats();
	}
	
	public Projectile attack() {
		return new Projectile(game, projectileType, this);
	}
	
	public abstract void initStats();

	//------------------------------------------------------------
	//Getters - Setters
	//------------------------------------------------------------
	
	public ProjectileType getProjectileType() {
		return projectileType;
	}

	public void setProjectileType(ProjectileType projectileType) {
		this.projectileType = projectileType;
	}

	public void setShootingVector(VectorR2 shootingVector) {
		this.shootingVector = shootingVector;
	}
	
	public VectorR2 getShootingVector() {
		shootingVector.setVector(shootingVector.getX(), shootingVector.getY());
		return shootingVector.getUnitVector();
	}
	
	public boolean getShootingState() {
		return isShooting;
	}
	
	public double getAttackSpeed() {
		return projectileType.getRate();
		
	}
	
}
