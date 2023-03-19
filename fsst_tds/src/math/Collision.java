package math;

import java.util.ArrayList;

import main.Game;
import objs.GameObj;
import objs.properties.Hitbox;
import objs.properties.Position;
import objs.properties.Size;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Collision {

	private Game game;
	private ArrayList<GameObj> objs;
	private ArrayList<Hitbox> mapHitbox;

	public Collision(Game game) {
		this.game = game;
	}

	public void update() {
		objs = game.parseObjs();
		mapHitbox = game.getMap().getMapCollisions();

		for (GameObj colObj : objs) {
			colObj.setColliding(false);
		}
		updateMap();
		updateObjs();
	}

	private void updateMap() {
		for (Hitbox colHB : mapHitbox) {
			for (GameObj colObj : objs) {
				if (colHB.getPos().getX() < colObj.getPos().getX() + colObj.getSize().getWidth()
						&& colHB.getPos().getX() + colHB.getSize().getWidth() > colObj.getPos().getX()
						&& colHB.getPos().getY() < colObj.getPos().getY() + colObj.getSize().getHeight()
						&& colHB.getPos().getY() + colHB.getSize().getHeight() > colObj.getPos().getY()) {
					colObj.setColliding(true);
					colObj.setCurrentCollisionHitbox(colHB);
				}
			}
		}
	}

	private void updateObjs() {
		for (GameObj colObj : objs) {
			for (GameObj colObj2 : objs) {
				if (colObj != colObj2) {
					if (colObj2.getPos().getX() < colObj.getPos().getX() + colObj.getSize().getWidth()
							&& colObj2.getPos().getX() + colObj2.getSize().getWidth() > colObj.getPos().getX()
							&& colObj2.getPos().getY() < colObj.getPos().getY() + colObj.getSize().getHeight()
							&& colObj2.getPos().getY() + colObj2.getSize().getHeight() > colObj.getPos().getY()) {
						colObj2.setColliding(true);
						colObj.setColliding(true);

						colObj.setCurrentCollision(colObj2);
						colObj2.setCurrentCollision(colObj);
					}
				}
			}
		}
	}

	public boolean checkCollision(Position pos, Size size) {
		boolean ret = false;
		for (GameObj colObj : objs) {
			if (pos.getX() < colObj.getPos().getX() + colObj.getSize().getWidth()
					&& pos.getX() + size.getWidth() > colObj.getPos().getX()
					&& pos.getY() < colObj.getPos().getY() + colObj.getSize().getHeight()
					&& pos.getY() + size.getHeight() > colObj.getPos().getY()) {
				ret = true;
			}
		}
		for (Hitbox colHB : mapHitbox) {
			if (pos.getX() < colHB.getPos().getX() + colHB.getSize().getWidth()
					&& pos.getX() + size.getWidth() > colHB.getPos().getX()
					&& pos.getY() < colHB.getPos().getY() + colHB.getSize().getHeight()
					&& pos.getY() + size.getHeight() > colHB.getPos().getY()) {
				ret = true;
			}
		}
		return ret;
	}
}
