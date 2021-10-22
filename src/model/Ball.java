package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Ball extends MovingObject {

	public final static int RADIUS = 12;
	
	private final static int HALF_R = RADIUS / 2;
	private final static double DEFAULT_ACCELERATION = 15f;
	private final static Color BALL_COLOR = new Color(0, 255, 255);
	
	private Map map;
	private double acceleration;
	
	public Ball(Point startingPos) {
		super(startingPos);
		this.map = null;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public synchronized void setSpeed(Vector2D newSpeed) {
		this.speed = newSpeed;
		this.acceleration = DEFAULT_ACCELERATION;
	}
	
	public synchronized void setAcceleration(double newAcc) {
		this.acceleration = newAcc;
	}
	
	public synchronized double getAcceleration() {
		return this.acceleration;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(BALL_COLOR);
		g.fillOval(this.getPosition().getIntX() - HALF_R, 
				this.getPosition().getIntY() - HALF_R, 
				RADIUS, 
				RADIUS);
	}

	@Override
	protected synchronized void updateSpeed(double timeElapsed) {
		double deltaV = this.getAcceleration() * timeElapsed;
		if (this.getSpeed().getSquareModule() <= deltaV * deltaV) {
			this.speed = Vector2D.nullVector();
			this.acceleration = 0f;
		} else {
			this.speed = new Vector2D(this.getSpeed().getAngle(), getSpeed().getModule() - deltaV);
			this.acceleration = DEFAULT_ACCELERATION;
		}
	}

	@Override
	protected synchronized void applyConstraints() {
		this.map.getObjects().stream()
		.filter(obj -> !obj.equals(this))
		.forEach(obj -> obj.applyConstraintTo(this));
		if (this.getPosition().getY() + RADIUS >= this.map.getSize().getHeight()) {
			this.setPosition(new Point2D(this.getPosition().getX(), this.map.getSize().getHeight() - RADIUS));
			this.speed = new Vector2D(this.getSpeed().getX(), - this.getSpeed().getY());
		}
		if (this.getPosition().getY() - RADIUS <= 0) {
			this.setPosition(new Point2D(this.getPosition().getX(), RADIUS));
			this.speed = new Vector2D(this.getSpeed().getX(), - this.getSpeed().getY());
		}
		if (this.getPosition().getX() + RADIUS >= this.map.getSize().getWidth()) {
			this.setPosition(new Point2D(this.map.getSize().getWidth() - RADIUS, this.getPosition().getY()));
			this.speed = new Vector2D(- this.getSpeed().getX(), this.getSpeed().getY());
		}
		if (this.getPosition().getX() - RADIUS <= 0) {
			this.setPosition(new Point2D(RADIUS, this.getPosition().getY()));
			this.speed = new Vector2D(- this.getSpeed().getX(), this.getSpeed().getY());
		}
	}

	//to implement if there are 2 or more balls in the same map simultaneously
	@Override
	public void applyConstraintTo(Ball ball) {
	}

}
