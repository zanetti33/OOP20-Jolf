package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.Controller;
import model.MapObject;

public class GameGUI extends JFrame implements GameOutput, GameInput {

	private final static String DEFAULT_TOTAL_SHOTS_LABEL = "Total Shots: ";
	private final static String DEFAULT_SHOTS_LABEL = "Map Shots: ";
	private final static String DEFAULT_NAME_LABEL = "Name: ";
	private final static Color GRASS_COLOR = new Color(34, 111, 84);
	private final static Color HOLE_COLOR = new Color(218, 44, 56);
	private final static int BORDER_THICKNESS = 20;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MapObject> objects;
	private final MenuGUI menuGUI;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private final JPanel displayShots = new JPanel(new GridLayout(1, 2));
	private final JLabel totalShotsLabel = new JLabel(DEFAULT_TOTAL_SHOTS_LABEL);
	private final JLabel shotsLabel = new JLabel(DEFAULT_SHOTS_LABEL);
	private final JLabel nameLabel = new JLabel(DEFAULT_NAME_LABEL);
	private final InputPanel inputPanel;
	private final JPanel displayGame = new JPanel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			g.setColor(GRASS_COLOR);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			for (MapObject obj : GameGUI.this.objects) {
				obj.draw(g);
			}
		}
	};
	
	private int totalShots;
	
	public GameGUI(Controller controller, MenuGUI menuGUI) {
		super();
		this.menuGUI = menuGUI;
		this.inputPanel = new InputPanel(controller);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.nameLabel.setBorder(new LineBorder(getBackground(), BORDER_THICKNESS));
		this.displayShots.setBorder(new LineBorder(getBackground(), BORDER_THICKNESS));
		this.displayShots.add(this.shotsLabel);
		this.displayShots.add(this.totalShotsLabel);
		this.layeredPane.add(this.inputPanel, JLayeredPane.DRAG_LAYER);
		this.layeredPane.add(this.displayGame, JLayeredPane.DEFAULT_LAYER);
		this.inputPanel.setOpaque(false);
		this.add(this.nameLabel, BorderLayout.NORTH);
		this.add(this.layeredPane, BorderLayout.CENTER);
		this.add(this.displayShots, BorderLayout.SOUTH);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(GameGUI.this, menuGUI, controller);
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
	public void gameFinished() {
		MyOptionPane.gameOver(this, this.menuGUI, this.totalShots);
	}

	@Override
	public void enableShot(Point ballPosition) {
		this.inputPanel.enableShot(ballPosition);
	}

}
