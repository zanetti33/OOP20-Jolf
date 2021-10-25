package model;

import java.awt.Graphics;

import util.Point2D;
import util.Vector2D;

/**
 * Implements a moving object that updates is position each UPDATE_RATE milliseconds
 * @author loren
 *
 */
public abstract class MovingObject extends Thread implements MapObject {

	public final static long UPDATE_RATE = 20l;
	public final static double TO_SECONDS = 0.001;
	
	private boolean stop;
	private long lastTimeUpdate;
	protected Point2D position;
	protected Vector2D speed;
	protected Map map;
	
	/**
	 * @param startingPos
	 */
	public MovingObject(Point2D startingPos) {
		this(startingPos, Vector2D.nullVector());
	}
	
	/**
	 * @param startingPos
	 * @param startingSpeed
	 */
	public MovingObject(Point2D startingPos, Vector2D startingSpeed) {
		this.position = startingPos;
		this.speed = startingSpeed;
		this.stop = false;
	}
	
	/**
	 * @return this object current position
	 */
	@Override
	public synchronized Point2D getPosition() {
		return this.position;
	}
	
	/**
	 * set the objects position
	 * @param position
	 */
	public synchronized void setPosition(Point2D position) {
		this.position = position;
	}

	/**
	 * set the map in which the object is located
	 * @param map
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	
	/**
	 * is the default routine
	 */
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
	
	/**
	 * forces it to stop
	 */
	public void forceStop() {
		this.stop = true;
	}
	
	/**
	 * updates the object speed after timeElapsed milliseconds are passed
	 * @param timeElapsed
	 */
	protected abstract void updateSpeed(double timeElapsed);

	/**
	 * applies constraints to this object
	 */
	protected abstract void applyConstraints();

	/**
	 * @return if the object is moving
	 */
	public synchronized boolean isMoving() {
		return !speed.equals(Vector2D.nullVector());
	}
	
	/**
	 * @return this object current speed
	 */
	public synchronized Vector2D getSpeed() {
		return this.speed;
	}
	
	/**
	 * updates the object position after timeElapsed milliseconds are passed
	 * @param timeElapsed
	 */
	protected synchronized void updatePosition(final double timeElapsed) {
		this.position = this.speed.multiply(timeElapsed).traslate(this.getPosition());
	}

	/**
	 *draw the object in a graphics object g
	 */
	@Override
	public abstract void draw(Graphics g);
	
}
