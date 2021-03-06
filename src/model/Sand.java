package model;

import java.awt.Color;
import java.awt.Graphics;

import util.Point2D;

public class Sand extends Surface {
	
	private static final double SAND_FRICTION = 50f;
	private static final Color SAND_COLOR = new Color(241, 232, 184);
	
	public Sand(final Point2D position, final int width, final int height) {
		super(position, width, height, SAND_FRICTION);
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(SAND_COLOR);
		super.draw(g);
	}

}
