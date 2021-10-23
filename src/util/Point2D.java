package util;

import java.awt.Point;

public class Point2D {

	private double x;
	private double y;
	
	public static Point2D nullPoint() {
		return new Point2D(0, 0);
	}
	
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public int getIntX() {
		return Double.valueOf(this.x).intValue();
	}
	
	public int getIntY() {
		return Double.valueOf(this.y).intValue();
	}
	
	public Point2D traslate(Vector2D vector) {
		return new Point2D(this.x + vector.getX(), this.y + vector.getY());
	}
	
	@Override
	public String toString() {
		return "Point2D [x=" + x + ", y=" + y + "]";
	}

	public void setLocation(Point2D newPoint) {
		this.x = newPoint.getX();
		this.y = newPoint.getY();
	}
	
	public Point toPoint() {
		return new Point(Double.valueOf(this.x).intValue(),
				Double.valueOf(this.y).intValue());
	}

}
