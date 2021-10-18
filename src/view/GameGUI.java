package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
	private final static int BORDER_THICKNESS = 10;
	private final static Color BORDER_COLOR = Color.BLUE;
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
			for (MapObject obj : GameGUI.this.objects) {
				obj.draw(g);
			}
		}
	};
	
	private int totalShots;
	
	public GameGUI(Controller controller, MenuGUI menuGUI) {
		super();
		this.menuGUI = menuGUI;
		this.setLayout(new BorderLayout());
		this.add(this.displayShots, BorderLayout.NORTH);
		this.displayShots.add(this.shotsLabel);
		this.displayShots.add(this.totalShotsLabel);
		this.inputPanel = new InputPanel(controller);
		this.inputPanel.setOpaque(false);
		this.add(this.layeredPane, BorderLayout.CENTER);/*
		this.layeredPane.add(this.inputPanel, JLayeredPane.DRAG_LAYER);*/
		this.layeredPane.add(this.displayGame, JLayeredPane.DEFAULT_LAYER);
		this.displayGame.setBorder(new LineBorder(BORDER_COLOR, BORDER_THICKNESS));
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
	public void setSize(Dimension size) {
		super.setSize(size);
		this.layeredPane.setSize(size);
		this.inputPanel.setSize(size);
		this.displayGame.setSize(size);
		this.layeredPane.setBounds(0, 
				0, 
				Double.valueOf(size.getWidth()).intValue(), 
				Double.valueOf(size.getHeight()).intValue());
		this.inputPanel.setBounds(0, 
				0, 
				Double.valueOf(size.getWidth()).intValue(), 
				Double.valueOf(size.getHeight()).intValue());
		this.layeredPane.setBounds(0, 
				0, 
				Double.valueOf(size.getWidth()).intValue(), 
				Double.valueOf(size.getHeight()).intValue());
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
