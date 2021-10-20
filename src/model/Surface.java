package model;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Surface implements MapObject {
	
	private final double friction;
	private final Point position;
	private final int width;
	private final int height;
	
	public Surface(final Point position, final int width, final int height, final double friction) {
		this.position = position;
		this.width = width;
		this.height = height;
		this.friction = 0.1;
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(this.position.x, this.position.y, this.width, this.height);
	}

	@Override
	public void applyConstraintTo(Ball ball) {
	}
	
	public final double getFriction() {
		return this.friction;
	}

}
