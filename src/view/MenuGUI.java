package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MenuGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Dimension MENU_DIMENSION = new Dimension(400, 600);
	
	private final JPanel panel = new JPanel();
	private final JPanel titlePanel = new JPanel();
	private final Border border = new LineBorder(this.getBackground(), 70);
	private final GridLayout buttonLayout = new GridLayout(6, 1);
	private final JLabel title = new MyTitle();
	private final JButton playButton = new JButton("PLAY");
	private final JButton levelsButton = new JButton("LEVELS");
	private final JButton multiplayerButton = new JButton("1 VS 1");
	private final JButton optionButton = new JButton("OPTIONS");
	private final JButton quitButton = new JButton("QUIT");
	private final ActionListener listener = new ButtonListener();
	
	public MenuGUI() {
		super();
		this.setSize(MENU_DIMENSION);
		this.buttonLayout.setVgap(10);
		panel.setLayout(buttonLayout);
		this.panel.setBorder(this.border);
		this.add(this.panel);
		this.panel.add(this.titlePanel);
		this.titlePanel.add(this.title);
		this.panel.add(this.playButton);
		this.panel.add(this.levelsButton);
		this.panel.add(this.multiplayerButton);
		this.panel.add(this.optionButton);
		this.panel.add(this.quitButton);
		//to implement
		this.multiplayerButton.setEnabled(false);
		this.multiplayerButton.addActionListener(this.listener);
		this.playButton.addActionListener(this.listener);
		this.levelsButton.addActionListener(this.listener);
		this.optionButton.addActionListener(this.listener);
		this.quitButton.addActionListener(this.listener);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.showClosingDialog();
			}
		});
	}
	
	private class ButtonListener implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(final ActionEvent e) {
			if (e.getSource().equals(MenuGUI.this.playButton)) {
				JFrame newGUI = new StartingGUI();
				newGUI.setVisible(true);
				MenuGUI.this.dispose();
			} else if (e.getSource().equals(MenuGUI.this.levelsButton)) {
				//temporary (we need the class for the gui where we select levels)
				JFrame newGUI = null;
				MenuGUI.this.dispose();
			} else if (e.getSource().equals(MenuGUI.this.optionButton)) {
				//temporary (we need the class for the gui where we display game options)
				JFrame newGUI = null;
				MenuGUI.this.dispose();
			} else if (e.getSource().equals(MenuGUI.this.multiplayerButton)) {
				//to implement
			} else if (e.getSource().equals(MenuGUI.this.quitButton)) {
				MyOptionPane.showClosingDialog();
			}
		}

	}
	
}
