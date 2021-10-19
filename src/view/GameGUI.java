package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controller.Controller;
import model.MapObject;

public class GameGUI extends JFrame implements GameOutput, GameInput {

	private final static String DEFAULT_TOTAL_SHOTS_LABEL = "Total Shots: ";
	private final static String DEFAULT_SHOTS_LABEL = "Map Shots: ";
	private final static int BORDER_THICKNESS = 20;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MapObject> objects;
	private final MenuGUI menuGUI;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private final JPanel displayShots = new JPanel();
	private final JLabel totalShotsLabel = new JLabel(DEFAULT_TOTAL_SHOTS_LABEL);
	private final JLabel shotsLabel = new JLabel(DEFAULT_SHOTS_LABEL);
	private final InputPanel inputPanel;
	private final JPanel displayGame = new JPanel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			g.clearRect(0, 0, this.getWidth(), this.getHeight());
			g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
			for (MapObject obj : GameGUI.this.objects) {
				obj.draw(g);
			}
		}
	};
	
	private int totalShots;
	
	public GameGUI(Controller controller, MenuGUI menuGUI) {
		super();
		this.menuGUI = menuGUI;
		this.setLayout(new FlowLayout());
		this.add(this.displayShots);
		this.displayShots.add(this.shotsLabel);
		this.displayShots.add(this.totalShotsLabel);
		this.inputPanel = new InputPanel(controller);
		this.add(this.layeredPane);
		this.layeredPane.add(this.inputPanel, JLayeredPane.DRAG_LAYER);
		this.layeredPane.add(this.displayGame, JLayeredPane.DEFAULT_LAYER);
		this.inputPanel.setOpaque(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(GameGUI.this, menuGUI);
			}
		});
	}
	
	@Override
	public void updateObjectsPosition(final List<MapObject> objects) {
		this.objects = objects;
		this.displayGame.repaint();
	}

	@Override
	public void updateShotCount(int shots) {
		this.shotsLabel.setText(DEFAULT_SHOTS_LABEL + shots);
	}

	@Override
	public void mapFinished() {
		System.out.println("map finished");
	}

	@Override
	public void setSize(Dimension mapSize) {
		Dimension containerSize = new Dimension(Double.valueOf(mapSize.getWidth()).intValue() + BORDER_THICKNESS,
				Double.valueOf(mapSize.getHeight()).intValue() + BORDER_THICKNESS);
		this.layeredPane.setPreferredSize(containerSize);
		this.displayGame.setPreferredSize(mapSize);
		this.inputPanel.setPreferredSize(containerSize);
		this.inputPanel.setBounds(0, 
				0, 
				Double.valueOf(containerSize.getWidth()).intValue(), 
				Double.valueOf(containerSize.getHeight()).intValue());
		this.displayGame.setBounds(0, 
				0, 
				Double.valueOf(mapSize.getWidth()).intValue(), 
				Double.valueOf(mapSize.getHeight()).intValue());
		this.pack();
		System.out.println(this.getSize());
		System.out.println(this.inputPanel.getSize());
		System.out.println(this.displayGame.getSize());
		System.out.println(this.layeredPane.getSize());
	}

	@Override
	public void updateTotalShotsCount(int totalShots) {
		this.totalShots = totalShots;
		this.totalShotsLabel.setText(DEFAULT_TOTAL_SHOTS_LABEL + this.totalShots);
	}

	@Override
	public void gameFinished() {
		MyOptionPane.gameOver(this, this.menuGUI, this.totalShots);
	}

	@Override
	public void enableShot(Point ballPosition) {
		this.inputPanel.enableShot(ballPosition);
	}

}
