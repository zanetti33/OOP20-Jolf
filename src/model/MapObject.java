package model;

import java.awt.Graphics;

public interface MapObject {
	
	Point2D getPosition();
	
	void draw(Graphics g);
	
	void applyConstraintTo(Ball ball);
	
}