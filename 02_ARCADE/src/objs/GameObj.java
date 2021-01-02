package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import assets.Animation;
import config.Config;
import main.Game;
import math.VectorR2;
import objs.properties.Position;
import objs.properties.Size;
import objs.properties.Spritesheet;

/**
 * @author	Simon Grundner
 * 			3AHEL
 */

public abstract class GameObj {

	protected boolean collidable;
	protected boolean colliding;
	protected boolean animate;
	protected boolean alive;
	protected Game game;
	protected Size size;
	protected Position pos;
	protected VectorR2 vector;
	protected VectorR2 pointingVector;
	protected Spritesheet spritesheet;
	protected BufferedImage img;
	protected String defaultName;
	protected Animation animation;
	protected GameObj currentCollision;

	public GameObj(Game game) {
		this.game = game;
		init();
	}

	private void init() {
		colliding = false;
		animate = true;
		alive = true;
		pointingVector = new VectorR2(0, 0);
		vector = new VectorR2(0, 0);
		size = new Size(Config.TILESIZE, Config.TILESIZE);
		pos = new Position(0, 0);
	}

	// ------------------------------------------------------------
	// Abstract Methods
	// ------------------------------------------------------------

	public abstract void update();
	public abstract void anim(Animation anim);
	public abstract void initAnim();
	public abstract void draw(Graphics2D graphics);
	public abstract void isColliding();
	public abstract AffineTransform transform(Graphics2D graphics);

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public BufferedImage getImg() { return img; }

	public void setImg(String name) {
		setSpritesheet(name);
		img = spritesheet.getImage(	0,
									0);
	}

	public void setDefaultName(String name) { defaultName = name; }

	public void setDefaultPos() {
		pos = game.getMap().getSpawn();
	}

	public Position getPos() { return pos; }

	public void setPos(Position pos) { this.pos = pos; }

	public Size getSize() { return size; }

	public VectorR2 getVector() { return vector; }

	public void setVector(VectorR2 vector) { this.vector = vector; }

	public void setSpritesheet(String name) { spritesheet = new Spritesheet(name); }

	public void setColliding(boolean colliding) { this.colliding = colliding; }

	public void setCollidable(boolean collidable) { this.collidable = collidable; }

	public boolean isAlive() { return alive; }

	public void setAlive(boolean alive) { this.alive = alive; }

	public void setCurrentCollision(GameObj obj) { currentCollision = obj; }

	public GameObj getCurrentCollision() { return currentCollision; }

	// ------------------------------------------------------------
	// Debug
	// ------------------------------------------------------------

	public String displayPos() {
		return pos.toString();
	}
}
