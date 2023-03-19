package objs.enumerators;

import config.Config;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public enum CustomHitbox {

	// ------------------------------------------------------------
	// Enumerators (Hitboxes)
	// ------------------------------------------------------------

	OBJ_TILE(Config.TILESIZE, Config.TILESIZE), NTT_P_PLAYER(12, 16), W_PROJ_BULLET(6, 16), W_PROJ_LASER(14, 16);

	// ------------------------------------------------------------
	// Members - Constructor
	// ------------------------------------------------------------

	private int width;
	private int height;

	private CustomHitbox(int width, int height) {
		this.width = width;
		this.height = height;
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public int getWidth() { return width; }

	public void setWidth(int width) { this.width = width; }

	public int getHeight() { return height; }

	public void setHeight(int height) { this.height = height; }
}
