package model;

import java.awt.Graphics;

public abstract class Surface implements MapObject {
	
	private final double friction;
	private final Point2D position;
	private final int width;
	private final int height;
	
	public Surface(final Point2D position, final int width, final int height, final double friction) {
		this.position = position;
		this.width = width;
		this.height = height;
		this.friction = friction;
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
	}
	
	public final double getFriction() {
		return this.friction;
	}

}
