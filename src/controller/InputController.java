package controller;

import java.awt.Point;

import model.Vector2D;

public interface InputController {
	
	void shoot();
	
	void update(Vector2D direction);
	
}
