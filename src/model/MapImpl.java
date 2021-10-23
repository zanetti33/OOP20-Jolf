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
	private final List<Star> stars;
	
	private MapImpl(Dimension size, Ball ball, List<MovingObject> otherMovingObjects, List<MapObject> otherObjects, List<Star> stars) {
		this.size = size;
		this.ball = ball;
		this.ball.setMap(this);
		this.movingObjects = new ArrayList<MovingObject>(otherMovingObjects);
		this.movingObjects.add(ball);
		this.stars = new ArrayList<Star>(stars);
		this.objects = new ArrayList<MapObject>(otherObjects);
		this.objects.addAll(movingObjects);
		this.objects.addAll(stars);
	}
	
	public static MapImpl getMap(EMap map) {
		switch(map) {
			case TEST:
				return new MapImpl(new Dimension(720, 400),
						new Ball(new Point(100, 200)),
						List.of(),
						List.of(),
						List.of(new Star(new Point2D(300, 100)), new Star(new Point2D(500, 300)), new Star(new Point2D(680, 180))));
			case HOLE_1:
				return new MapImpl(new Dimension(720, 400),
						new Ball(new Point(90, 200)),
						List.of(),
						List.of(new Wall(new Point2D(0, 100), 50, 200), new Wall(new Point2D(50, 100), 620, 40),
								new Wall(new Point2D(50, 260), 620, 40), new Wall(new Point2D(670, 100), 50, 200),
								new Wall(new Point2D(300, 140), 30, 60), new Wall(new Point2D(500, 200), 30, 60)),
						List.of(new Star(new Point2D(190, 185)), new Star(new Point2D(385, 185)), new Star(new Point2D(635, 185))));
			case HOLE_2:
				return new MapImpl(new Dimension(720, 400),
						new Ball(new Point(50, 200)),
						List.of(),
						List.of(new Wall(new Point2D(130, 130), 300, 30), new Wall(new Point2D(130, 240), 300, 30),
								new Ice(new Point2D(130, 0), 300, 130), new Ice(new Point2D(130, 270), 300, 130),
								new Sand(new Point2D(130, 160), 300, 80)),
						List.of(new Star(new Point2D(450, 185)), new Star(new Point2D(600, 50)), new Star(new Point2D(600, 300))));
			case HOLE_3:
				return new MapImpl(new Dimension(720, 400),
						new Ball(new Point(30, 200)),
						List.of(),
						List.of(new Wall(new Point2D(110, 120), 300, 30), new Wall(new Point2D(180, 250), 300, 30), new Wall(new Point2D(570, 120), 30, 200),
								new Sand(new Point2D(180, 150), 230, 100), new Sand(new Point2D(600, 120), 150, 200)),
						List.of(new Star(new Point2D(300, 45)), new Star(new Point2D(220, 350)), new Star(new Point2D(600, 300))));
			case HOLE_4:
				return new MapImpl(new Dimension(720, 400),
						new Ball(new Point(600, 350)),
						List.of(),
						List.of(new Wall(new Point2D(370, 0), 30, 180), new Wall(new Point2D(130, 240), 300, 30), new Wall(new Point2D(150, 0), 30, 100), new Wall(new Point2D(0, 150), 100, 30),
								new Ice(new Point2D(130, 270), 300, 130),
								new Sand(new Point2D(400, 0), 120, 180), new Sand(new Point2D(220, 400), 300, 80)),
						List.of(new Star(new Point2D(600, 50)), new Star(new Point2D(250, 50)), new Star(new Point2D(150, 300))));
			case HOLE_5:
				return new MapImpl(new Dimension(720, 400),
						new Ball(new Point(10, 200)),
						List.of(),
						List.of(new Wall(new Point2D(130, 130), 300, 30), new Wall(new Point2D(130, 240), 300, 30), new Wall(new Point2D(550, 150), 30, 100),
								 new Wall(new Point2D(580, 150), 50, 20), new Wall(new Point2D(670, 150), 50, 20), new Wall(new Point2D(580, 230), 50, 20), new Wall(new Point2D(670, 230), 50, 20),
								new Ice(new Point2D(130, 0), 300, 130), new Ice(new Point2D(130, 270), 300, 130), new Ice(new Point2D(130, 350), 300, 130)),
						List.of(new Star(new Point2D(380, 60)), new Star(new Point2D(600, 190)), new Star(new Point2D(125, 350))));
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
	public List<MovingObject> getMovingObjects() {
		return this.movingObjects;
	}

	@Override
	public Ball getBall() {
		return this.ball;
	}

	@Override
	public List<Star> getStars() {
		return this.stars;
	}

}
