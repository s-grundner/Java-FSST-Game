package main;

import config.Config;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class GameLoop implements Runnable {

	private Game game;

	public static boolean running;
	private long nextStatTime;
	private int ups, fps;
	
	public GameLoop(Game game) {
		this.game = game;
	}

	//------------------------------------------------------------
	//Loop
	//------------------------------------------------------------
	
	@Override
	public void run() {
		running = true;
		nextStatTime = System.currentTimeMillis() + 1000;

		double delta = 0;
		long currentTime, lastUpd = System.currentTimeMillis();

		while (running) {
			currentTime = System.currentTimeMillis();
			double lastRenderTimeInSeconds = (currentTime - lastUpd) / 1000d;
			delta += lastRenderTimeInSeconds;
			lastUpd = currentTime;

			if (delta >= Config.REFRESH_RATE) {
				while (delta >= Config.REFRESH_RATE) {
					update();
					delta -= Config.REFRESH_RATE;

				}
				render();
			}
			printStats();
		}
	}

	//------------------------------------------------------------
	//update - render
	//------------------------------------------------------------
	
	private void update() {
		game.update();
		ups++;
	}

	private void render() {
		game.render();
		fps++;
	}
	
	//------------------------------------------------------------
	//Debug
	//------------------------------------------------------------
	
	private void printStats() {
		if (System.currentTimeMillis() > nextStatTime) {
			System.out.println(String.format("FPS: %d, UPS: %d", fps, ups));
			fps = 0;
			ups = 0;
			nextStatTime = System.currentTimeMillis() + 1000;
//			game.listObjs();
		}
	}
}

