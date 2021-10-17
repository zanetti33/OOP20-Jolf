package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyOptionPane extends JOptionPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static void showClosingDialog() {
		if (showConfirmDialog(null, "Are you sure you want to quit?", "Closing...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	public static void returnToMenu(JFrame thisGUI, MenuGUI menuGUI) {
		if (showConfirmDialog(null, "Return to menu?", "Closing...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			thisGUI.setVisible(false);
			menuGUI.setVisible(true);
		}
	}
	
}
