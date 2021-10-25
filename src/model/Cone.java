package model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

import util.Point2D;
import util.Vector2D;

public class Cone implements MapObject {
	
	private final Point2D point1;
	private final Point2D point2;
	private final Point2D point3;
	private static final Color CONE_COLOR = new Color(255, 115, 0);
	private final Set<Edge> edges;
	
	public Cone(final Point2D point1, final Point2D point2, final Point2D point3) {
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
		edges = Set.of(new Edge(this.point1, this.point2), 
				new Edge(this.point1, this.point3),
				new Edge(this.point2, this.point3));
	}

	@Override
	public Point2D getPosition() {
		return this.point1;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(CONE_COLOR);
		int x[] = {this.point1.getIntX(), this.point2.getIntX(), this.point3.getIntX()};
		int y[] = {this.point1.getIntY(),  this.point2.getIntY(), this.point3.getIntY()};
		g.fillPolygon(x, y, this.edges.size());
		g.setColor(Color.WHITE);
		edges.forEach(edge -> g.drawLine(edge.getP1().getIntX(), 
				edge.getP1().getIntY(), 
				edge.getP2().getIntX(), 
				edge.getP2().getIntY()));
	}

	@Override
	public void applyConstraintTo(Ball ball) {
		edges.stream()
		.filter(edge -> edge.isHit(ball))
		.forEach(edge -> {
		ball.setSpeed(new Vector2D(edge.resultAngle(ball.getSpeed().getAngle()),
				ball.getSpeed().getModule()));
		});
	}

}
