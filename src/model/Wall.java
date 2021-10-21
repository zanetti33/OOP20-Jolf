package model;

import java.awt.Color;
import java.awt.Graphics;

public class Wall implements MapObject {
	
	private final Point2D position;
	private static final Color WALL_COLOR = new Color(87, 61, 28);
	private final int width;
	private final int height;
	
	public Wall(final Point2D position, final int width, final int height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}

	@Override
	public Point2D getPosition() {
		return this.position;
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(WALL_COLOR);
		g.fillRect(this.position.getIntX(), this.position.getIntY(), this.width, this.height);
	}

	@Override
	public void applyConstraintTo(final Ball ball) {
		// TODO Auto-generated method stub

	}

}
