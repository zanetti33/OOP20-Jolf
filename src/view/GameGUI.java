package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.MapObject;

public class GameGUI extends JFrame implements GameOutput, GameInput {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MapObject> objects;
	private final JPanel displayGame = new JPanel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			for (MapObject obj : GameGUI.this.objects) {
				obj.draw(g);
			}
		}
	};
	
	public GameGUI() {
		super();
		this.setVisible(true);
	}
	
	@Override
	public void updateObjectsPosition(final List<MapObject> objects) {
		this.objects = objects;
		this.displayGame.repaint();
	}

	@Override
	public void updateShotCount(int shots) {
		System.out.println("shots: " + shots);
	}

	@Override
	public void mapFinished() {
		System.out.println("map finished");
	}

	@Override
	public void setSize(Dimension size) {
		super.setSize(size);
		this.displayGame.setSize(size);
		this.add(this.displayGame);
	}

	@Override
	public void updateTotalShotsCount(int totalShots) {
		System.out.println("total shots: " + totalShots);
	}

	@Override
	public void gameFinished() {
		System.out.println("game over");
	}

	@Override
	public void enableShot(Point ballPosition) {
	}

}
