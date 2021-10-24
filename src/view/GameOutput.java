package view;

import java.awt.Dimension;
import java.util.List;

import model.MapObject;
import model.Star;

public interface GameOutput {
	
	void updateTotalShotsCount(int totalShots);
	
	void updateShotCount(int shots);
	
	void gameFinished(int totalShots);

	void updateObjectsPosition(List<MapObject> objects, List<Star> stars);

	void setSize(Dimension size);

}
