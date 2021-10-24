package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.Controller;
import model.MapObject;
import model.Star;
import util.MyOptionPane;

public class GameGUI extends JFrame implements GameOutput, GameInput {

	private final static String DEFAULT_TOTAL_SHOTS_LABEL = "Total Shots: ";
	private final static String DEFAULT_SHOTS_LABEL = "Map Shots: ";
	private final static String DEFAULT_NAME_LABEL = "Name: ";
	private final static String DEFAULT_MAP_LABEL = "Map: ";
	private final static Color GRASS_COLOR = new Color(34, 111, 84);
	private final static int BORDER_THICKNESS = 20;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private List<MapObject> objects;
	private List<Star> stars;
	private int totalShots;
	private final MenuGUI menuGUI;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private final JPanel displayShots = new JPanel(new GridLayout(1, 2));
	private final JLabel totalShotsLabel = new JLabel(DEFAULT_TOTAL_SHOTS_LABEL);
	private final JLabel shotsLabel = new JLabel(DEFAULT_SHOTS_LABEL);
	private final JPanel displayInfo = new JPanel(new GridLayout(1, 2));
	private final JLabel mapNameLabel = new JLabel(DEFAULT_MAP_LABEL);
	private final JLabel playerNameLabel = new JLabel(DEFAULT_NAME_LABEL);
	private final InputPanel inputPanel;
	private final JPanel displayGame = new JPanel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(GRASS_COLOR);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			super.paintChildren(g);
			for (MapObject obj : GameGUI.this.objects) {
				obj.draw(g);
			}
			for (Star star : GameGUI.this.stars) {
				star.draw(g, GameGUI.this.displayGame);
			}
		}

	};
	
	public GameGUI(Controller controller, MenuGUI menuGUI) {
		super();
		this.controller = controller;
		this.menuGUI = menuGUI;
		this.inputPanel = new InputPanel(controller);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		Optional<String> playerName = this.controller.getPlayerName();
		this.playerNameLabel.setText(playerName.isPresent() ? DEFAULT_NAME_LABEL + playerName.get() : "");
		this.displayInfo.setBorder(new LineBorder(getBackground(), BORDER_THICKNESS));
		this.displayInfo.add(this.playerNameLabel);
		this.displayInfo.add(this.mapNameLabel);
		this.displayShots.setBorder(new LineBorder(getBackground(), BORDER_THICKNESS));
		this.displayShots.add(this.shotsLabel);
		this.displayShots.add(this.totalShotsLabel);
		this.layeredPane.add(this.inputPanel, JLayeredPane.DRAG_LAYER);
		this.layeredPane.add(this.displayGame, JLayeredPane.DEFAULT_LAYER);
		this.inputPanel.setOpaque(false);
		this.add(this.displayInfo, BorderLayout.NORTH);
		this.add(this.layeredPane, BorderLayout.CENTER);
		this.add(this.displayShots, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(GameGUI.this, menuGUI, controller);
			}
		});
	}
	
	@Override
	public void updateObjectsPosition(final List<MapObject> objects, final List<Star> stars) {
		this.objects = objects;
		this.stars = stars;
		this.displayGame.repaint();
	}

	@Override
	public void updateShotCount(final int shots) {
		this.shotsLabel.setText(DEFAULT_SHOTS_LABEL + shots);
	}

	@Override
	public void setSize(Dimension mapSize) {
		this.mapNameLabel.setText(DEFAULT_MAP_LABEL + this.controller.getMapName());
		this.layeredPane.setPreferredSize(mapSize);
		this.displayGame.setPreferredSize(mapSize);
		this.inputPanel.setPreferredSize(mapSize);
		this.inputPanel.setBounds(0, 
				0, 
				Double.valueOf(mapSize.getWidth()).intValue(), 
				Double.valueOf(mapSize.getHeight()).intValue());
		this.displayGame.setBounds(0, 
				0, 
				Double.valueOf(mapSize.getWidth()).intValue(), 
				Double.valueOf(mapSize.getHeight()).intValue());
		this.pack();
	}

	@Override
	public void updateTotalShotsCount(int totalShots) {
		this.totalShots = totalShots;
		this.totalShotsLabel.setText(DEFAULT_TOTAL_SHOTS_LABEL + this.totalShots);
	}

	@Override
	public void gameFinished(int totalShots) {
		MyOptionPane.gameOver(this, this.menuGUI, totalShots);
	}

	@Override
	public void enableShot(Point ballPosition) {
		this.inputPanel.enableShot(ballPosition);
	}

}
