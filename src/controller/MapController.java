package controller;

import model.Course;
import model.Map;
import model.MovingObject;
import model.Vector2D;
import view.GameInput;
import view.GameOutput;

public class MapController extends Thread implements Controller {

	private final static long DELAY = 30;
	
	private GameOutput myOutput;
	private GameInput myInput;
	private final Map map;
	
	private boolean playing = false;
	private int currentShots;
	
	public MapController(Map m) {
		this.map = m;
	}
	
	@Override
	public void run() {
		try {
			while (playing) {
				//bisogna aggiungere che se la palla si ferma si attiva l'input
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
		this.map.getMovingObjects().stream().forEach(MovingObject::start);
		super.start();
	}
	
	@Override
	public void newShot(Vector2D shot) {
		//bisogna cambiare la velocità della pallina
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
