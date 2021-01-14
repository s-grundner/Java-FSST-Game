package main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import assets.Audio;
import config.Config;
import math.Collision;
import math.Spawner;
import misc.menu.Menu;
import misc.menu.MenuController;
import movement.Input;
import movement.PlayerController;
import objs.GameObj;
import objs.Hostile;
import objs.Player;
import objs.enumerators.GameState;
import objs.enumerators.HostileType;
import objs.enumerators.Maps;
import objs.map.Map;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Game {

	private Config config;
	private Audio audio;
	private boolean playAudio;
	private boolean loopAudio;
	private long loopTime;
	private Gui gui;
	private Map map;
	private ArrayList<GameObj> objs;
	private ArrayList<GameObj> add;
	private ArrayList<Spawner> spawns;
	private Input input;
	private Collision collision;
	private GameState gameState;
	private Menu menu;

	public Game() {
		config = new Config();
		config.init();
		gameState = GameState.GAME;

		input = new Input();
		gui = new Gui(Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT, input);
		menu = new Menu(this, new PlayerController(input));
		collision = new Collision(this);

		initMap(Maps.MAP1);
		initObjs();
		initAudio();
		initSpawns();
	}

	// ------------------------------------------------------------
	// Initializing
	// ------------------------------------------------------------

	private void initObjs() {
		objs = new ArrayList<GameObj>();
		add = new ArrayList<GameObj>();

		objs.add(new Player(this, new PlayerController(input)));
	}

	public void initAudio() {
		playAudio = true;
		loopAudio = false;
		audio = new Audio("GAME-Intro.wav");
	}

	public void initMap(Maps maps) {
		this.map = new Map(this);
		this.map.loadMap(maps);
	}

	private void initSpawns() {
		spawns = new ArrayList<>();
		spawns.add(new Spawner(this, new Hostile(this, HostileType.HOSTILE1), 1500));
	}
	// ------------------------------------------------------------
	// update - render
	// ------------------------------------------------------------

	public void update() {
		switch (gameState) {
			case MENU:

				config.refresh(gui);
				// ------------------------------------------------------------
				// Audio
				// ------------------------------------------------------------

//				if (Config.AUDIO) {
//					if (playAudio) {
//						playAudio = false;
//						audio.select("GAME-Menu.wav");
//						audio.loop();
//					}
//				}
			break;
			case END:
				audio.stop();
			break;
			case GAME:
				collision.update();
				objs.addAll(add);
				add.clear();
				objs.forEach(obj -> obj.update());
				if (objs.size() < 250) {
					spawns.forEach(spwn -> spwn.update());
				}

				for (Iterator<GameObj> obji = objs.iterator(); obji.hasNext();) {
					GameObj obj = obji.next();
					if (!obj.isAlive()) {
						obji.remove();
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
			break;
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

	public void addObjs(GameObj obj) {
		add.add(obj);
	}

	public void drawMapTop(Graphics2D graphics) {
		this.map.drawMapTop(graphics);
	}

	public void drawMapBot(Graphics2D graphics) {
		this.map.drawMapBot(graphics);
	}

	public Collision getCollision() { return collision; }

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public Config getConfig() { return config; }

	public Gui getGui() { return gui; }

	public Map getMap() { return map; }

	public GameState getGameState() { return gameState; }

	public void setGameState(GameState gameState) { this.gameState = gameState; }

	public Menu getMenu() { return menu; }

	// ------------------------------------------------------------
	// Debug
	// ------------------------------------------------------------

	public void listObjs() {
		for (GameObj obj : objs) {
			System.out.println(obj.getPos());
		}
	}

	public void runSpawners() {
		spawns.forEach(spwn -> spwn.setRunning(!spwn.isRunning()));
	}
}
