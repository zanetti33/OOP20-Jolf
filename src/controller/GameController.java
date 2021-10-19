package controller;


import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.List;

import model.Course;
import model.ECourse;
import model.Map;
import model.MapObject;
import model.Vector2D;
import view.GameInput;
import view.GameOutput;

public class GameController extends Thread implements Controller, GameInput, GameOutput {
	
	private GameOutput myOutput;
	private GameInput myInput;
	private final List<Map> maps;
	private final int players;
	private final String playerName;
	private final Navigator navigator;
	private MapController mapController;
	private int mapIndex;
	private int totalShots;
	
	public GameController(final String playerName, final Course course) {
		this.totalShots = 0;
		this.maps = course.getMaps();
		this.mapIndex = 0;
		this.players = 1;
		this.playerName = playerName;
		this.navigator = new NavigatorImpl();
	}
	
	public GameController(String playerName, String course) {
		this(playerName, ECourse.get(course));
	}

	public void start() {
		this.nextMap();
	}
	
	private void nextMap() {
		if (this.mapIndex < this.maps.size()) {
			this.mapController = new MapController(this.maps.get(this.mapIndex), this.players);
			this.mapController.setOutput(this);
			this.mapController.setInput(this);
			this.mapController.start();
			this.mapIndex++;
		} else {
			this.myOutput.gameFinished();
		}
	}
	
	@Override
	public void newShot(Vector2D shot) {
		this.totalShots++;
		this.mapController.newShot(shot);
	}

	@Override
	public void setInput(GameInput inputView) {
		this.myInput = inputView;
	}

	@Override
	public void setOutput(GameOutput outputView) {
		this.myOutput = outputView;
	}

	@Override
	public void updateObjectsPosition(List<MapObject> objects) {
		this.myOutput.updateObjectsPosition(objects);
	}

	@Override
	public void updateShotCount(int shots) {
		this.myOutput.updateShotCount(shots);
		this.myOutput.updateTotalShotsCount(this.totalShots);
	}

	@Override
	public void mapFinished() {
		this.nextMap();
	}

	@Override
	public void setSize(Dimension size) {
		this.myOutput.setSize(size);
	}

	@Override
	public void updateTotalShotsCount(int totalShots) {
	}

	@Override
	public void gameFinished() {
		try {
			this.navigator.writeOnLeaderboard(this.playerName, this.totalShots);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enableShot(Point ballPosition) {
		this.myInput.enableShot(ballPosition);
	}

}
