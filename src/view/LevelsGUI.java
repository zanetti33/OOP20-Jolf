package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;

public class LevelsGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LevelsGUI(List<String> leaderboard, MenuGUI menuGUI) {
		// TODO Auto-generated constructor stub
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(LeaderboardGUI.this, menuGUI);
			}
		});
	}

}
