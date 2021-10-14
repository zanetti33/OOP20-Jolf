package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeaderboardGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static final Dimension LEADERBOARD_DIMENSION = new Dimension(400, 600);
	
	private final JPanel mainPanel = new JPanel();
	private final JPanel titlePanel = new JPanel();
	private final JLabel title = new MyTitle();

	public LeaderboardGUI(Map<String,Integer> leaderboard) {
		super();
		this.setSize(LEADERBOARD_DIMENSION);
		this.titlePanel.add(this.title);
		this.mainPanel.setLayout(new BorderLayout());
		this.mainPanel.add(this.titlePanel);
		this.mainPanel.add(this.leaderboardPanel(leaderboard));
		
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
		//TODO: implement how to display the leaderboard 
		return panel;
	}

}
