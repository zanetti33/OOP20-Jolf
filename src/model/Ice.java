package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Ice extends Surface {
	
	private static final double ICE_FRICTION = 0.05;
	private static final Color ICE_COLOR = new Color(137, 210, 220);
	
	public Ice(final Point position, final int width, final int height) {
		super(position, width, height, ICE_FRICTION);
	}

	@Override
	public void draw(final Graphics g) {
		super.draw(g);
		g.setColor(ICE_COLOR);
	}

	@Override
	public void applyConstraintTo(final Ball ball) {
	}

}
