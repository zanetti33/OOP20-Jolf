package model;

import java.awt.Graphics;

import util.Point2D;
import util.Vector2D;

public abstract class MovingObject extends Thread implements MapObject {

	public final static long UPDATE_RATE = 20l;
	public final static double TO_SECONDS = 0.001;
	
	private boolean stop;
	private long lastTimeUpdate;
	protected Point2D position;
	protected Vector2D speed;
	protected Map map;
	
	public MovingObject(Point2D startingPos) {
		this(startingPos, Vector2D.nullVector());
	}
	
	public MovingObject(Point2D startingPos, Vector2D startingSpeed) {
		this.position = startingPos;
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

	public void setMap(Map map) {
		this.map = map;
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
				this.updateSpeed(timeElapsed);
				this.updatePosition(timeElapsed);
				this.applyConstraints();
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
	
	protected synchronized void updatePosition(final double timeElapsed) {
		this.position = this.speed.multiply(timeElapsed).traslate(this.getPosition());
	}

	@Override
	public abstract void draw(Graphics g);
	
}
