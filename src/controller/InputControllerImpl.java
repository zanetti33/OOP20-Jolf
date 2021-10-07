package controller;

import java.awt.Point;

import model.Vector2D;
import view.InputPanel;
import view.InputVisualizer;

public class InputControllerImpl implements InputController {

	private Vector2D shot;
	private final InputVisualizer visualizer;
	
	
	public InputControllerImpl(InputVisualizer visualizer) {
		this.visualizer = visualizer;
	}

	@Override
	public void update(Vector2D direction) {
		
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

}
