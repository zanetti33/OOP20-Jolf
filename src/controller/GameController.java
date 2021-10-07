package controller;

import java.util.List;

import model.MapObject;
import model.Vector2D;

public interface GameController {

	void newShot(Vector2D shot);
	
	int getTotalShots();
	
	int getShots();
	
	List<MapObject> getMapObjects();
	
}
