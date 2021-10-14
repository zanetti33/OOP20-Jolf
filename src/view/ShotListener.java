package view;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Vector2D;

public class ShotListener extends MouseAdapter implements ShotInput {

	private final ShotVisualizer visualizer; 
	private Point startingPoint = null;
	private Point currentPoint = null;
	private boolean enable = false;
	
	public ShotListener(ShotVisualizer visualizer) {
		this.visualizer = visualizer;
	}
	
	@Override
	public synchronized void mouseDragged(MouseEvent e) {
		if (this.enable) {
			this.currentPoint = e.getPoint();
			this.visualizer.updateShotIntent(new Vector2D(this.startingPoint, this.currentPoint));
		}
	}

	@Override
	public synchronized void mousePressed(MouseEvent e) {
		if (this.enable) {
			this.startingPoint = e.getPoint();
		}
	}

	@Override
	public synchronized void mouseReleased(MouseEvent e) {
		if (this.enable && this.startingPoint != null) {
			this.visualizer.shoot();
			this.startingPoint = null;
			this.currentPoint = null;
			this.enable = false;
		}
	}
	
	public synchronized void enable() {
		this.enable = true;
	}
}
