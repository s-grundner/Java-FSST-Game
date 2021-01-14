package main;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

/**
 * @author Simon Grundner
 *		   3AHEL
 */
public class Launcher {

	public static void main(String[] args) {
		new Thread(new GameLoop(new Game())).start();
	}

}
