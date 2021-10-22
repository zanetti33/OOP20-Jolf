package model;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Surface implements MapObject {
	
	private final double friction;
	private final Point2D position;
	private final int width;
	private final int height;
	private final Rectangle2D hitbox;
	
	public Surface(final Point2D position, final int width, final int height, final double friction) {
		this.position = position;
		this.width = width;
		this.height = height;
		this.friction = friction;
		this.hitbox = new Rectangle2D.Double(position.getX(), position.getY(), width, height);
	}

	@Override
	public Point2D getPosition() {
		return this.position;
	}

	@Override
	public void draw(Graphics g) {
		g.fillRect(this.position.getIntX(), this.position.getIntY(), this.width, this.height);
	}

	@Override
	public void applyConstraintTo(Ball ball) {
		if (this.hitbox.contains(ball.getPosition())) {
			
		}
	}
	
	public final double getFriction() {
		return this.friction;
	}

}
