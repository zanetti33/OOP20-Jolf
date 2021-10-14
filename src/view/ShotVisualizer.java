package view;

import model.Vector2D;

public interface ShotVisualizer {

	void updateShotIntent(Vector2D direction);
	
	void shoot();
	
}