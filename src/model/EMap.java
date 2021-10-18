package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

public enum EMap implements Map {

	STARTING_GROUND(new Dimension(720, 400), List.of(new Ball(new Point(0, 0))), List.of(), List.of()),
	PIPPO(new Dimension(720, 400), List.of(), List.of(), List.of());
	
	private final Dimension size;
	private final List<Ball> balls;
	private final List<MovingObject> movingObjects;
	private final List<MapObject> objects;
	
	private EMap(Dimension size, List<Ball> balls, List<MovingObject> otherMovingObjects, List<MapObject> otherObjects) {
		this.size = size;
		this.balls = balls;
		this.balls.stream().forEach(ball -> ball.setMap(this));
		this.movingObjects = otherMovingObjects;
		this.movingObjects.addAll(balls);
		this.objects = otherObjects;
		this.objects.addAll(movingObjects);
	}

	@Override
	public Dimension getSize() {
		return this.size;
	}

	@Override
	public List<MapObject> getObjects() {
		return this.objects;
	}

	@Override
	public void startMovingObjects() {
		this.movingObjects.stream().forEach(MovingObject::start);
	}

	@Override
	public boolean ballsAreMoving() {
		return this.balls.stream().anyMatch(Ball::isMoving);
	}

	@Override
	public List<Ball> getBalls() {
		return this.balls;
	}
	
}