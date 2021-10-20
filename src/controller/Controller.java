package controller;

import model.Vector2D;
import view.GameInput;
import view.GameOutput;

public interface Controller {

	void newShot(Vector2D shot);
	
	void setInput(GameInput inputView);
	
	void setOutput(GameOutput outputView);

	void start();

	void forceStop();
	
}
