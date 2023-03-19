package objs.enumerators;

/**
 * @author Simon Grundner
 *		   3AHEL
 */

public enum HostileType {

	HOSTILE1("NA", 50, 1, 10000), 
	HOSTILE2("NA", 50, 4, 100);

	private String imgSource;
	private int hp;
	private double speed;
	private int nextVectorTime;

	private HostileType(String imgSource, int hp, double speed, int nextVectorTime) {
		this.imgSource = imgSource;
		this.hp = hp;
		this.speed = speed;
		this.nextVectorTime = nextVectorTime;
	}

	public String getImgSource() { return imgSource; }

	public int getHp() { return hp; }

	public double getSpeed() { return speed; }

	public int getNextVectorTime() { return nextVectorTime; }
}
