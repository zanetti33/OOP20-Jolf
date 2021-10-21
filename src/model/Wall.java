package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Wall implements MapObject {
	
	private static final Color WALL_COLOR = new Color(87, 61, 28);
	private final Point position;
	private final int width;
	private final int height;
	
	public Wall(final Point position, final int width, final int height) {
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
		g.setColor(WALL_COLOR);
		g.fillRect(this.position.x, this.position.y, this.width, this.height);
	}

	@Override
	public void applyConstraintTo(final Ball ball) {
		// TODO Auto-generated method stub

	}

}
