package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class MapImpl implements Map {

	private final Dimension size;
	private final Ball ball;
	private final List<MovingObject> movingObjects;
	private final List<MapObject> objects;
	
	private MapImpl(Dimension size, Ball ball, List<MovingObject> otherMovingObjects, List<MapObject> otherObjects) {
		this.size = size;
		this.ball = ball;
		this.ball.setMap(this);
		this.movingObjects = new ArrayList<MovingObject>(otherMovingObjects);
		this.movingObjects.add(ball);
		this.objects = new ArrayList<MapObject>(otherObjects);
		this.objects.addAll(movingObjects);
	}
	
	public static MapImpl getMap(EMap map) {
		switch(map) {
			case PIPPO:
				return new MapImpl(new Dimension(720, 400),
						new Ball(new Point(50, 50)),
						List.of(),
						List.of());
			case STARTING_GROUND:
				return new MapImpl(new Dimension(720, 400),
						new Ball(new Point(50, 50)),
						List.of(),
						List.of());
			default:
				return null;
		}
	}
	
	public static MapImpl getMap(String mapName) {
		return MapImpl.getMap(EMap.get(mapName));
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
	public Ball getBall() {
		return this.ball;
	}

}
