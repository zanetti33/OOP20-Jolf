package view;

import java.awt.Dimension;
import java.util.List;

import model.MapObject;
import model.Star;

/**
 * an interface that the displays a game of Jolf
 * @author loren
 *
 */
public interface GameOutput {
	
	/**
	 * update the shots count of the full course
	 * @param totalShots
	 */
	void updateTotalShotsCount(int totalShots);
	
	/**
	 * update the shots count of this specific map
	 * @param shots
	 */
	void updateShotCount(int shots);
	
	/**
	 * lets the user know that the game is finished
	 * @param totalShots
	 */
	void gameFinished(int totalShots);

	/**
	 * updates the objects positions
	 * @param objects
	 * @param stars
	 */
	void updateObjectsPosition(List<MapObject> objects, List<Star> stars);

	/**
	 * sets the size of the display for the map
	 * @param size
	 */
	void setSize(Dimension size);

}
