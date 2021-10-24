package controller;

import java.util.Optional;

import util.Vector2D;
import view.GameInput;
import view.GameOutput;

public interface Controller {

	void newShot(Vector2D shot);
	
	void setInput(GameInput inputView);
	
	void setOutput(GameOutput outputView);
	
	Optional<String> getPlayerName();
	
	String getMapName();

	void start();

	void forceStop();
	
}
