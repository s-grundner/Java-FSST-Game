package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import assets.Animation;
import config.Config;
import main.Game;
import math.Vector2;
import movement.Controller;
import objs.enumerators.EntityStats;
import objs.enumerators.Maps;
import objs.enumerators.ProjectileType;
import objs.properties.Animated;
import objs.properties.Hitbox;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Player extends Entity implements Animated {

	private Controller controller;
	private int animationIndex;
	private int chargingIndex;
	private int swIndex;
	private double nextImg = 0.0;
	private boolean charging;
	private boolean swMap;

	public Player(Game game, Hitbox hitbox, Controller controller) {
		super(game, hitbox);

		this.controller = controller;
		init();
	}

	private void init() {
		setDefaultName("NTT_Player");
		setImg(defaultName);
		setProjectileType(ProjectileType.BULLET);
		setPos(game.getMap().getSpawn());

		animationIndex = 0;
		chargingIndex = 0;
		swIndex = 0;
		charging = false;
		swMap = true;
	}

	@Override
	public void initStats() {
		stats = EntityStats.P_DEFAULT;
	}

	// ------------------------------------------------------------
	// update
	// ------------------------------------------------------------

	@Override
	public void update() {
		updateHitbox();
		setShootingVector(getMouseVector());

		vector.setVector(	0,
							0);
		Keys();
		isColliding();
		initAnim();

		move(stats.getSpeed());
		System.out.println(pointingVector.toString());
	}

	public void Keys() {
		if (controller.reqUp()) {
			vector.setY(-1);
			pointingVector.setToUnitVector();
			
			pointingVector.setY(pointingVector.getY()-1);
		}
		if (controller.reqDown()) {
			vector.setY(1);
			pointingVector.setToUnitVector();
			
			pointingVector.setY(pointingVector.getY()+1);
		}
		if (controller.reqRight()) {
			vector.setX(1);
			pointingVector.setToUnitVector();
			
			pointingVector.setX(pointingVector.getX()+1);
		}
		if (controller.reqLeft()) {
			vector.setX(-1);
			pointingVector.setToUnitVector();
			pointingVector.setX(pointingVector.getX()-1);
		}
		if (controller.reset()) {
			setDefaultPos();
		}
		if (controller.evade()) {
			Animation.P_EVADE.setActive(true);
			stats = EntityStats.P_EVADE;
		} else {
			stats = EntityStats.P_DEFAULT;
		}
		if (controller.shoot()) {
			isShooting = true;
		} else {
			isShooting = false;
		}
		if (controller.charge()) {
			if (!charging) {
				charging = true;
				Animation.P_CHARGE1.setActive(true);
			}
		}
		if (controller.exit()) {
			System.exit(0);
		}
		if (controller.refresh()) {
			game.getMap().getGameScaler().rescale();
			game.getConfig().refresh(game.getGui());
		}
		if (controller.swMap()) {
			if (swIndex >= Maps.values().length) {
				swIndex = 0;
			}
			if (swMap) {
				game.initMap(Maps.values()[swIndex]);
				setDefaultPos();
				swIndex++;
			}
			swMap = false;
		} else {
			swMap = true;
		}
	}

	@Override
	public void isColliding() {
		if (colliding) {
			if (vector.getX() < 0 || vector.getX() > 0) {
//				vector.setX(-vector.getX());
//				stats.setSpeed(stats.getSpeed() * Math.sin(pointingVector.getAngleToXAxis()));
			}
			if (vector.getY() < 0 || vector.getY() > 0) {
//				vector.setY(-vector.getY());
//				stats.setSpeed(stats.getSpeed() * Math.cos(pointingVector.getAngleToXAxis()));
			}
		} else {}
	}

	// ------------------------------------------------------------
	// Animate
	// ------------------------------------------------------------

	@Override
	public void initAnim() {
		if (Animation.P_EVADE.getActive()) {
			isShooting = false;
			anim(Animation.P_EVADE);

			if (!Animation.P_EVADE.getActive()) {
				setImg(defaultName);
			}
		}

		else if (Animation.P_CHARGE1.getActive()) {
			isShooting = false;
			anim(Animation.P_CHARGE1);
			chargingIndex = -1;
			Animation.P_CHARGING_STAGES.setActive(true);
		}

		else if (Animation.P_CHARGE2.getActive()) {
			anim(Animation.P_CHARGE2);

			if (animationIndex >= 3 && animationIndex <= 8) {
				setProjectileType(ProjectileType.LASER);
				isShooting = true;
			} else {
				isShooting = false;
				setProjectileType(ProjectileType.BULLET);
			}
			if (!Animation.P_CHARGE2.getActive()) {
				charging = false;
			}
		}

		else if (Animation.P_CHARGING_STAGES.getActive()) {
			isShooting = false;

			if (controller.charge() && charging) {
				anim(Animation.P_CHARGING_STAGES);
			} else {
				Animation.P_CHARGING_STAGES.setActive(false);
			}
			if (!Animation.P_CHARGING_STAGES.getActive()) {
				if (chargingIndex < 0) {
					chargingIndex = 0;
				}
				Animation.P_CHARGE2.setMode(chargingIndex);
				ProjectileType.LASER.setMode(chargingIndex);
				animationIndex = 0;
				Animation.P_CHARGE2.setActive(true);
			}
		}
	}

	@Override
	public void anim(Animation animation) {
		setSpritesheet(animation.getFileName());

		if ((System.currentTimeMillis() > nextImg)) {
			if (animationIndex < spritesheet.getImgCount()) {
				img = spritesheet.getImage(	animationIndex,
											animation.getMode());
				animationIndex++;

				if (chargingIndex < Animation.P_CHARGING_STAGES.getFrameCount() - 1) {
					chargingIndex++;
				}
				nextImg = System.currentTimeMillis() + (animation.getDuration() / animation.getFrameCount()) * 1000;
			}
		}
		if (animationIndex == animation.getFrameCount()) {
			animationIndex = 0;
			animation.setActive(false);
		}
	}

	// ------------------------------------------------------------
	// Draw
	// ------------------------------------------------------------

	@Override
	public AffineTransform transform(Graphics2D graphics) {
		AffineTransform transform;
		transform = AffineTransform.getTranslateInstance(	pos.getX() + size.getWidth() / 2,
															pos.getY() + size.getHeight() / 2);

		if (controller.shoot() || controller.charge() || charging) {
			transform.setToRotation(getMouseVector().getX(),
									getMouseVector().getY(),
									pos.getX() + size.getWidth() / 2,
									pos.getY() + size.getHeight() / 2);
			graphics.transform(transform);
		} else {
			transform.setToRotation(pointingVector.getX(),
									pointingVector.getY(),
									pos.getX() + size.getWidth() / 2,
									pos.getY() + size.getHeight() / 2);
			graphics.transform(transform);
		}
		transform.translate(pos.getX(),
							pos.getY());
		graphics.transform(transform);

		return transform;
	}

	@Override
	public void draw(Graphics2D graphics) {
		Graphics2D graphics2 = (Graphics2D) graphics.create();
		AffineTransform latch = graphics2.getTransform();
		graphics.drawImage(	getImg(),
							transform(graphics2),
							null);

		graphics.setTransform(latch);
		graphics.dispose();
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public Vector2 getMouseVector() {
		Vector2 vector = new Vector2(	controller.getMousePos().getX()
												- (pos.getX() + (Config.TILESIZE / 2)) * Config.SCALE,
										controller.getMousePos().getY()
												- (pos.getY() + (Config.TILESIZE / 2)) * Config.SCALE);
		return vector;
	}
}
