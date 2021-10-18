package controller;

import model.Map;
import model.Vector2D;
import view.GameInput;
import view.GameOutput;

public class MapController extends Thread implements Controller {

	private final static long DELAY = 30;
	
	private GameOutput myOutput;
	private GameInput myInput;
	private final Map map;
	private final int players;
	
	private boolean playing = false;
	private int currentShots;
	private int currentPlayer;
	
	public MapController(Map m, int players) {
		this.map = m;
		this.currentPlayer = 0;
		this.players = players;
	}
	
	@Override
	public void run() {
		try {
			while (playing) {
				if (!this.map.ballsAreMoving()) {
					this.myInput.enableShot(this.map.getBalls()
							.get(this.currentPlayer)
							.getPosition());
				}
				//e che se la palla è in buca allora si ferma la partita
				myOutput.updateObjectsPosition(this.map.getObjects());
				Thread.sleep(DELAY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.myOutput.mapFinished();
	}
	
	@Override
	public void start() {
		this.currentShots = 0;
		this.myOutput.updateShotCount(this.currentShots);
		this.playing = true;
		this.map.startMovingObjects();
		super.start();
	}
	
	@Override
	public void newShot(Vector2D shot) {
		this.map.getBalls().get(this.currentPlayer).setSpeed(shot);
		this.currentPlayer = (this.currentPlayer + 1) % this.players;
		this.currentShots++;
		this.myOutput.updateShotCount(this.currentShots);
	}

	@Override
	public void setInput(GameInput inputView) {
		this.myInput = inputView;
	}

	@Override
	public void setOutput(GameOutput outputView) {
		this.myOutput = outputView;
	}

}
