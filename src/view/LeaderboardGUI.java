package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import util.MyOptionPane;
import util.MyTitle;

public class LeaderboardGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final int MAX_PLAYERS_ON_LEADERBOARD = 10;
	private static final Dimension LEADERBOARD_DIMENSION = new Dimension(600,800);
	
	private final JPanel mainPanel = new JPanel();
	private final JPanel titlePanel = new JPanel();
	private final JPanel headerPanel = new JPanel();
	private final JLabel positionHeader = new JLabel("Position");
	private final JLabel playerHeader = new JLabel("Player");
	private final JLabel scoreHeader = new JLabel("Score");
	private final JLabel title = new MyTitle("LEADERBOARD");

	public LeaderboardGUI(Map<String,Integer> leaderboard, MenuGUI menuGUI) {
		/*
		 * TODO: Make this GUI decent, cute, well-designed... now it's orrible 
		 * (but it works well!)
		 */
		super();
		this.setSize(LEADERBOARD_DIMENSION);
		this.setLayout(new BorderLayout());
		this.titlePanel.setLayout(new BorderLayout());
		this.titlePanel.add(this.title);
		this.add(this.titlePanel, BorderLayout.NORTH);
		this.headerPanel.setLayout(new BorderLayout());
		this.headerPanel.add(this.positionHeader, BorderLayout.WEST);
		this.headerPanel.add(this.playerHeader, BorderLayout.CENTER);
		this.headerPanel.add(this.scoreHeader, BorderLayout.EAST);
		this.add(this.headerPanel, BorderLayout.CENTER);
		this.mainPanel.setLayout(new BorderLayout());
		this.mainPanel.add(this.leaderboardPanel(leaderboard));
		this.add(this.mainPanel, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(LeaderboardGUI.this, menuGUI);
			}
		});
	}
	
	private JPanel leaderboardPanel(Map<String,Integer> leaderboard) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		int position = 0;
		String[][] tableData = new String[MAX_PLAYERS_ON_LEADERBOARD][3];
		
		for (Entry<String, Integer> e : leaderboard.entrySet()) {
			System.out.println(e);
			final String playerPosition = Integer.toString(position + 1);
			final String playerName = e.getKey();
			final String playerScore = Integer.toString(e.getValue());
			tableData[position][0] = playerPosition;
			tableData[position][1] = playerName;
			tableData[position][2] = playerScore;
			position++;
		}
		
		String[] tableCol = new String[] {"Position", "Player Name", "Score"};
		final JTable table = new JTable(tableData, tableCol);
		
		panel.add(table);
		return panel;
	}

}
