package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Dimension OPTIONS_DIMENSION = new Dimension(600,800);

	private final JPanel titlePanel = new JPanel();
	private final JLabel title = new MyTitle("OPTIONS");
	public OptionGUI(MenuGUI menuGUI) {
		// TODO Auto-generated constructor stub
		super();
		this.setSize(OPTIONS_DIMENSION);
		this.setLayout(new BorderLayout());
		this.titlePanel.setLayout(new BorderLayout());
		this.titlePanel.add(this.title);
		this.add(this.titlePanel, BorderLayout.NORTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(OptionGUI.this, menuGUI);
			}
		});
	}

}
