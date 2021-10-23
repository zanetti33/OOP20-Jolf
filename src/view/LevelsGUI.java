package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
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
	private static final int GRID_ROWS = 7;
	
	private static final Dimension LEVELS_DIMENSION = new Dimension(600,800);
	private final Border border = new LineBorder(this.getBackground(), BORDER_WIDTH);
	private final GridLayout buttonLayout = new GridLayout(GRID_ROWS, 1);
	private final JPanel mainPanel = new JPanel();
	private final JPanel titlePanel = new JPanel();
	private final JLabel title = new MyTitle("LEVELS");
	
	public LevelsGUI(List<String> maps, MenuGUI menuGUI) {
		super();
		this.setSize(LEVELS_DIMENSION);
		this.buttonLayout.setVgap(10);
		this.mainPanel.setLayout(this.buttonLayout);
		this.mainPanel.setBorder(this.border);
		this.mainPanel.add(this.titlePanel);
		this.titlePanel.add(this.title);
		this.mainPanel.setLayout(this.buttonLayout);
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
