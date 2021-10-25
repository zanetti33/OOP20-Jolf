package util;

import java.awt.Point;

/**
 * a generic point 2-Dimensional
 * @author loren
 *
 */
public class Point2D {

	private double x;
	private double y;
	
	/**
	 * @return the point (0,0)
	 */
	public static Point2D nullPoint() {
		return new Point2D(0, 0);
	}
	
	/**
	 * @param x
	 * @param y
	 */
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @param x
	 * @param y
	 */
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the point x
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * @return the point y
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * @return the integer x of the point
	 */
	public int getIntX() {
		return Double.valueOf(this.x).intValue();
	}
	
	/**
	 * @return the integer y of the point
	 */
	public int getIntY() {
		return Double.valueOf(this.y).intValue();
	}
	
	/**
	 * @param vector
	 * @return this point translated by the given vector
	 */
	public Point2D traslate(Vector2D vector) {
		return new Point2D(this.x + vector.getX(), this.y + vector.getY());
	}
	
	@Override
	public String toString() {
		return "Point2D [x=" + x + ", y=" + y + "]";
	}

	/**
	 * @param newPoint
	 */
	public void setLocation(Point2D newPoint) {
		this.x = newPoint.getX();
		this.y = newPoint.getY();
	}
	
	/**
	 * @return an instance of the class Point of this point
	 */
	public Point toPoint() {
		return new Point(Double.valueOf(this.x).intValue(),
				Double.valueOf(this.y).intValue());
	}

}
