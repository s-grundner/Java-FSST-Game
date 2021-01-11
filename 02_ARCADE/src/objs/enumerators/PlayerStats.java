package objs.enumerators;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public enum PlayerStats {

	// ------------------------------------------------------------
	// Enumerators (Entities)
	// ------------------------------------------------------------

	P_DEFAULT(2, 3), P_EVADE(10, 3), H_HOSTILE1(2, 3);

	// ------------------------------------------------------------
	// Members - Constructor
	// ------------------------------------------------------------

	private double speed;
	private double defaultSpeed;
	private double speedX;
	private double speedY;
	private int hp;

	private PlayerStats(double speed, int hp) {
		this.speed = speed;
		this.defaultSpeed = speed;
		this.speedX = speed;
		this.speedY = speed;
		this.hp = hp;
	}

	// ------------------------------------------------------------
	// Setters - Getters
	// ------------------------------------------------------------

	public void setHp(int hp) { this.hp = hp; }

	public int getHp() { return hp; }

	public double getSpeed() { return speed; }

	public void setSpeed(double speed) { this.speed = speed; }

	public double getDefaultSpeed() { return defaultSpeed; }

	public void setDefaultSpeed() {
		this.speed = defaultSpeed;
	}

	public double getSpeedX() { return speedX; }

	public void setSpeedX(double speedX) { this.speedX = speedX; }

	public double getSpeedY() { return speedY; }

	public void setSpeedY(double speedY) { this.speedY = speedY; }
}
