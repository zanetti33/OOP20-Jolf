package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Optional;

public class Ball extends MovingObject {

	private final static int RADIUS = 10;
	private final static int HALF_R = RADIUS / 2;
	private final static Color BALL_COLOR = new Color(67, 41, 31);
	private final static double DEFAULT_ACCELERATION = -0.1f;
	
	private Map map;
	private Optional<Double> acceleration = Optional.empty();
	
	public Ball(Point startingPos) {
		super(startingPos);
		this.map = null;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(BALL_COLOR);
		g.fillOval(this.getPosition().x - HALF_R, 
				this.getPosition().y - HALF_R, 
				RADIUS, 
				RADIUS);
	}

	//to implement if there are 2 or more balls in the same map simultaneously
	@Override
	public void applyConstraintTo(Ball ball) {
	}

	@Override
	protected synchronized void updateSpeed(long timeElapsed) {
	}

	private synchronized double getAcceleration() {
		return this.acceleration.orElse(DEFAULT_ACCELERATION);
	}
	
	public synchronized void setAcceleration(double acceleration) {
		this.acceleration = Optional.of(acceleration);
	}
	
	public synchronized void resetAcceleration() {
		this.acceleration = Optional.empty();
	}

	@Override
	protected synchronized void applyConstraints() {
		this.map.getObjects().stream()
		.filter(obj -> !obj.equals(this))
		.forEach(obj -> obj.applyConstraintTo(this));
		final Point position = this.getPosition();
		if (position.getX() + HALF_R > this.map.getSize().getWidth() ||
				position.getX() - HALF_R < 0) {
			this.setSpeed(new Vector2D(-this.getSpeed().getX(), this.getSpeed().getY()));
		}
		if (position.getY() + HALF_R > this.map.getSize().getHeight() ||
				position.getY() - HALF_R < 0) {
			this.setSpeed(new Vector2D(this.getSpeed().getX(), -this.getSpeed().getY()));
		}
	}

}
