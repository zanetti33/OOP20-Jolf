package model;

import java.awt.Graphics;
import java.awt.Point;

public class Ball extends MovingObject {

	private final static int RADIUS = 10;
	
	public Ball(Point startingPos) {
		super(startingPos);
	}

	@Override
	public void draw(Graphics g) {
		g.drawOval(this.getPosition().x, this.getPosition().y, RADIUS, RADIUS);
	}

	@Override
	public ObjectType getType() {
		return ObjectType.MOVING;
	}

}
