package objs.enumerators;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public enum PlayerStats {

	// ------------------------------------------------------------
	// Enumerators (Entities)
	// ------------------------------------------------------------

	P_DEFAULT(2, 3), P_EVADE(10, 3);

	// ------------------------------------------------------------
	// Members - Constructor
	// ------------------------------------------------------------

	private double speed;
	private int hp;

	private PlayerStats(double speed, int hp) {
		this.speed = speed;
		this.hp = hp;
	}

	// ------------------------------------------------------------
	// Setters - Getters
	// ------------------------------------------------------------

	public void setHp(int hp) { this.hp = hp; }

	public int getHp() { return hp; }

	public double getSpeed() { return speed; }

}
