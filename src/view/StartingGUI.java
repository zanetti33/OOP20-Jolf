package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import controller.GameController;
import util.MyOptionPane;
import util.MyTitle;


public class StartingGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int BORDER_WIDTH = 30;
	private static final Dimension TEXTFIELD_DIMENSION = new Dimension(200,20);
	
	private final JPanel namePanel = new JPanel();
	private final JPanel titlePanel = new JPanel();
	private final JLabel title = new MyTitle("JOLF");
	private final JLabel insertName = new JLabel("Insert name here: ");
	private final JTextField nameTextField = new JTextField();
	
	public StartingGUI(List<String> courses, MenuGUI menuGUI) {
		super();
		this.setLayout(new GridLayout(courses.size() + 2, 1));
		this.titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.titlePanel.add(this.title);
		this.add(this.titlePanel);
		this.namePanel.setLayout(new FlowLayout(FlowLayout.LEADING, BORDER_WIDTH, BORDER_WIDTH));
		this.namePanel.add(this.insertName);
		this.namePanel.add(this.nameTextField);
		this.nameTextField.setPreferredSize(TEXTFIELD_DIMENSION);
		this.add(namePanel);
		courses.stream().forEach(course -> {
			JButton button = new JButton("Course " + course);
			this.add(button);
			button.addActionListener(e -> {
				String playerName = this.nameTextField.getText();
				if (playerName.contains("=") || playerName.isBlank()) {
					MyOptionPane.incorrectPlayerName(this);
				} else {
					Controller controller = new GameController(playerName, course);
					GameGUI gameGUI = new GameGUI(controller, menuGUI);
					controller.setInput(gameGUI);
					controller.setOutput(gameGUI);
					gameGUI.setVisible(true);
					controller.start();
					this.dispose();
				}
			});
		});		
		this.pack();
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(StartingGUI.this, menuGUI);
			}
		});
	}

}
