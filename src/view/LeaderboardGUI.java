package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.MyOptionPane;
import util.MyTitle;

/**
 * the interface that lets the user view the leaderboard
 * @author loren
 *
 */
public class LeaderboardGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final int MAX_PLAYERS_ON_LEADERBOARD = 10;
	private static final Dimension LEADERBOARD_DIMENSION = new Dimension(600, 800);
	private static final Dimension TITLE_DIMENSION = new Dimension(600, 70);
	private static final Dimension POSITION_DIMENSION = new Dimension(80, 50);
	private static final Dimension PLAYER_DIMENSION = new Dimension(350, 50);
	private static final Dimension SCORE_DIMENSION = new Dimension(80, 50);
	
	private final JPanel mainPanel = new JPanel();
	private final JPanel titlePanel = new JPanel();
	private final JLabel positionHeader = new JLabel("Position");
	private final JLabel playerHeader = new JLabel("Player");
	private final JLabel scoreHeader = new JLabel("Score");
	private final JLabel title = new MyTitle("LEADERBOARD");

	/**
	 * @param leaderboard
	 * @param menuGUI
	 */
	public LeaderboardGUI(Map<String,Integer> leaderboard, MenuGUI menuGUI) {
		super();
		this.setSize(LEADERBOARD_DIMENSION);
		this.setLayout(new FlowLayout());
		this.titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.titlePanel.add(this.title);
		this.titlePanel.setPreferredSize(TITLE_DIMENSION);
		this.add(this.titlePanel);
		this.positionHeader.setPreferredSize(POSITION_DIMENSION);
		this.playerHeader.setPreferredSize(PLAYER_DIMENSION);
		this.scoreHeader.setPreferredSize(SCORE_DIMENSION);
		this.mainPanel.setLayout(new FlowLayout());
		this.mainPanel.add(this.leaderboardPanel(leaderboard));
		this.add(this.mainPanel);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(LeaderboardGUI.this, menuGUI);
			}
		});
	}
	
	/**
	 * @param leaderboard
	 * @return a JPanel displaying the leaderboard
	 */
	private JPanel leaderboardPanel(Map<String,Integer> leaderboard) {
		JPanel leaderboardPanel = new JPanel();
		leaderboardPanel.setLayout(new GridLayout(MAX_PLAYERS_ON_LEADERBOARD + 1, 1));
		int position = 1;
		
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new FlowLayout());
		headerPanel.add(this.positionHeader);
		headerPanel.add(this.playerHeader);
		headerPanel.add(this.scoreHeader);
		leaderboardPanel.add(headerPanel);
		
		for (Entry<String, Integer> e : leaderboard.entrySet()) {
			JPanel internPanel = new JPanel();
			JLabel playerPosition = new JLabel(Integer.toString(position++));
			JLabel playerName = new JLabel(e.getKey());
			JLabel playerScore = new JLabel(Integer.toString(e.getValue()));
			internPanel.setLayout(new FlowLayout());
			playerPosition.setPreferredSize(POSITION_DIMENSION);
			playerName.setPreferredSize(PLAYER_DIMENSION);
			playerScore.setPreferredSize(SCORE_DIMENSION);
			internPanel.add(playerPosition);
			internPanel.add(playerName);
			internPanel.add(playerScore);
			leaderboardPanel.add(internPanel);
			if (position > MAX_PLAYERS_ON_LEADERBOARD) {
				return leaderboardPanel;
			}
		}
		return leaderboardPanel;
	}

}
