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
 * @author	Simon Grundner
 *			3AHEL
 */

public abstract class GameObj {

	protected boolean colliding;
	protected boolean animate;
	protected boolean alive;
	protected Game game;
	protected Size size;
	protected Position pos;
	protected Vector2 vector;
	protected Vector2 pointingVector;
	protected Vector2 collisionVector;
	protected Spritesheet spritesheet;
	protected BufferedImage img;
	protected String defaultName;
	protected Animation animation;
	protected Hitbox hitbox;
	protected GameObj currentCollision;
	protected Hitbox currentHitboxCollision;

	public GameObj(Game game, Hitbox hitbox) {
		this.game = game;
		this.hitbox = hitbox;
		init();
	}

	// ------------------------------------------------------------
	//
	// ------------------------------------------------------------

	private void init() {
		colliding = false;
		animate = true;
		alive = true;
		vector = new Vector2(0, 0);
		pointingVector = new Vector2(1 + 1E-20, 0 + 1E-20);
		size = new Size(Config.TILESIZE, Config.TILESIZE);
		pos = new Position(0, 0);

		hitbox.setPos(pos);
	}

	public void move(double speed) {
		pos = new Position(pos.getX() + vector.getX() * speed, pos.getY() + vector.getY() * speed);
	}

	public void move(double speedX, double speedY, double refSpeed) {
//		if (Math.round(speedX) == 0) {
//			speedY = refSpeed * Math.sin(pointingVector.getSmallAngleToXAxis());
//		}
//		if (Math.round(speedY) == 0) {
//			speedX = refSpeed * Math.cos(pointingVector.getSmallAngleToXAxis());
//		}
		pos = new Position(pos.getX() + vector.getX() * speedX, pos.getY() + vector.getY() * speedY);
	}

	public void draw(Graphics2D graphics) {
		Graphics2D graphics2 = (Graphics2D) graphics.create();
		AffineTransform latch = graphics.getTransform();
		graphics.drawImage(	getImg(),
							transform(graphics2),
							null);

		graphics.setTransform(latch);
		graphics2.dispose();
	}

	// ------------------------------------------------------------
	// Abstract Methods
	// ------------------------------------------------------------

	public abstract void colliding();
	public abstract void update();
	public abstract AffineTransform transform(Graphics2D graphics);

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

	public boolean isColliding() { return colliding; }

	public boolean isAlive() { return alive; }

	public void setAlive(boolean alive) { this.alive = alive; }

	public void setCurrentCollision(GameObj obj) { currentCollision = obj; }

	public GameObj getCurrentCollision() { return currentCollision; }

	public Hitbox getCurrentHitboxCollision() { return currentHitboxCollision; }

	public void setCurrentHitboxCollision(Hitbox currentHitboxCollision) {
		this.currentHitboxCollision = currentHitboxCollision;
	}

	public void setCollisionVector(Vector2 collisionVector) { this.collisionVector = collisionVector; }

	// ------------------------------------------------------------
	// Debug
	// ------------------------------------------------------------

	@Override
	public String toString() {
		return pos.toString();
	}

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

	public void drawOutline(Graphics2D graphics) {
		Graphics2D graphics2 = (Graphics2D) graphics.create();
		graphics2.setColor(Color.BLUE);
		graphics2.drawRect(	(int) pos.getX(),
							(int) pos.getY(),
							(int) size.getWidth(),
							(int) size.getHeight());
		graphics2.dispose();
	}
}
