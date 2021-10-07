package model;

import java.awt.Graphics;
import java.awt.Point;

public interface MapObject {

	Point getPosition();
	
	void draw(Graphics g);
	
}
