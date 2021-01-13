package math;

import main.Game;
import objs.GameObj;
import objs.Hostile;
import objs.Item;
import objs.Player;
import objs.Projectile;
import objs.Tile;

/**
 * @author Simon Grundner
 *		   3AHEL
 */

public class Spawner {

	private Game game;
	private GameObj obj;
	private int spawnTime;
	private long estTime;
	private boolean running;

	public Spawner(Game game, GameObj obj, int spawnTime) {
		this.game = game;
		this.obj = obj;
		this.spawnTime = spawnTime;
		running = true;
		
	}

	public void update() {
		if (running) {
			if ((System.currentTimeMillis() > estTime)) {
				estTime = System.currentTimeMillis() + spawnTime;
				GameObj obj2 = obj;
				switch (obj.getObject()) {
					case HOSTILE:
						obj2 = new Hostile(game, ((Hostile) obj).getType());
						break;
					case ITEM:
						obj2 = new Item(game, ((Item) obj).getItemType());
						break;
					case PLAYER:
						obj2 = new Player(game, ((Player) obj).getController());
						break;
					case PROJECTILE:
						obj2 = new Projectile(game, ((Projectile) obj).getProjectile(), ((Projectile) obj).getHost());
						break;
					case TILE:
						obj2 = new Tile(game);
						break;
					default:
						break;
				}
				obj2.randomize();
				game.addObjs(obj2);
			}
		}
	}

	public boolean isRunning() { return running; }

	public void setRunning(boolean running) { this.running = running; }
}
