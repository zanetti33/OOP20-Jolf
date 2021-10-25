package model;

import java.awt.Color;
import java.awt.Graphics;

import util.Point2D;
import util.Vector2D;

/**
 * this is an edge constantly moving on the map
 * @author loren
 *
 */
public class MovingEdge extends MovingObject implements MapObject {

	private final static Color EDGE_COLOR = Color.BLACK;
	
	private Edge edge;
	
	/**
	 * @param p1
	 * @param p2
	 * @param speed
	 */
	public MovingEdge(Point2D p1, Point2D p2, Vector2D speed) {
		super(p1, speed);
		this.edge = new Edge(p1, p2);
	}

	@Override
	protected synchronized void updatePosition(final double timeElapsed) {
		Vector2D deltaX = this.speed.multiply(timeElapsed);
		this.position = deltaX.traslate(this.getPosition());
		this.edge = edge.translate(deltaX);
	}
	
	@Override
	public synchronized void setPosition(Point2D position) {
		this.position = position;
		this.edge = this.edge.translate(new Vector2D(this.position, position));
	}

	@Override
	public void applyConstraintTo(Ball ball) {
		this.edge.applyConstraintTo(ball);
	}

	@Override
	protected void updateSpeed(double timeElapsed) {
	}

	/**
	 * applies map constraints to this edge
	 */
	@Override
	protected void applyConstraints() {
		if (this.edge.getP1().getY() >= this.map.getSize().getHeight() ||
				this.edge.getP2().getY() >= this.map.getSize().getHeight() ||
				this.edge.getP1().getY() <= 0 ||
				this.edge.getP2().getY() <= 0) {
			this.speed = new Vector2D(this.getSpeed().getX(), - this.getSpeed().getY());
		}
		if (this.edge.getP1().getX() >= this.map.getSize().getWidth() ||
				this.edge.getP2().getX() >= this.map.getSize().getWidth() ||
				this.edge.getP1().getX() <= 0 ||
				this.edge.getP2().getX() <= 0) {
			this.speed = new Vector2D(- this.getSpeed().getX(), this.getSpeed().getY());
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(EDGE_COLOR);
		g.drawLine(this.edge.getP1().getIntX(),
				this.edge.getP1().getIntY(),
				this.edge.getP2().getIntX(),
				this.edge.getP2().getIntY());
	}

}
