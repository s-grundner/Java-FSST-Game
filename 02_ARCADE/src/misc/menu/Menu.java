package misc.menu;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import config.Config;
import main.Game;
import math.GameScaler;
import movement.PController;
import objs.properties.Size;
import objs.properties.Spritesheet;

/**
 * @author Simon Grundner
 *		   3AHEL
 */

public class Menu {

	private PController controller;
	private Spritesheet logo;
	private ArrayList<Button> buttons;
	private GameScaler menuScaler;
	private Game game;

	public Menu(Game game, PController controller) {
		this.controller = controller;
		logo = new Spritesheet("MENU_Logo");
		menuScaler = new GameScaler(new Size(	Config.CANVAS_WIDTH / (Config.TILESIZE * 4),
												Config.CANVAS_HEIGHT / (Config.TILESIZE * 4)));
	}

	public void update() {
		if(controller.evade()) {
			System.out.println(1);
//			game.setGameState(GameState.GAME);
//			game.initAudio();
		}
	}

	public void draw(Graphics2D graphics) {
		Graphics2D graphics2 = (Graphics2D) graphics.create();
		AffineTransform latch = graphics2.getTransform();
		menuScaler.rescale();
		graphics2.drawImage(logo.getSheet(),
							(int) (Config.CANVAS_WIDTH / (2 * Config.SCALE)
									- (Config.TILESIZE * logo.getImgHorizontalCount() / 2)),
							(int) (Config.CANVAS_HEIGHT / (2 * Config.SCALE)
									- (Config.TILESIZE * logo.getImgVerticalCount() / 2)),
							null);

		graphics2.setTransform(latch);
		graphics2.dispose();
	}
}
