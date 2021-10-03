package controller;

import java.awt.Point;

import model.Vector2D;

public interface InputController {

	public Point getBallPosition();
	
	public void newShot(Vector2D direction);
	
}
