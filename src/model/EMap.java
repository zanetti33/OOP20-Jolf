package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;

public enum EMap implements Map {

	STARTING_GROUND(new Dimension(720, 400), List.of(new Ball(new Point(0, 0)))),
	PIPPO(new Dimension(720, 400), List.of());
	
	private Dimension size;
	private List<MapObject> objects;
	
	private EMap(Dimension size, List<MapObject> objects) {
		this.size = size;
		this.objects = objects;
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
		
	}
	
}