package util;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class MyTitle extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SIZE = 36;
	
	public MyTitle(String title) {
		super();
		this.setText(title);
		this.setFont(new Font("Comic Sans MS", Font.BOLD, SIZE));
		this.setForeground(Color.BLUE);
	}
}
