package model;

import java.awt.Point;

public class Vector2D {

	private double x;
	private double y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Point p1, Point p2) {
		this(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
	public Vector2D(double x1, double y1, double x2, double y2) {
		this.x = x1 - x2;
		this.y = y1 - y2;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public Vector2D getOppositeVector() {
		return new Vector2D(-this.getX(), -this.getY());
	}
	
	public double getModule() {
		return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
	}
	
	public Angle getAngle() {
		return Angle.ofRadians(Math.acos(this.getX() / this.getModule()));
	}
	
	public Point traslate(Point p) {
		return new Point(Double.valueOf(this.getX() + p.getX()).intValue(),
				Double.valueOf(this.getY() + p.getY()).intValue());
	}
	
	@Override
	public String toString() {
		return "Vector2D [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2D other = (Vector2D) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
}
