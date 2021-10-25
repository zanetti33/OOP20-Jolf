package model;

import java.awt.Graphics;

import util.Point2D;

/**
 * this is a generic object in a Jolf map
 * @author loren
 *
 */
public interface MapObject {
	
	/**
	 * @return the object position
	 */
	Point2D getPosition();
	
	/**
	 * draws the object in the graphics g
	 * @param g
	 */
	void draw(Graphics g);
	
	/**
	 * applies this object constraints to the ball
	 * @param ball
	 */
	void applyConstraintTo(Ball ball);
	
}