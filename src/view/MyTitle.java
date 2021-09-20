package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class MyTitle extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SIZE = 36;
	
	public MyTitle() {
		super();
		this.setText("JOLF");
		this.setFont(new Font("Comic Sans MS", Font.BOLD, SIZE));
		this.setForeground(Color.BLUE);
	}
}
