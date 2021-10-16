package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class StartingGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int BORDER_WIDTH = 30;
	
	private final JPanel panel = new JPanel();
	private final JButton startButton = new JButton("START");
	private final JLabel title = new MyTitle("JOLF");
	private final JTextField nameTextField = new JTextField("Insert name here");
	
	public StartingGUI() {
		super();
		this.add(this.panel);
		this.panel.setLayout(new FlowLayout(FlowLayout.LEADING, BORDER_WIDTH, BORDER_WIDTH));
		this.panel.add(this.title);
		this.panel.add(this.nameTextField);
		this.panel.add(this.startButton);
		this.pack();
		this.startButton.addActionListener(e -> {
			String playerName = this.nameTextField.getText();
			//temporary (it should pass playerName to the next GUI that will display the game)
			System.out.println(playerName);
			this.dispose();
		});
	}

}
