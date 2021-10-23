package view;

import java.awt.Color;
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
	private static final Color INDICATOR_COLOR = Color.RED;
	private static final double SQUARE_MINIMUM_SHOT_POWER = 100;
	
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
	public void paintComponent(Graphics g) {
		synchronized (this) {
			if (this.applicationPoint != null && validShot()) {
				Point directionTip = this.direction.traslate(this.applicationPoint);
				g.setColor(INDICATOR_COLOR);
				g.drawLine(Double.valueOf(this.applicationPoint.getX()).intValue(),
						Double.valueOf(this.applicationPoint.getY()).intValue(),
						Double.valueOf(directionTip.getX()).intValue(),
						Double.valueOf(directionTip.getY()).intValue());
			}
		}
	}

	public void updateShotIntent(Vector2D direction) {
		synchronized (this) {
			this.direction = direction.getOppositeVector();
		}
		repaint();
	}

	@Override
	public void enableShot(Point ballPosition) {
		this.mouseListener.setEnable(true);
		this.applicationPoint = ballPosition;
	}
	
	public void shoot() {
		if (validShot()) {
			this.myController.newShot(this.direction);
			this.mouseListener.setEnable(false);
		}
		this.applicationPoint = null;
		this.direction = null;
		repaint();
	}

	private boolean validShot() {
		if (this.direction == null) {
			return false;
		}
		return this.direction.getSquareModule() > SQUARE_MINIMUM_SHOT_POWER; 
	}
	
}
