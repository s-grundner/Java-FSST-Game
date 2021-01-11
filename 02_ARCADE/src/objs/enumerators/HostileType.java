package objs.enumerators;

/**
 * @author Simon Grundner
 *		   3AHEL
 */

public enum HostileType {

	HOSTILE1("NA", 50, 1, 1000), HOSTILE2("NA", 50, 10, 500);

	private String imgSource;
	private int hp;
	private int speed;
	private int nextVectorTime;

	private HostileType(String imgSource, int hp, int speed, int nextVectorTime) {
		this.imgSource = imgSource;
		this.hp = hp;
		this.speed = speed;
		this.nextVectorTime = nextVectorTime;
	}

	public String getImgSource() { return imgSource; }

	public void setImgSource(String imgSource) { this.imgSource = imgSource; }

	public int getHp() { return hp; }

	public void setHp(int hp) { this.hp = hp; }

	public int getSpeed() { return speed; }

	public void setSpeed(int speed) { this.speed = speed; }

	public int getNextVectorTime() { return nextVectorTime; }

	public void setNextVectorTime(int nextVectorTime) { this.nextVectorTime = nextVectorTime; }
}
