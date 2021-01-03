package objs.enumerators;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public enum EntityStats {

	// ------------------------------------------------------------
	// Enumerators (Entities)
	// ------------------------------------------------------------

	P_DEFAULT(2, 3), P_EVADE(10, 3), H_HOSTILE1(2, 3);

	// ------------------------------------------------------------
	// Members - Constructor
	// ------------------------------------------------------------

	private double speed;
	private int hp;

	private EntityStats(double speed, int hp) {
		this.speed = speed;
		this.hp = hp;
	}

	// ------------------------------------------------------------
	// Setters - Getters
	// ------------------------------------------------------------

	public void setHp(int hp) { this.hp = hp; }

	public int getHp() { return hp; }

	public double getSpeed() { return speed; }

	public void setSpeed(double speed) { this.speed = speed; }
}
