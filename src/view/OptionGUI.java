package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import controller.NavigatorImpl;
import util.MyOptionPane;
import util.MyTitle;

/**
 * an interface which displays the option menù
 * @author loren
 *
 */
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
	private final JButton ballColorButton = new JButton("CHANGE BALL COLOR");
	private final NavigatorImpl navigator = new NavigatorImpl();
	private final ActionListener listener = new ButtonListener();
	private final MenuGUI menuGUI;
	
	/**
	 * @param menuGUI
	 */
	public OptionGUI(MenuGUI menuGUI) {
		super();
		this.menuGUI = menuGUI;
		this.setSize(OPTIONS_DIMENSION);
		this.buttonLayout.setVgap(10);
		this.mainPanel.setLayout(this.buttonLayout);
		this.mainPanel.setBorder(this.border);
		this.mainPanel.add(this.titlePanel);
		this.titlePanel.add(this.title);
		this.mainPanel.setLayout(this.buttonLayout);
		this.mainPanel.add(this.resetButton);
		this.mainPanel.add(this.ballColorButton);
		this.add(this.mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.resetButton.addActionListener(this.listener);
		this.ballColorButton.addActionListener(this.listener);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(OptionGUI.this, menuGUI);
			}
		});
	}
	
	/**
	 * the ActionListener which directs the buttons input toward the right new interface
	 * @author loren
	 *
	 */
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			if (e.getSource().equals(OptionGUI.this.resetButton)) {
				try {
					OptionGUI.this.navigator.resetLeaderboard();
					MyOptionPane.resetLeaderboard(OptionGUI.this, OptionGUI.this.menuGUI);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource().equals(OptionGUI.this.ballColorButton)) {
				JFrame changeColor = new JFrame();
				JLabel selectColor = new JLabel("Select a color: ");
				String colorOptions[] = OptionGUI.this.navigator.getBallColors();
				JComboBox<String> pickColor = new JComboBox<>(colorOptions);
				JButton selectButton = new JButton("Select");
				changeColor.setLayout(new FlowLayout());
				changeColor.add(selectColor);
				changeColor.add(pickColor);
				changeColor.add(selectButton);
				selectButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String newColor = (String)pickColor.getSelectedItem();
						OptionGUI.this.navigator.changeBallColor(newColor);
						MyOptionPane.changeBallColor(OptionGUI.this, OptionGUI.this.menuGUI);
						changeColor.dispose();
					}
				});
				changeColor.pack();
				changeColor.setVisible(true);
				
			}
		}
	}

}
