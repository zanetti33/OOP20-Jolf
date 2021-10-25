package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Lets an interface get some useful information
 * @author loren
 *
 */
public interface Navigator {
	
	/**
	 * @return A map containing pairs player-score
	 * @throws IOException
	 */
	Map<String,Integer> getLeaderboard() throws IOException;
	
	/**
	 * Writes a new entry on the leaderboard
	 * @param player
	 * @param score
	 * @throws IOException
	 */
	void writeOnLeaderboard(String player, int score) throws IOException;
	
	/**
	 * Resets the leaderboard
	 * @throws IOException
	 */
	void resetLeaderboard() throws IOException;
	
	/**
	 * Change the color of the Ball in the game
	 * @param color
	 */
	void changeBallColor(String color);
	
	/**
	 * @return the star image
	 */
	String getStarImage();
	
	/**
	 * @return a list of the maps in the game
	 */
	List<String> getMaps();
	
	/**
	 * @returna a list of the courses in the game
	 */
	List<String> getCourses();

}
