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
		this.position = startingPos;
		this.speed = Vector2D.nullVector();
		this.stop = false;
	}
	
	@Override
	public synchronized Point getPosition() {
		return position;
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
				Thread.sleep(UPDATE_RATE);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isStill() {
		return speed.equals(Vector2D.nullVector());
	}

	public void setSpeed(Vector2D newSpeed) {
		this.speed = newSpeed;
	}
	
	private synchronized void updateSpeed(final long timeElapsed) {
	}

	private synchronized void updatePosition(final long timeElapsed) {
		this.position.setLocation(
				this.position.getX() + this.speed.getX() * timeElapsed,
				this.position.getY() + this.speed.getY() * timeElapsed);
	}

	@Override
	public abstract void draw(Graphics g);
	
	@Override
	public abstract ObjectType getType();

}
