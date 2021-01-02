package math;

import java.util.ArrayList;

import main.Game;
import objs.Entity;
import objs.GameObj;
import objs.properties.Hitbox;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Collision {

	private Game game;
	private ArrayList<GameObj> objs;
	private ArrayList<Entity> ntts;
	private ArrayList<Hitbox> mapHitbox;

	public Collision(Game game) {
		this.game = game;
	}

	public void update() {
		objs = game.parseObjs();
		ntts = game.parseNtts();
		mapHitbox = game.getMap().getMapCollisions();

		for (GameObj colObj : objs) {
			colObj.setColliding(false);
		}
		for (Entity colNtt : ntts) {
			colNtt.setColliding(false);
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
				}
			}
			for (Entity colNtt : ntts) {
				if (colHB.getPos().getX() < colNtt.getPos().getX() + colNtt.getSize().getWidth()
						&& colHB.getPos().getX() + colHB.getSize().getWidth() > colNtt.getPos().getX()
						&& colHB.getPos().getY() < colNtt.getPos().getY() + colNtt.getSize().getHeight()
						&& colHB.getPos().getY() + colHB.getSize().getHeight() > colNtt.getPos().getY()) {
					colNtt.setColliding(true);
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
			for (Entity colNtt2 : ntts) {
				if (colNtt2.getPos().getX() < colObj.getPos().getX() + colObj.getSize().getWidth()
						&& colNtt2.getPos().getX() + colNtt2.getSize().getWidth() > colObj.getPos().getX()
						&& colNtt2.getPos().getY() < colObj.getPos().getY() + colObj.getSize().getHeight()
						&& colNtt2.getPos().getY() + colNtt2.getSize().getHeight() > colObj.getPos().getY()) {
					colNtt2.setColliding(true);
					colObj.setColliding(true);

					colObj.setCurrentCollision(colNtt2);
					colNtt2.setCurrentCollision(colObj);
				}
			}
		}
		for (Entity colNtt : ntts) {
			for (GameObj colObj2 : objs) {
				if (colObj2.getPos().getX() < colNtt.getPos().getX() + colNtt.getSize().getWidth()
						&& colObj2.getPos().getX() + colObj2.getSize().getWidth() > colNtt.getPos().getX()
						&& colObj2.getPos().getY() < colNtt.getPos().getY() + colNtt.getSize().getHeight()
						&& colObj2.getPos().getY() + colObj2.getSize().getHeight() > colNtt.getPos().getY()) {
					colObj2.setColliding(true);
					colNtt.setColliding(true);

					colNtt.setCurrentCollision(colObj2);
					colObj2.setCurrentCollision(colNtt);
				}
			}
			for (Entity colNtt2 : ntts) {
				if (colNtt != colNtt2) {
					if (colNtt2.getPos().getX() < colNtt.getPos().getX() + colNtt.getSize().getWidth()
							&& colNtt2.getPos().getX() + colNtt2.getSize().getWidth() > colNtt.getPos().getX()
							&& colNtt2.getPos().getY() < colNtt.getPos().getY() + colNtt.getSize().getHeight()
							&& colNtt2.getPos().getY() + colNtt2.getSize().getHeight() > colNtt.getPos().getY()) {
						colNtt2.setColliding(true);
						colNtt.setColliding(true);

						colNtt.setCurrentCollision(colNtt2);
						colNtt2.setCurrentCollision(colNtt);
					}
				}
			}
		}
	}
}
