package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Ball extends MovingObject {

	private final static int RADIUS = 10;
	private final static int HALF_R = RADIUS / 2;
	private final static double DEFAULT_ACCELERATION = 0.0000000000005f;
	private final static double MINIMUM_SPEED = 0.5f;
	private final static Color BALL_COLOR = new Color(67, 41, 31);
	
	private Map map;
	private Vector2D acceleration;
	
	public Ball(Point startingPos) {
		super(startingPos);
		this.map = null;
		this.acceleration = new Vector2D(this.getSpeed().getAngle().inverse(), DEFAULT_ACCELERATION);
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
		if (this.getSpeed().getModule() <= MINIMUM_SPEED) {
			this.setSpeed(Vector2D.nullVector());
		} else {
			this.setSpeed(this.getSpeed().sum(this.getAcceleration().multiply(timeElapsed)));
		}
	}
	
	public synchronized void setAcceleration(Vector2D newAcc) {
		this.acceleration = newAcc;
	}
	
	public synchronized Vector2D getAcceleration() {
		return this.acceleration;
	}
	
	public synchronized void resetAcceleration() {
		this.acceleration = new Vector2D(this.getSpeed().getAngle().inverse(), DEFAULT_ACCELERATION);
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
