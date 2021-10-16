package view;

import java.awt.BorderLayout;
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

public class LeaderboardGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final int MAX_PLAYERS = 10;
	private static final Dimension LEADERBOARD_DIMENSION = new Dimension(500, 770);
	private static final Dimension LINE_DIMENSION = new Dimension(500, 77);
	
	private final JPanel mainPanel = new JPanel();
	private final JPanel titlePanel = new JPanel();
	private final JLabel title = new MyTitle("LEADERBOARD");

	public LeaderboardGUI(Map<String,Integer> leaderboard) {
		super();
		this.setSize(LEADERBOARD_DIMENSION);
		this.setLayout(new BorderLayout());
		this.titlePanel.setSize(LINE_DIMENSION);
		this.titlePanel.add(this.title);
		this.add(this.titlePanel);
		this.mainPanel.setLayout(new BorderLayout());
		this.mainPanel.add(this.leaderboardPanel(leaderboard));
		this.add(this.mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.showClosingDialog();
			}
		});
	}
	
	private JPanel leaderboardPanel(Map<String,Integer> leaderboard) {
		JPanel panel = new JPanel();
		int position = 1;
		panel.setLayout(new GridLayout(MAX_PLAYERS,1));
		for (Entry<String, Integer> e : leaderboard.entrySet()) {
			//System.out.println(e); 
			//TODO: Doesn't display correctly: the first element is never displayed...
			final JPanel internPanel = new JPanel();
			internPanel.setSize(LINE_DIMENSION);
			internPanel.setLayout(new GridLayout(1,3));
			final JLabel positionLabel = new JLabel(Integer.toString(position++));
			final JLabel nameLabel = new JLabel(e.getKey());
			final JLabel scoreLabel = new JLabel(Integer.toString(e.getValue()));
			internPanel.add(positionLabel);
			internPanel.add(nameLabel);
			internPanel.add(scoreLabel);
			panel.add(internPanel);
		}
		return panel;
	}

}
