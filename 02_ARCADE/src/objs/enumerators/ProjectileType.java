package objs.enumerators;

import objs.properties.Hitbox;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public enum ProjectileType {

	// ------------------------------------------------------------
	// Enumerators (Projectiles)
	// ------------------------------------------------------------

	BULLET("WEAPON_Bullet", new Hitbox(CustomHitbox.W_PROJ_BULLET), 100, 10, 3),
	LASER("WEAPON_Laser", new Hitbox(CustomHitbox.W_PROJ_LASER), 0, 16, 100),
	AOE("", new Hitbox(null, null), 0, 0, 0),
	WAND("", new Hitbox(null, null), 0, 0, 0);

	// ------------------------------------------------------------
	// Members - Constructor
	// ------------------------------------------------------------

	private String fileName;
	private Hitbox hitbox;
	private double rate;
	private double speed;
	private int power;
	private int mode;
	private int stage;

	private ProjectileType(String fileName, Hitbox hitbox, double rate, double speed, int power) {
		this.fileName = fileName;
		this.hitbox = hitbox;
		this.rate = rate;
		this.speed = speed;
		this.power = power;
		mode = 0;
		stage = 0;
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public String getFileName() { return fileName; }

	public void setFileName(String fileName) { this.fileName = fileName; }

	public double getRate() { return rate; }

	public void setRate(double rate) { this.rate = rate; }

	public double getSpeed() { return speed; }

	public void setSpeed(double speed) { this.speed = speed; }

	public int getPower() { return power; }

	public void setPower(int power) { this.power = power; }

	public int getMode() { return mode; }

	public void setMode(int mode) { this.mode = mode; }

	public int getStage() { return stage; }

	public void setStage(int stage) { this.stage = stage; }

	public Hitbox getHitbox() { return hitbox; }

	public void setHitbox(Hitbox hitbox) { this.hitbox = hitbox; }
}
