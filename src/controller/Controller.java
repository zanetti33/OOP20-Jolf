package controller;

import util.Vector2D;
import view.GameInput;
import view.GameOutput;

public interface Controller {

	void newShot(Vector2D shot);
	
	void setInput(GameInput inputView);
	
	void setOutput(GameOutput outputView);
	
	String getPlayerName();

	void start();

	void forceStop();
	
}
