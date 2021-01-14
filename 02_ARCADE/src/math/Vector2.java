package math;

import java.util.Random;

import objs.properties.Position;

/**
 * @author	Simon Grundner
 *			3AHEL
 */

public class Vector2 {

	private double x;
	private double y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2(Vector2 vector) {
		this.x = vector.getX();
		this.y = vector.getY();
	}
	
	public Vector2(Position pos1, Position pos2) {
		this.x = pos2.getX() - pos1.getX();
		this.y = pos2.getY() - pos1.getY();
	}
	
	// ------------------------------------------------------------
	// Math
	// ------------------------------------------------------------

	public double getAngleToXAxis() {
		Vector2 xAxis = new Vector2(1, 0);
		if (y > 0) {
			return (2 * Math.PI - (Math.acos(scalar(xAxis) / (abs() * xAxis.abs()))));
		} else {
			return Math.acos(scalar(xAxis) / (abs() * xAxis.abs()));
		}
	}

	public double getSmallAngleToXAxis() {
		Vector2 xAxis = new Vector2(1, 0);
		return Math.acos(scalar(xAxis) / (abs() * xAxis.abs()));
	}

	public Vector2 add(Vector2 a) {
		return new Vector2(this.x + a.x, this.y + a.y);
	}

	public Vector2 sub(Vector2 a) {
		return new Vector2(this.x - a.x, this.y - a.y);
	}

	public double scalar(Vector2 a) {
		return this.x * a.x + this.y * a.y;
	}

	public double abs() {
		return Math.sqrt((Math.pow(	this.x,
									2)
				+ Math.pow(	this.y,
							2)));
	}

	// ------------------------------------------------------------
	// Getters - Setters
	// ------------------------------------------------------------

	public Vector2 getUnitVector() { return new Vector2((x / abs()), (y / abs())); }

	public void setToUnitVector() {
		x = x / abs();
		y = y / abs();
	}

	public void setVector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setVector(Vector2 vector) {
		this.x = vector.getX();
		this.y = vector.getY();
	}
	public void setVector(double angle) {
		this.x = Math.cos(angle);
		this.y = Math.sin(angle);
	}

	public double getX() { return x; }

	public void setX(double x) { this.x = x; }

	public double getY() { return y; }

	public void setY(double y) { this.y = y; }

	public static Vector2 getRandom() {
		Random rnd = new Random();
		return new Vector2(rnd.nextInt(100) - 50, rnd.nextInt(100) - 50);
	}

	public static Vector2 getRandom(Vector2 vector) {
		Random rnd = new Random();
//		double x = rnd.nextInt(100)-50;
//		double y = rnd.nextInt(100)-50;
		double x = 0;
		double y = 0;
		
		if (vector.getX() < 0 && vector.getY() < 0) {
			x = rnd.nextInt(50)+1;
			y = rnd.nextInt(50)+1;
		} else if (vector.getX() < 0 && vector.getY() > 0) {
			x = rnd.nextInt(50)+1;
			y = -rnd.nextInt(50)+1;
		} else if (vector.getX() > 0 && vector.getY() < 0) {
			x = -rnd.nextInt(50)+1;
			y = rnd.nextInt(50)+1;
		} else if (vector.getX() > 0 && vector.getY() > 0) {
			x = -rnd.nextInt(50)+1;
			y = -rnd.nextInt(50)+1;
		}
		return new Vector2(x, y);
	}

	public Vector2 getNormalVecCW() {
		return new Vector2(this.y, -this.x);
	}

	public Vector2 getNormalVecCCW() {
		return new Vector2(-this.y, this.x);
	}
	
	public Vector2 getReverseVec() {
		return new Vector2(-this.x, -this.y); 
	}
	
	// ------------------------------------------------------------
	// Debug
	// ------------------------------------------------------------

	@Override
	public String toString() {
		return "VectorR2 [" + x + ", " + y + "]";
	}
}
