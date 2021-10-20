package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class OptionGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OptionGUI(MenuGUI menuGUI) {
		// TODO Auto-generated constructor stub
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MyOptionPane.returnToMenu(OptionGUI.this, menuGUI);
			}
		});
	}

}
