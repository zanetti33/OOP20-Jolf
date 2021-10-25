package controller;

import java.util.Optional;

import util.Vector2D;
import view.GameInput;
import view.GameOutput;

/**
 * Simulates a match of Jolf, full course or single map
 * @author loren
 *
 */
public interface Controller {

	/**
	 * Generate a new shot that make the ball move
	 * @param shot
	 */
	void newShot(Vector2D shot);
	
	/**
	 * Sets the controller input
	 * @param inputView
	 */
	void setInput(GameInput inputView);
	
	/**
	 * Sets the controller output
	 * @param outputView
	 */
	void setOutput(GameOutput outputView);
	
	/**
	 * @return the player name if there is one
	 */
	Optional<String> getPlayerName();
	
	/**
	 * @return the map name
	 */
	String getMapName();

	/**
	 * Starts the controller
	 */
	void start();

	/**
	 * Force the controller to stop
	 */
	void forceStop();
	
}
