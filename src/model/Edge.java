package model;

import java.awt.geom.Line2D;

public class Edge {
	
	private Line2D line;
	private Angle angle;
	
	public Edge(double x1, double y1, double x2, double y2) {
		this.angle = Angle.ofLine(x1, y1, x2, y2);
		this.line = new Line2D.Double(x1, y1, x2, y2);
	}
	
	public Edge(Point2D p1, Point2D p2) {
		this(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
	public Point2D getP1() {
		return new Point2D(this.line.getP1().getX(),
				this.line.getP1().getY());
	}
	
	public Point2D getP2() {
		return new Point2D(this.line.getP2().getX(),
				this.line.getP2().getY());
	}
	
	@Override
	public String toString() {
		return "Edge [p1=" + this.getP1() + ", p2=" + this.getP2() + "]";
	}

	public Angle getAngle() {
		return this.angle;
	}
	
	public boolean isHit(Ball ball) {
		return Ball.RADIUS * Ball.RADIUS >=
				this.line.ptSegDistSq(ball.getPosition().getX(), ball.getPosition().getY());
	}
	
	public Angle resultAngle(Angle directionAngle) {
		return Angle.ofRadians(this.angle.getRadians() * 2 - directionAngle.getRadians());
	}
	
}
