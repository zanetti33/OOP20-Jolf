package model;

import java.awt.Point;

public class Vector2D {

	private double x;
	private double y;
	
	public static Vector2D nullVector() {
		return new Vector2D(0, 0);
	}
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Point p1, Point p2) {
		this(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
	public Vector2D(double x1, double y1, double x2, double y2) {
		this.x = x2 - x1;
		this.y = y2 - y1;
	}
	
	public Vector2D(Angle angle, double module) {
		this.x = Math.cos(angle.getRadians()) * module;
		this.y = Math.sin(angle.getRadians()) * module;
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
		return Math.sqrt(this.getSquareModule());
	}
	
	public double getSquareModule() {
		return this.getX() * this.getX() + this.getY() * this.getY();
	}
	
	public Angle getAngle() {
		return Angle.ofRadians(Math.atan2(this.getY(), this.getX()));
	}
	
	public Point traslate(Point p) {
		return new Point(Double.valueOf(this.getX() + p.getX()).intValue(),
				Double.valueOf(this.getY() + p.getY()).intValue());
	}

	public Point2D traslate(Point2D p) {
		return new Point2D(this.getX() + p.getX(),
				this.getY() + p.getY());
	}
	
	public Vector2D sum(final Vector2D vector) {
		return new Vector2D(this.x + vector.getX(), this.y + vector.getY());
	}
	
	public Vector2D multiply(double a) {
		return new Vector2D(this.x * a, this.y * a);
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Vector2D other = (Vector2D) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
			return false;
		}
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
			return false;
		}
		return true;
	}
	
}
