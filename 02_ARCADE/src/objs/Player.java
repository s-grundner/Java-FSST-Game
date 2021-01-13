package objs;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JOptionPane;

import assets.Animation;
import config.Config;
import main.Game;
import math.Vector2;
import misc.Question;
import misc.SQuestionL;
import movement.Controller;
import objs.enumerators.Maps;
import objs.enumerators.Objects;
import objs.enumerators.PlayerStats;
import objs.enumerators.ProjectileType;
import objs.properties.Animated;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Player extends Entity implements Animated {

	private Controller controller;
	private PlayerStats stats;
	private SQuestionL sql;
	private int animationIndex;
	private int chargingIndex;
	private int swIndex;
	private double nextImg = 0.0;
	private boolean charging;
	private boolean[] sw;

	public Player(Game game, Controller controller) {
		super(game);
		this.controller = controller;

		init();
	}

	@Override
	public void assignType() {
		object = Objects.PLAYER;
	}

	private void init() {
		setDefaultName("NTT_Player");
		setImg(defaultName);
		setProjectileType(ProjectileType.BULLET);
		setPos(game.getMap().getSpawn());
		stats = PlayerStats.P_DEFAULT;
		animationIndex = 0;
		chargingIndex = 0;
		swIndex = 1;
		charging = false;
		sql = new SQuestionL();
		sw = new boolean[3];
		for (int i = 0; i < sw.length; i++) {
			sw[i] = true;
		}
	}

	// ------------------------------------------------------------
	// update
	// ------------------------------------------------------------

	@Override
	public void update() {
		super.update();
		setShootingVector(getMouseVector());

		vector.setVector(	0,
							0);
		Keys();
		colliding();
		initAnim();
		move(stats.getSpeed());
	}

	public void Keys() {
		if (controller.reqUp()) {
			vector.setY(-1);
			pointingVector.setToUnitVector();
			pointingVector.setY(pointingVector.getY() - 1);
		}
		if (controller.reqDown()) {
			vector.setY(1);
			pointingVector.setToUnitVector();
			pointingVector.setY(pointingVector.getY() + 1);
		}
		if (controller.reqRight()) {
			vector.setX(1);
			pointingVector.setToUnitVector();
			pointingVector.setX(pointingVector.getX() + 1);
		}
		if (controller.reqLeft()) {
			vector.setX(-1);
			pointingVector.setToUnitVector();
			pointingVector.setX(pointingVector.getX() - 1);
		}
		if (controller.reset()) {
			setDefaultPos();
		}
		if (controller.evade()) {
			Animation.P_EVADE.setActive(true);
			stats = PlayerStats.P_EVADE;
		} else {
			stats = PlayerStats.P_DEFAULT;
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
			if (sw[0]) {
				game.initMap(Maps.values()[swIndex]);
				setDefaultPos();
				swIndex++;
			}
			sw[0] = false;
		} else {
			sw[0] = true;
		}
		if (controller.question()) {
			if (sw[1]) {
				askQuestion();
			}
			sw[1] = false;
		} else {
			sw[1] = true;
		}
		if (controller.spawners()) {
			if (sw[2]) {
				game.runSpawners();
			}
			sw[2] = false;
		} else {
			sw[2] = true;
		}
	}

	@Override
	public void colliding() {
		if (colliding) {
			pos = prevPos;
		} else {
			prevPos = pos;
		}
	}

	public void askQuestion() {
		Question q = sql.getRandomQ();
		String answer = JOptionPane.showInputDialog(null,
													q.getQ(),
													"Question",
													3);
		if (q.isCorrect(answer)) {
			System.out.println("uwu");
		} else {
			System.out.println("òwó");
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

//		if (controller.shoot() || controller.charge() || charging) {
			transform.setToRotation(getMouseVector().getX(),
									getMouseVector().getY(),
									pos.getX() + size.getWidth() / 2,
									pos.getY() + size.getHeight() / 2);
			graphics.transform(transform);
//		} else {
//			transform.setToRotation(pointingVector.getX(),
//									pointingVector.getY(),
//									pos.getX() + size.getWidth() / 2,
//									pos.getY() + size.getHeight() / 2);
//			graphics.transform(transform);
//		}
		transform.translate(pos.getX(),
							pos.getY());
		graphics.transform(transform);

		return transform;
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

	public Controller getController() { return controller; }
}
