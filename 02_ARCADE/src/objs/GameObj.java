package objs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import assets.Animation;
import config.Config;
import main.Game;
import math.Vector2;
import objs.properties.Hitbox;
import objs.properties.Position;
import objs.properties.Size;
import objs.properties.Spritesheet;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public abstract class GameObj {

	protected boolean collidable;
	protected boolean colliding;
	protected boolean animate;
	protected boolean alive;
	protected Game game;
	protected Size size;
	protected Position pos;
	protected Vector2 vector;
	protected Vector2 pointingVector;
	protected Spritesheet spritesheet;
	protected BufferedImage img;
	protected String defaultName;
	protected Animation animation;
	protected Hitbox hitbox;
	protected GameObj currentCollision;

	public GameObj(Game game, Hitbox hitbox) {
		this.game = game;
		this.hitbox = hitbox;
		init();
	}

	private void init() {
		colliding = false;
		animate = true;
		alive = true;
		pointingVector = new Vector2(0, 0);
		vector = new Vector2(0, 0);
		size = new Size(Config.TILESIZE, Config.TILESIZE);
		pos = new Position(0, 0);
		
		hitbox.setPos(pos);
	}

	public void move(double speed) {
		pos = new Position(pos.getX() + vector.getX() * speed, pos.getY() + vector.getY() * speed);
	}

	public void updateHitbox() {
		hitbox.setPos(pos);
	}
	
	// ------------------------------------------------------------
	// Abstract Methods
	// ------------------------------------------------------------

	public abstract void update();
	public abstract void isColliding();
	public abstract AffineTransform transform(Graphics2D graphics);
	public abstract void draw(Graphics2D graphics);

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public Hitbox getHitbox() { return hitbox; }
	
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

	public Vector2 getVector() { return vector; }

	public void setVector(Vector2 vector) { this.vector = vector; }

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

	public void drawOrigin(Graphics2D graphics) {
		Graphics2D graphics2 = (Graphics2D) graphics.create();
		graphics2.setColor(Color.BLUE);
		graphics2.drawRect(	(int) pos.getX(),
							(int) pos.getY(),
							2,
							2);
		graphics2.dispose();
	}
}
