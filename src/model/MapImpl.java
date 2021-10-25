package model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import util.Point2D;
import util.Vector2D;

/**
 * the map of Jolf implementation
 * @author loren
 *
 */
public class MapImpl implements Map {

	private final String name;
	private final Dimension size;
	private final Ball ball;
	private final List<MovingObject> movingObjects;
	private final List<MapObject> objects;
	private final List<Star> stars;
	
	/**
	 * @param name
	 * @param size
	 * @param ball
	 * @param otherMovingObjects
	 * @param otherObjects
	 * @param stars
	 */
	private MapImpl(String name, Dimension size, Ball ball, List<MovingObject> otherMovingObjects, List<MapObject> otherObjects, List<Star> stars) {
		this.name = name;
		this.size = size;
		this.ball = ball;
		this.movingObjects = new ArrayList<MovingObject>(otherMovingObjects);
		this.movingObjects.add(ball);
		this.movingObjects.forEach(mo -> mo.setMap(this));
		this.stars = new ArrayList<Star>(stars);
		this.objects = new ArrayList<MapObject>(otherObjects);
		this.objects.addAll(movingObjects);
		this.objects.addAll(stars);
	}
	
	/**
	 * @param map
	 * @return an instance of this map
	 */
	public static MapImpl getMap(EMap map) {
		switch(map) {
			case TEST:
				return new MapImpl(map.getName(),
						new Dimension(720, 400),
						new Ball(new Point2D(100, 200)),
						List.of(new MovingEdge(new Point2D(400, 20), new Point2D(400, 40), new Vector2D(0, 50))),
						List.of(),
						List.of(new Star(new Point2D(300, 100)), new Star(new Point2D(500, 300)), new Star(new Point2D(680, 180))));
			case HOLE_1:
				return new MapImpl(map.getName(),
						new Dimension(720, 400),
						new Ball(new Point2D(90, 200)),
						List.of(),
						List.of(new Wall(new Point2D(0, 100), 50, 200), new Wall(new Point2D(50, 100), 620, 40),
								new Wall(new Point2D(50, 260), 620, 40), new Wall(new Point2D(670, 100), 50, 200),
								new Wall(new Point2D(300, 140), 30, 60), new Wall(new Point2D(500, 200), 30, 60)),
						List.of(new Star(new Point2D(190, 185)), new Star(new Point2D(385, 185)), new Star(new Point2D(635, 185))));
			case HOLE_2:
				return new MapImpl(map.getName(),
						new Dimension(720, 400),
						new Ball(new Point2D(50, 200)),
						List.of(),
						List.of(new Wall(new Point2D(130, 130), 300, 30), new Wall(new Point2D(130, 240), 300, 30),
								new Ice(new Point2D(130, 0), 300, 130), new Ice(new Point2D(130, 270), 300, 130),
								new Sand(new Point2D(130, 160), 300, 80)),
						List.of(new Star(new Point2D(450, 185)), new Star(new Point2D(600, 50)), new Star(new Point2D(600, 300))));
			case HOLE_3:
				return new MapImpl(map.getName(),
						new Dimension(720, 400),
						new Ball(new Point2D(30, 200)),
						List.of(),
						List.of(new Wall(new Point2D(110, 120), 300, 30), new Wall(new Point2D(180, 250), 300, 30), 
								new Wall(new Point2D(570, 120), 30, 200),new Sand(new Point2D(180, 150), 230, 100), 
								new Sand(new Point2D(600, 120), 150, 200)),
						List.of(new Star(new Point2D(300, 45)), new Star(new Point2D(220, 350)), new Star(new Point2D(600, 300))));
			case HOLE_4:
				return new MapImpl(map.getName(),
						new Dimension(720, 400),
						new Ball(new Point2D(600, 350)),
						List.of(),
						List.of(new Wall(new Point2D(370, 0), 30, 180), new Wall(new Point2D(130, 240), 300, 30), 
								new Wall(new Point2D(150, 0), 30, 100), new Wall(new Point2D(0, 150), 100, 30),
								new Ice(new Point2D(130, 270), 300, 130), new Sand(new Point2D(400, 0), 120, 180), 
								new Sand(new Point2D(220, 400), 300, 80)),
						List.of(new Star(new Point2D(600, 50)), new Star(new Point2D(250, 50)), new Star(new Point2D(150, 300))));
			case HOLE_5:
				return new MapImpl(map.getName(),
						new Dimension(720, 400),
						new Ball(new Point2D(10, 200)),
						List.of(),
						List.of(new Wall(new Point2D(130, 130), 300, 30), new Wall(new Point2D(130, 240), 300, 30), 
								new Wall(new Point2D(550, 150), 30, 100), new Wall(new Point2D(580, 150), 50, 20), 
								new Wall(new Point2D(670, 150), 50, 20), new Wall(new Point2D(580, 230), 50, 20), 
								new Wall(new Point2D(670, 230), 50, 20), new Ice(new Point2D(130, 0), 300, 130), 
								new Ice(new Point2D(130, 270), 300, 130), new Ice(new Point2D(130, 350), 300, 130)),
						List.of(new Star(new Point2D(380, 60)), new Star(new Point2D(600, 190)), new Star(new Point2D(125, 350))));
			case HOLE_6:
				return new MapImpl(map.getName(),
						new Dimension(720, 400),
						new Ball(new Point2D(50, 350)),
						List.of(),
						List.of(new Cone(new Point2D(50, 0), new Point2D(150, 100), new Point2D(250, 0)),
								new Cone(new Point2D(150, 399), new Point2D(250, 300), new Point2D(350, 399)),
								new Cone(new Point2D(400, 0), new Point2D(450, 100), new Point2D(500, 0)),
								new Wall(new Point2D(240, 110), 20, 190), new Wall(new Point2D(440, 100), 20, 150),
								new Wall(new Point2D(130, 100), 20, 190), new Sand(new Point2D(460, 250), 260, 150)),
						List.of(new Star(new Point2D(300, 40)), new Star(new Point2D(420, 300)), new Star(new Point2D(580, 40))));
			default:
				return null;
		}
	}
	
	/**
	 * @param mapName
	 * @return the an instance of the map with the name mapName
	 */
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

	@Override
	public String getName() {
		return this.name;
	}

}
