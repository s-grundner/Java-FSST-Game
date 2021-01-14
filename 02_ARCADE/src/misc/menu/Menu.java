package misc.menu;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import config.Config;
import main.Game;
import math.GameScaler;
import objs.enumerators.GameState;
import objs.properties.Size;
import objs.properties.Spritesheet;

/**
 * @author Simon Grundner
 *		   3AHEL
 */

public class Menu {

	private MController controller;
	private Spritesheet logo;
	private Spritesheet start;
	private GameScaler menuScaler;
	private Game game;

	public Menu(Game game, MenuController menuController) {
		this.game = game;
		this.controller = menuController;
		logo = new Spritesheet("MENU_Logo");
		start = new Spritesheet("format-START");
		menuScaler = new GameScaler(new Size(	Config.CANVAS_WIDTH / (Config.TILESIZE * 4),
												Config.CANVAS_HEIGHT / (Config.TILESIZE * 4)));
	}

	public void update() {
		if (controller.getSpace()) {
			game.getAudio().stop();
			game.setGameState(GameState.GAME);
		}
		if (controller.exit()) {
			System.exit(0);
		}
	}

	public void draw(Graphics2D graphics) {
		menuScaler.rescale();
		Graphics2D graphics2 = (Graphics2D) graphics.create();
		AffineTransform latch = graphics2.getTransform();
		graphics2.drawImage(logo.getSheet(),
							(int) (Config.CANVAS_WIDTH / (2 * Config.SCALE)
									- (Config.TILESIZE * logo.getImgHorizontalCount() / 2)),
							(int) (Config.CANVAS_HEIGHT / (2 * Config.SCALE)
									- (Config.TILESIZE * logo.getImgVerticalCount() / 2)),
							null);
		graphics2.drawImage(start.getSheet(),
							(int) (Config.CANVAS_WIDTH / (2 * Config.SCALE)
									- (Config.TILESIZE * start.getImgHorizontalCount() / 2)),
							(int) (56 + (Config.CANVAS_HEIGHT / (2 * Config.SCALE)
									- (Config.TILESIZE * start.getImgVerticalCount() / 2))),
							null);

		graphics2.setTransform(latch);
		graphics2.dispose();
	}
}
