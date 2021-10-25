package view;

import util.Vector2D;

/**
 * an interface used for the game input
 * @author loren
 *
 */
public interface ShotVisualizer {

	/**
	 * updates the indicator that shows the direction and power of a shot
	 * @param direction
	 */
	void updateShotIntent(Vector2D direction);
	
	/**
	 *  manages a new shot by the user
	 */
	void shoot();
	
}