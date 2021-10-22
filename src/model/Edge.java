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
	
	public Angle getAngle() {
		return this.angle;
	}

	public double getSqDistance(double x, double y) {
		return this.line.ptLineDistSq(x, y);
	}
	
	public boolean isHit(Ball ball) {
		return Ball.RADIUS * Ball.RADIUS < 
				this.getSqDistance(ball.getPosition().getX(), ball.getPosition().getY());
	}
	
	public Angle resultAngle(Angle directionAngle) {
		return Angle.ofRadians(this.angle.getRadians() * 2 - directionAngle.getRadians());
	}
	
}
