package view;

import java.awt.Point;

/**
 * an interface that lets the user shoot the ball
 * @author loren
 *
 */
public interface GameInput {

	/**
	 * enables the user to shoot
	 * @param ballPosition
	 */
	void enableShot(Point ballPosition);
	
}
