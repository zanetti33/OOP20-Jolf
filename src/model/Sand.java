package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Sand extends Surface {
	
	private static final double SAND_FRICTION = 0.7;
	private static final Color SAND_COLOR = new Color(241, 232, 184);
	
	public Sand(final Point position, final int width, final int height) {
		super(position, width, height, SAND_FRICTION);
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(SAND_COLOR);
		super.draw(g);
	}

	@Override
	public void applyConstraintTo(final Ball ball) {
	}

}
