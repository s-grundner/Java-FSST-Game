package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import assets.Audio;
import config.Config;
import math.Collision;
import movement.Input;
import movement.PlayerController;
import objs.Entity;
import objs.GameObj;
import objs.Player;
import objs.enumerators.CustomHitbox;
import objs.enumerators.Maps;
import objs.map.Map;
import objs.properties.Hitbox;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public class Game {

	private Config config;
	private Audio audio;
	private boolean playAudio;
	private boolean loopAudio;
	private long loopTime;
	private double nextBulletTime;
	private Gui gui;
	private Map map;
	private ArrayList<GameObj> objs;
	private ArrayList<Entity> ntts;
	private Input input;
	private Collision collision;

	public Game() {
		config = new Config();
		config.init();

		input = new Input();
		gui = new Gui(Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT, input);

		initMap(Maps.MAP2);
		initObjs();
		initAudio();

		collision = new Collision(this);
		
		nextBulletTime = 0.0;
	}

	// ------------------------------------------------------------
	// Initializing
	// ------------------------------------------------------------

	private void initObjs() {
		objs = new ArrayList<GameObj>();
		ntts = new ArrayList<Entity>();
		
//		objs.add(new Hostile(this,
//		                     new Hitbox(CustomHitbox.OBJ_TILE),
//		                     EntityStats.H_HOSTILE1));
		ntts.add(new Player(this,
		                    new Hitbox(CustomHitbox.NTT_P_PLAYER),
							new PlayerController(input)));
	}

	private void initAudio() {
		playAudio = true;
		loopAudio = false;
		audio = new Audio("GAME-Intro.wav");
	}

	public void initMap(Maps maps) {
		this.map = new Map(this);
		this.map.loadMap(maps);
	}

	// ------------------------------------------------------------
	// update - render
	// ------------------------------------------------------------

	public void update() {

		collision.update();
		
		for (GameObj obj : objs) {
			obj.update();

			if ((obj.getPos().getX() < 0 || obj.getPos().getY() < 0)
					|| (obj.getPos().getX() > Config.CANVAS_WIDTH || obj.getPos().getY() > Config.CANVAS_HEIGHT)) {
				obj.setAlive(false);
			}
		}
		for (Entity ntt : ntts) {
			ntt.update();

			if ((System.currentTimeMillis() > nextBulletTime) && ntt.getShootingState()) {
				objs.add(ntt.attack());
				nextBulletTime = System.currentTimeMillis() + ntt.getAttackSpeed();
			}
		}
		// ------------------------------------------------------------
		// Iterators
		// ------------------------------------------------------------

		for (Iterator<GameObj> obji = objs.iterator(); obji.hasNext();) {
			GameObj obj = obji.next();
			if (!obj.isAlive()) {
				obji.remove();
			}
		}
		for (Iterator<Entity> ntti = ntts.iterator(); ntti.hasNext();) {
			Entity ntt = ntti.next();
			if (!ntt.isAlive()) {
				ntti.remove();
			}
		}
		// ------------------------------------------------------------
		// Audio
		// ------------------------------------------------------------

		if (Config.AUDIO) {
			if (!loopAudio && playAudio) {
				playAudio = false;
				loopTime = System.currentTimeMillis() * 1000 + audio.getClip().getMicrosecondLength();
				audio.play();
			} else if (loopAudio && playAudio) {
				playAudio = false;
				audio.select("GAME-Loop.wav");
				audio.loop();
			}
			if (!loopAudio && (System.currentTimeMillis() * 1000 > loopTime)) {
				loopAudio = true;
				playAudio = true;
			}
		}
	}

	public void render() {
		gui.render(this);
	}

	// ------------------------------------------------------------
	// Gui-Parsing
	// ------------------------------------------------------------

	public ArrayList<GameObj> parseObjs() {
		return objs;
	}

	public ArrayList<Entity> parseNtts() {
		return ntts;
	}

	public void addObjs(GameObj obj) {
		objs.add(obj);
	}

	public void drawMap(Graphics2D graphics) {
		this.map.drawMap(graphics);
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public Config getConfig() { return config; }

	public Gui getGui() { return gui; }

	public Map getMap() { return map; }

	// ------------------------------------------------------------
	// Debug
	// ------------------------------------------------------------

	public void listObjs() {
		for (GameObj obj : objs) {
			System.out.println(obj.getPos());
		}
	}
}
