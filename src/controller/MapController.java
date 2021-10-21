package controller;
import model.Map;
import model.MapImpl;
import model.MovingObject;
import model.Vector2D;
import view.GameInput;
import view.GameOutput;

public class MapController extends Thread implements Controller {

	private final static long DELAY = 30;
	private final static double FROM_PIXEL_TO_SPEED = 0.001;
	
	private GameOutput myOutput;
	private GameInput myInput;
	private final Map map;
	
	private boolean playing = false;
	private boolean forcedStop = false;
	private int currentShots;
	
	public MapController(final Map m) {
		this.map = m;
	}
	
	public MapController(final String mapName) {
		this(MapImpl.getMap(mapName));
	}
	
	@Override
	public void run() {
		try {
			while (playing && !forcedStop) {
				if (!this.map.getBall().isMoving()) {
					this.myInput.enableShot(this.map.getBall().getPosition());
				}
				//e che se la palla è in buca allora si ferma la partita
				myOutput.updateObjectsPosition(this.map.getObjects(), this.map.getStars());
				Thread.sleep(DELAY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!forcedStop) {
			this.myOutput.gameFinished(this.currentShots);
		}
	}
	
	@Override
	public void start() {
		this.currentShots = 0;
		this.myOutput.setSize(this.map.getSize());
		this.myOutput.updateShotCount(this.currentShots);
		this.playing = true;
		this.map.getMovingObjects().stream().forEach(MovingObject::start);
		super.start();
	}
	
	@Override
	public void forceStop() {
		this.forcedStop = true;
		this.map.getBall().forceStop();
	}
	
	@Override
	public void newShot(Vector2D shot) {
		this.map.getBall().setSpeed(new Vector2D(shot.getX() * FROM_PIXEL_TO_SPEED, shot.getY() * FROM_PIXEL_TO_SPEED));
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
