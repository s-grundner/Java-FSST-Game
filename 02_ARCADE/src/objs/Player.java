package objs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JOptionPane;

import assets.Animation;
import assets.Audio;
import config.Config;
import main.Game;
import math.Vector2;
import misc.Question;
import misc.SQuestionL;
import movement.PController;
import movement.PlayerController;
import objs.enumerators.GameState;
import objs.enumerators.Maps;
import objs.enumerators.Objects;
import objs.enumerators.PlayerStats;
import objs.enumerators.ProjectileType;
import objs.properties.Animated;
import objs.properties.Position;
import objs.properties.Spritesheet;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Player extends Entity implements Animated {

	private PController controller;
	private PlayerStats stats;
	private SQuestionL sql;
	private Spritesheet hpSprite;
	private Audio audio;
	private int animationIndex;
	private int chargingIndex;
	private int swIndex;
	private double nextImg = 0.0;
	private boolean charging;
	private boolean[] sw;

	public Player(Game game, PController controller) {
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
		hpSprite = new Spritesheet("GAME_MISC_HP");
		audio = new Audio("GAME_PEW.wav");
		stats = PlayerStats.P_DEFAULT;
		hp = stats.getHp();
		animationIndex = 0;
		chargingIndex = 0;
		swIndex = 1;
		charging = false;
		sql = new SQuestionL();
		sw = new boolean[5];
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

		if ((score % 10 == 0) && (score != 0)) {
			if (swIndex >= Maps.values().length) {
				swIndex = 0;
			}
			if (sw[0]) {
				for (GameObj obj : game.parseObjs()) {
					if (!this.equals(obj)) {
						obj.setAlive(false);
					}
				}
				sw[1] = true;
				game.initMap(Maps.values()[swIndex]);
				setDefaultPos();
				swIndex++;
				isShooting = false;
				vector.setVector(	0,
									0);
				askQuestion();
			}
			sw[0] = false;
		} else {
			sw[0] = true;
		}
		if (sw[1]) {
			pos = new Position(game.getMap().getSpawn());
			sw[1] = false;
		} else {
			move(stats.getSpeed());
		}
		if (hp < 0) {
			outitialize();
		}
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
	}

	@Override
	public void colliding() {
		if (colliding) {
			if (!controller.evade()) {
				if (currentCollision instanceof Projectile) {
					Projectile hitMark = (Projectile) getCurrentCollision();
					if (sw[2]) {
						hp -= hitMark.getProjectile().getDmg();
					}
					sw[2] = false;
				}
			}
			pos = prevPos;
		} else {
			currentCollision = null;
			sw[2] = true;
			prevPos = pos;
		}
	}

	@Override
	public void setAlive(boolean alive) {
		super.setAlive(alive);
	}

	public void askQuestion() {
		Question q = sql.getRandomQ();
		String answer = JOptionPane.showInputDialog(null,
													q.getQ(),
													"Question",
													3);
		if (q.isCorrect(answer)) {
			System.out.println("uwu");
			if (hp != stats.getHp()) {
				hp++;
			}
		} else {
			System.out.println("òwó");
			hp--;
		}
	}

	private void outitialize() {
		game.parseObjs().forEach(obj -> obj.setAlive(false));
		game.setGameState(GameState.END);
	}

	@Override
	public void attack() {
		super.attack();
		audio.play();
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

	public void drawStats(Graphics2D graphics) {
		int[] hpArr = new int[stats.getHp() / 2];
		for (int i = 0; i < hpArr.length; i++) {
			if (i < (stats.getHp() - hp) / 2) {
				hpArr[i] = 2;
			} else if (i < (stats.getHp() - hp + 1) / 2) {
				hpArr[i] = 1;
			}
			graphics.drawImage(	hpSprite.getImage(	hpArr[i],
													0),
								0,
								16 * i,
								null);
		}
		graphics.setColor(new Color(0, 0, 0, 120));
		graphics.fillRect(	16,
							0,
							64,
							16);
		graphics.setColor(Color.WHITE);
		graphics.drawString("Score: " + String.valueOf(score),
							19,
							12);

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

	public PController getController() { return controller; }
}
