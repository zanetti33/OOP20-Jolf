package model;

import java.awt.geom.Line2D;

import util.Angle;
import util.Point2D;
import util.Vector2D;

/**
 * A generic physical edge inside a Jolf map
 * @author loren
 *
 */
public class Edge {
	
	private Line2D line;
	private Angle angle;
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Edge(double x1, double y1, double x2, double y2) {
		this.angle = Angle.ofLine(x1, y1, x2, y2);
		this.line = new Line2D.Double(x1, y1, x2, y2);
	}
	
	/**
	 * @param p1
	 * @param p2
	 */
	public Edge(Point2D p1, Point2D p2) {
		this(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
	/**
	 * @return the first point of the edge
	 */
	public Point2D getP1() {
		return new Point2D(this.line.getP1().getX(),
				this.line.getP1().getY());
	}
	
	/**
	 * @return the second point of the edge
	 */
	public Point2D getP2() {
		return new Point2D(this.line.getP2().getX(),
				this.line.getP2().getY());
	}
	
	@Override
	public String toString() {
		return "Edge [p1=" + this.getP1() + ", p2=" + this.getP2() + "]";
	}

	/**
	 * @return the edge's angle with the x axis
	 */
	public Angle getAngle() {
		return this.angle;
	}
	
	/**
	 * @param ball
	 * @return true if the ball hits the edge
	 */
	public boolean isHit(Ball ball) {
		return Ball.RADIUS * Ball.RADIUS >=
				this.line.ptSegDistSq(ball.getPosition().getX(), ball.getPosition().getY());
	}
	
	/**
	 * @param directionAngle which has the ball
	 * @return the resulting angle which the ball directs to after the collision
	 */
	public Angle resultAngle(Angle directionAngle) {
		return Angle.ofRadians(this.angle.getRadians() * 2 - directionAngle.getRadians());
	}
	
	public void applyConstraintTo(final Ball ball) {
		if (this.isHit(ball)) {
			ball.setSpeed(new Vector2D(this.resultAngle(ball.getSpeed().getAngle()),
					ball.getSpeed().getModule()));
			ball.setPosition(ball.getSpeed().multiply(MovingObject.UPDATE_RATE * MovingObject.TO_SECONDS).traslate(ball.getPosition()));
		}
	}

	/**
	 * @param vector
	 * @return the resulting edge translated by the vector
	 */
	public Edge translate(Vector2D vector) {
		return new Edge(vector.traslate(this.getP1()),
				vector.traslate(this.getP2()));
	}
	
}
