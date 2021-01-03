package math;

/**
 * @author	Simon Grundner <br>
 *			3AHEL
 */

public class Vector2 {

	private double x;
	private double y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
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

	public double getAngleToYAxis() {
		Vector2 yAxis = new Vector2(0, 1);
		return Math.acos((scalar(yAxis) / (abs() * yAxis.abs())));
	}

	public Vector2 addVec(Vector2 a) {
		return new Vector2(this.x + a.x, this.y + a.y);
	}

	public Vector2 subVec(Vector2 a) {
		return new Vector2(this.x - a.x, this.y - a.y);
	}

	public Vector2 normalVecCW() {
		return new Vector2(this.y, -this.x);
	}

	public Vector2 normalVecCCW() {
		return new Vector2(-this.y, this.x);
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

	public double getX() { return x; }

	public void setX(double x) { this.x = x; }

	public double getY() { return y; }

	public void setY(double y) { this.y = y; }

	// ------------------------------------------------------------
	// Debug
	// ------------------------------------------------------------

	@Override
	public String toString() {
		return "VectorR2 [" + x + ", " + y + "]";
	}
}
