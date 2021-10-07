package view;

import java.util.List;

import model.MapObject;

public interface GameOutput {

	void updateObjectsPosition(List<MapObject> objects);
	
	void gameFinished();
	
	void mapFinished();
	
}
