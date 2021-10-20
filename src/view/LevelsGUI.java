package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import controller.Controller;
import controller.MapController;

public class LevelsGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel mainPanel = new JPanel();
	
	private static final Dimension LEVELS_DIMENSION = new Dimension(600,800);
	private final JPanel titlePanel = new JPanel();
	private final JLabel title = new MyTitle("LEVELS");
	
	public LevelsGUI(List<String> maps, MenuGUI menuGUI) {
		super();
		this.mainPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
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
		
		this.setSize(LEVELS_DIMENSION);
		this.setLayout(new BorderLayout());
		this.titlePanel.setLayout(new BorderLayout());
		this.titlePanel.add(this.title);
		this.add(this.titlePanel, BorderLayout.NORTH);
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
