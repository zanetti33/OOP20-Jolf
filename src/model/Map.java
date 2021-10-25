package model;

import java.awt.Dimension;
import java.util.List;

/**
 * A generic map in the Jolf game
 * @author loren
 *
 */
public interface Map {

	/**
	 * @return the map name
	 */
	String getName();
	
	/**
	 * @return the map size
	 */
	Dimension getSize();
	
	/**
	 * @return the list of objects in this map
	 */
	List<MapObject> getObjects();

	/**
	 * @return the list of moving objects in this map
	 */
	List<MovingObject> getMovingObjects();
	
	/**
	 * @return the list of stars in the map
	 */
	List<Star> getStars();
	
	/** 
	 * @return the ball object in the map
	 */
	Ball getBall();
	
}
