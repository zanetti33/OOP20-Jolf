package controller;


import model.Course;
import model.Map;
import model.Vector2D;
import view.GameInput;
import view.GameOutput;

public class ControllerImpl implements Controller {

	private GameOutput myOutput;
	private GameInput myInput;
	
	public ControllerImpl(String playerName, Course course) {
		for(Map m : course.getMaps()) {
			playMap(m);
		}
		this.myOutput.gameFinished();
	}
	
	private void playMap(Map m) {
		// TODO Auto-generated method stub
		
	}

	public ControllerImpl(Map map) {
		playMap(map);
	}
	
	@Override
	public void newShot(Vector2D shot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setInput(GameInput inputView) {
		this.myInput = inputView;
	}

	@Override
	public void setOutput(GameOutput outputView) {
		this.myOutput = outputView;
	}

}
