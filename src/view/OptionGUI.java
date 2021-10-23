package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controller.Navigator;
import controller.NavigatorImpl;

public class OptionGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int BORDER_WIDTH = 70;
	private static final int GRID_ROWS = 3;
	
	private static final Dimension OPTIONS_DIMENSION = new Dimension(600,800);
	private final Border border = new LineBorder(this.getBackground(), BORDER_WIDTH);
	private final GridLayout buttonLayout = new GridLayout(GRID_ROWS, 1);
	private final JPanel mainPanel = new JPanel();
	private final JPanel titlePanel = new JPanel();
	private final JLabel title = new MyTitle("OPTIONS");
	private final JButton resetButton = new JButton("RESET LEADERBOARD");
	private final JButton ballColourButton = new JButton("BALL COLOUR");
	private final Navigator navigator = new NavigatorImpl();
	private final ActionListener listener = new ButtonListener();
	
	public OptionGUI(MenuGUI menuGUI) {
		super();
		this.setSize(OPTIONS_DIMENSION);
		this.buttonLayout.setVgap(10);
		this.mainPanel.setLayout(this.buttonLayout);
		this.mainPanel.setBorder(this.border);
		this.mainPanel.add(this.titlePanel);
		this.titlePanel.add(this.title);
		this.mainPanel.setLayout(this.buttonLayout);
		this.mainPanel.add(this.resetButton);
		this.mainPanel.add(this.ballColourButton);
		this.add(this.mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.resetButton.addActionListener(this.listener);
		this.ballColourButton.addActionListener(this.listener);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(OptionGUI.this, menuGUI);
			}
		});
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			if (e.getSource().equals(OptionGUI.this.resetButton)) {
				try {
					OptionGUI.this.navigator.resetLeaderboard();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource().equals(OptionGUI.this.ballColourButton)) {
				//to implement
			}
		}
	}

}
