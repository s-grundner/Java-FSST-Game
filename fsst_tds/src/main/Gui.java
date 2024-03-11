
package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import config.Config;
import movement.Input;
import objs.properties.Spritesheet;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;
	private Spritesheet bg;
	private Canvas canvas;
	private BufferStrategy bs;

	public Gui(int width, int height, Input input) {
		setTitle("Ich will nicht mehr, ich will nicht mehr, ich habe die Nase voll");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setFocusable(true);

		add(canvas);

		canvas.addKeyListener(input);
		canvas.addMouseListener(input);
		canvas.addMouseMotionListener(input);
		pack();

		setLocationRelativeTo(null);
		setVisible(true);

		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();

		bg = new Spritesheet("Wallpaper1");
	}

	// ------------------------------------------------------------
	// Render Gui
	// ------------------------------------------------------------

	public void render(Game game) {
		Graphics2D graphics = (Graphics2D) bs.getDrawGraphics();
		graphics.drawImage(bg.getSheet(), 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		graphics.scale(	Config.SCALE, Config.SCALE);
		switch (game.getGameState()) {
			case END:
			break;
			case GAME:
				game.drawMapBot(graphics);
				game.parseObjs().forEach(obj -> obj.draw(graphics));
				game.drawMapTop(graphics);
				game.parseObjs().forEach(obj -> obj.drawStats(graphics));

			break;
			case MENU:
				game.getMenu().draw(graphics);
			break;
			default:
			break;
		}
		graphics.dispose();
		bs.show();
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public Canvas getCanvas() { return canvas; }
}
