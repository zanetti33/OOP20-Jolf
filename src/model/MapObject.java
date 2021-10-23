package model;

import java.awt.Graphics;

import util.Point2D;

public interface MapObject {
	
	Point2D getPosition();
	
	void draw(Graphics g);
	
	void applyConstraintTo(Ball ball);
	
}