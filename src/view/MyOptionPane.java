package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Controller;

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
	
	public static void returnToMenu(JFrame thisGUI, MenuGUI menuGUI, Controller controller) {
		if (showConfirmDialog(thisGUI, "Return to menu?", "Closing...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			thisGUI.dispose();
			controller.forceStop();
			menuGUI.setVisible(true);
		}
	}
	
	public static void gameOver(JFrame thisGUI, MenuGUI menuGUI, int totalShots) {
		JOptionPane.showMessageDialog(thisGUI, "Well done! You made it with a total of " + totalShots + " shots!");
		thisGUI.dispose();
		menuGUI.setVisible(true);
	}
	
}
