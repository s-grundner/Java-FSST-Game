package objs.properties;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import config.Config;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public class Spritesheet {

	private BufferedImage sheet;
	private BufferedImage[][] parts;

	public Spritesheet(String fileName) {
		try {
			sheet = ImageIO.read(new File("src\\assets\\sprites\\" + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		parts = new BufferedImage[sheet.getWidth(null) / Config.TILESIZE][sheet.getHeight(null) / Config.TILESIZE];

		for (int i = 0; i < parts.length; i++) {
			for (int j = 0; j < parts[i].length; j++) {
				parts[i][j] = sheet.getSubimage((i * Config.TILESIZE), (j * Config.TILESIZE), Config.TILESIZE, Config.TILESIZE);
			}
		}
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public BufferedImage getImage(int pos, int row) {
		return parts[pos][row];
	}

	public BufferedImage[][] getParts() { return parts; }

	public BufferedImage getSheet() { return sheet; }

	public int getImgCount() { return parts.length; }

	public int getImgHorizontalCount() { return sheet.getWidth(null) / Config.TILESIZE; }

	public int getImgVerticalCount() { return sheet.getHeight(null) / Config.TILESIZE; }
}
