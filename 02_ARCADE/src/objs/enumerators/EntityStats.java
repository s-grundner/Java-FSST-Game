package objs.enumerators;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public enum EntityStats {

	//------------------------------------------------------------
	//Enumerators (Entities)
	//------------------------------------------------------------
	
	P_DEFAULT(2, 3),
	P_EVADE(10, 3),
	H_HOSTILE1(2, 3);
	
	//------------------------------------------------------------
	//Members - Constructor
	//------------------------------------------------------------
	
	private int speed;
	private int hp;
	
	private EntityStats(int speed, int hp) {
		this.speed = speed;
		this.hp = hp;
	}
	
	//------------------------------------------------------------
	//Setters - Getters
	//------------------------------------------------------------
	
	public void setHp(int hp) {
		this.hp = hp;
	}	
	
	public int getHp() { return hp; }
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
