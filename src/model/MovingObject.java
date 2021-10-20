package model;

import java.awt.Graphics;
import java.awt.Point;

public abstract class MovingObject extends Thread implements MapObject {

	private final static long UPDATE_RATE = 20l;
	private boolean stop;
	private Point position;
	private Vector2D speed;
	private long lastTimeUpdate;
	
	public MovingObject(Point startingPos) {
		this(startingPos, Vector2D.nullVector());
	}
	
	public MovingObject(Point startingPos, Vector2D startingSpeed) {
		this.position = startingPos;
		this.speed = startingSpeed;
		this.stop = false;
	}
	
	@Override
	public synchronized Point getPosition() {
		return this.position;
	}
	
	@Override
	public void run() {
		try {
			this.lastTimeUpdate = System.currentTimeMillis();
			while(!stop) {
				long time = System.currentTimeMillis();
				long timeElapsed = time - this.lastTimeUpdate;
				this.lastTimeUpdate = time;
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
	
	protected abstract void updateSpeed(long timeElapsed);

	protected abstract void applyConstraints();

	public synchronized boolean isMoving() {
		return !speed.equals(Vector2D.nullVector());
	}

	public synchronized void setSpeed(Vector2D newSpeed) {
		this.speed = newSpeed;
	}
	
	public synchronized Vector2D getSpeed() {
		return this.speed;
	}

	private synchronized void updatePosition(final long timeElapsed) {
		this.position.setLocation(
				this.position.getX() + this.speed.getX() * timeElapsed,
				this.position.getY() + this.speed.getY() * timeElapsed);
	}

	@Override
	public abstract void draw(Graphics g);
	
}
