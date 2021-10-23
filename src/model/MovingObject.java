package model;

import java.awt.Graphics;
import java.awt.Point;

public abstract class MovingObject extends Thread implements MapObject {

	public final static long UPDATE_RATE = 20l;
	public final static double TO_SECONDS = 0.001;
	
	private boolean stop;
	protected Point2D position;
	protected Vector2D speed;
	private long lastTimeUpdate;
	
	public MovingObject(Point startingPos) {
		this(startingPos, Vector2D.nullVector());
	}
	
	public MovingObject(Point startingPos, Vector2D startingSpeed) {
		this.position = new Point2D(startingPos.getX(), startingPos.getY());
		this.speed = startingSpeed;
		this.stop = false;
	}
	
	@Override
	public synchronized Point2D getPosition() {
		return this.position;
	}
	
	public synchronized void setPosition(Point2D position) {
		this.position = position;
	}
	
	@Override
	public void run() {
		try {
			this.lastTimeUpdate = System.currentTimeMillis();
			while(!stop) {
				long time = System.currentTimeMillis();
				long millsElapsed = time - this.lastTimeUpdate;
				this.lastTimeUpdate = time;
				double timeElapsed = Long.valueOf(millsElapsed).doubleValue() * TO_SECONDS;
				updateSpeed(timeElapsed);
				updatePosition(timeElapsed);
				applyConstraints();
				Thread.sleep(UPDATE_RATE);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void forceStop() {
		this.stop = true;
	}
	
	protected abstract void updateSpeed(double timeElapsed);

	protected abstract void applyConstraints();

	public synchronized boolean isMoving() {
		return !speed.equals(Vector2D.nullVector());
	}
	
	public synchronized Vector2D getSpeed() {
		return this.speed;
	}
	
	private synchronized void updatePosition(final double timeElapsed) {
		this.position = this.speed.multiply(timeElapsed).traslate(this.getPosition());
	}

	@Override
	public abstract void draw(Graphics g);
	
}
