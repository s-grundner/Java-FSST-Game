package math;

/**
 * @author	Simon Grundner
 * 			3AHEL
 */

public class VectorR2 {

	private double x;
	private double y;

	public VectorR2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// ------------------------------------------------------------
	// Math
	// ------------------------------------------------------------

	public double getAngleToXAxis() {
		VectorR2 xAxis = new VectorR2(1, 0);
		return Math.acos(scalar(xAxis) / (abs() * xAxis.abs()));
	}

	public double getAngleToYAxis() {
		VectorR2 yAxis = new VectorR2(0, 1);
		return Math.acos((scalar(yAxis) / (abs() * yAxis.abs())));
	}

	public VectorR2 addVec(VectorR2 a) {
		return new VectorR2(this.x + a.x, this.y + a.y);
	}

	public VectorR2 subVec(VectorR2 a) {
		return new VectorR2(this.x - a.x, this.y - a.y);
	}

	public VectorR2 normalVecCW() {
		return new VectorR2(this.y, -this.x);
	}

	public VectorR2 normalVecCCW() {
		return new VectorR2(-this.y, this.x);
	}

	public double scalar(VectorR2 a) {
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

	public VectorR2 getUnitVector() { return new VectorR2((x / abs()), (y / abs())); }

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
