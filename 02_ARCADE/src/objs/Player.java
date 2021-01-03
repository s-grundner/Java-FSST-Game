package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import assets.Animation;
import config.Config;
import main.Game;
import math.VectorR2;
import movement.Controller;
import objs.enumerators.EntityStats;
import objs.enumerators.Maps;
import objs.enumerators.ProjectileType;
import objs.properties.Animated;

/**
 * @author	Simon Grundner <br>
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
	private boolean vectorUpd;
	private VectorR2 colV;

	public Player(Game game, Controller controller) {
		super(game);

		this.controller = controller;

		setDefaultName("NTT_Player");
		setImg(defaultName);
		setProjectileType(ProjectileType.BULLET);
		setPos(game.getMap().getSpawn());

		animationIndex = 0;
		chargingIndex = 0;
		swIndex = 0;
		charging = false;
		swMap = true;
		vectorUpd = false;
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
		setShootingVector(getMouseVector());
		vectorUpd = false;
		vector.setVector(	0,
							0);
		Keys();

		if (vectorUpd) {
			pointingVector = vector;
		}
		if (!vector.equals(colV)) {
			move(stats.getSpeed());
		}
		isColliding();
		initAnim();
	}

	public void Keys() {
		if (controller.reqUp()) {
			vector.setY(-1);
			vectorUpd = true;
		}
		if (controller.reqDown()) {
			vector.setY(1);
			vectorUpd = true;
		}
		if (controller.reqRight()) {
			vector.setX(1);
			vectorUpd = true;
		}
		if (controller.reqLeft()) {
			vector.setX(-1);
			vectorUpd = true;
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
			colV = vector;
			colliding = false;

			System.out.println(colV.toString());
		} else {
			colV = null;
		}
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

	public VectorR2 getMouseVector() {
		VectorR2 vector = new VectorR2(	controller.getMousePos().getX()
												- (pos.getX() + (Config.TILESIZE / 2)) * Config.SCALE,
										controller.getMousePos().getY()
												- (pos.getY() + (Config.TILESIZE / 2)) * Config.SCALE);
		return vector;
	}
}
