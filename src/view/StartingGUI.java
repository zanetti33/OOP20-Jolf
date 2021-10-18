package view;

import java.awt.FlowLayout;
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


public class StartingGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int BORDER_WIDTH = 30;
	
	private final JPanel panel = new JPanel();
	private final JLabel title = new MyTitle("JOLF");
	private final JTextField nameTextField = new JTextField("Insert name here");
	
	public StartingGUI(List<String> courses, MenuGUI menuGUI) {
		super();
		this.add(this.panel);
		this.panel.setLayout(new FlowLayout(FlowLayout.LEADING, BORDER_WIDTH, BORDER_WIDTH));
		this.panel.add(this.title);
		this.panel.add(this.nameTextField);
		courses.stream().forEach(course -> {
			JButton button = new JButton(course);
			this.panel.add(button);
			button.addActionListener(e -> {
				String playerName = this.nameTextField.getText();
				Controller controller = new GameController(playerName, course);
				GameGUI gameGUI = new GameGUI(controller, menuGUI);
				controller.setInput(gameGUI);
				controller.setOutput(gameGUI);
				gameGUI.setVisible(true);
				controller.start();
				this.dispose();
			});
		});
		this.pack();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(StartingGUI.this, menuGUI);
			}
		});
	}

}
