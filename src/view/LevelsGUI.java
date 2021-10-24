package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controller.Controller;
import controller.MapController;
import util.MyOptionPane;
import util.MyTitle;

public class LevelsGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int BORDER_WIDTH = 70;
	
	private static final Dimension LEVELS_DIMENSION = new Dimension(600,800);
	private final Border border = new LineBorder(this.getBackground(), BORDER_WIDTH); 
	private final JPanel mainPanel = new JPanel();
	private final JPanel titlePanel = new JPanel();
	private final JLabel title = new MyTitle("LEVELS");
	
	public LevelsGUI(List<String> maps, MenuGUI menuGUI) {
		super();
		this.setSize(LEVELS_DIMENSION);
		GridLayout buttonLayout = new GridLayout(maps.size() + 1, 1);
		buttonLayout.setVgap(10);
		this.mainPanel.setLayout(buttonLayout);
		this.mainPanel.setBorder(this.border);
		this.titlePanel.add(this.title);
		this.mainPanel.add(this.titlePanel);
		maps.forEach(map -> {
			JButton button = new JButton("Map " + map);
			button.addActionListener(e -> {
				Controller controller = new MapController(map);
				GameGUI gameGUI = new GameGUI(controller, menuGUI);
				controller.setInput(gameGUI);
				controller.setOutput(gameGUI);
				gameGUI.setVisible(true);
				controller.start();
				this.dispose();
			});
			this.mainPanel.add(button);
		});
		this.add(this.mainPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(LevelsGUI.this, menuGUI);
			}
		});
	}
	
}
