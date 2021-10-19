package model;

import java.awt.Graphics;
import java.awt.Point;

public class Sand implements Surface {
	
	private static final double SAND_FRICTION = 0.7;
	private final Point position;
	private final int width;
	private final int height;
	
	public Sand(final Point position, final int width, final int height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void draw(final Graphics g) {
		g.drawRect(this.position.x, this.position.y, this.width, this.height);
	}

	@Override
	public void applyConstraintTo(final Ball ball) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getFriction() {
		return SAND_FRICTION;
	}

}
