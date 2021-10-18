package model;

import java.awt.Graphics;
import java.awt.Point;

public class Ball extends MovingObject {

	private final static int RADIUS = 10;
	private final static float DEFAULT_FRICTION = 0.1f;
	
	private Map map;
	
	public Ball(Point startingPos) {
		super(startingPos);
		this.map = null;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawOval(this.getPosition().x, this.getPosition().y, RADIUS, RADIUS);
	}

	//to implement if there are 2 or more balls in the same map simultaneously
	@Override
	public void applyConstraintTo(Ball ball) {
	}

	@Override
	protected void updateSpeed(long timeElapsed) {
		getSpeed().multiply(DEFAULT_FRICTION * Math.pow(timeElapsed, 2));
	}

	@Override
	protected void applyConstraints() {
		this.map.getObjects().stream()
		.filter(obj -> !obj.equals(this))
		.forEach(obj -> obj.applyConstraintTo(this));
	}

}
