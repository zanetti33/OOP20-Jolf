package view;

import java.awt.Dimension;
import java.util.List;

import model.MapObject;

public interface MapOutput {

	void updateObjectsPosition(List<MapObject> objects);
	
	void updateShotCount(int shots);
	
	void mapFinished();
	
	void setSize(Dimension size);
	
}
