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
		if (showConfirmDialog(thisGUI, "Return to menu?", "Closing...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			thisGUI.dispose();
			menuGUI.setVisible(true);
		}
	}
	
	public static void gameOver(JFrame thisGUI, MenuGUI menuGUI, int totalShots) {
		JOptionPane.showMessageDialog(thisGUI, "Well done! You completed the course with " + totalShots + " shots!");
		thisGUI.dispose();
		menuGUI.setVisible(true);
	}
	
}
