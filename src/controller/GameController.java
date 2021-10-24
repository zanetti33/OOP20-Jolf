package controller;


import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import model.Course;
import model.CourseImpl;
import model.Map;
import model.MapObject;
import model.Star;
import util.Vector2D;
import view.GameInput;
import view.GameOutput;

public class GameController implements Controller, GameInput, GameOutput {
	
	private GameOutput myOutput;
	private GameInput myInput;
	private final List<Map> maps;
	private final String playerName;
	private final Navigator navigator;
	private Controller mapController;
	private int mapIndex;
	private int totalShots;
	
	public GameController(final String playerName, final Course course) {
		this.totalShots = 0;
		this.maps = course.getMaps();
		this.mapIndex = 0;
		this.playerName = playerName;
		this.navigator = new NavigatorImpl();
	}
	
	public GameController(final String playerName, final String course) {
		this(playerName, CourseImpl.getCourse(course));
	}

	public void start() {
		this.nextMap();
	}
	
	public Optional<String> getPlayerName() {
		return Optional.of(this.playerName);
	}
	
	@Override
	public void forceStop() {
		this.mapController.forceStop();
	}
	
	private void nextMap() {
		if (this.mapIndex < this.maps.size()) {
			this.mapController = new MapController(this.maps.get(this.mapIndex));
			this.mapController.setOutput(this);
			this.mapController.setInput(this);
			this.mapController.start();
			this.mapIndex++;
		} else {
			try {
				this.navigator.writeOnLeaderboard(this.playerName, this.totalShots);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.myOutput.gameFinished(this.totalShots);
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
	public void updateObjectsPosition(List<MapObject> objects, List<Star> stars) {
		this.myOutput.updateObjectsPosition(objects, stars);
	}

	@Override
	public void updateShotCount(int shots) {
		this.myOutput.updateShotCount(shots);
		this.myOutput.updateTotalShotsCount(this.totalShots);
	}

	@Override
	public void setSize(Dimension size) {
		this.myOutput.setSize(size);
	}

	@Override
	public void updateTotalShotsCount(int totalShots) {
	}

	@Override
	public void gameFinished(final int shots) {
		this.nextMap();
	}

	@Override
	public void enableShot(Point ballPosition) {
		this.myInput.enableShot(ballPosition);
	}

	@Override
	public String getMapName() {
		return this.mapController.getMapName();
	}

}
