package view;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

import controller.Controller;
import model.Vector2D;

public class InputPanel extends JPanel implements GameInput, ShotVisualizer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Controller myController;
	private final ShotListener mouseListener = new ShotListener(this);
	
	private Point applicationPoint;
	private Vector2D direction;
	
	public InputPanel(Controller c) {
		super();
		this.myController = c;
		this.addMouseListener(this.mouseListener);
		this.addMouseMotionListener(this.mouseListener);
	}
	
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		synchronized (this) {
			if (this.applicationPoint != null && this.direction != null) {
				Point directionTip = this.direction.traslate(this.applicationPoint);
				g.drawLine(Double.valueOf(this.applicationPoint.getX()).intValue(),
						Double.valueOf(this.applicationPoint.getY()).intValue(),
						Double.valueOf(directionTip.getX()).intValue(),
						Double.valueOf(directionTip.getY()).intValue());
			}
		}
	}

	public void updateShotIntent(Vector2D direction) {
		synchronized (this) {
			this.direction = direction;
		}
		repaint();
	}

	@Override
	public void enableShot(Point ballPosition) {
		this.mouseListener.enable();
		this.applicationPoint = ballPosition;
	}
	
	public void shoot() {
		this.myController.newShot(this.direction);
	}
	
}
