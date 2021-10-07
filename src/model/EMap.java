package model;

import java.awt.Dimension;
import java.util.List;

public enum EMap implements Map {

	STARTING_GROUND(new Dimension(720, 400), List.of()),
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
	
}