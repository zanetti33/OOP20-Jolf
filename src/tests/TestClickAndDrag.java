package tests;

import java.awt.Color;

import javax.swing.JFrame;

import view.InputPanel;

public class TestClickAndDrag {

	public static void main(final String[] args) {
		JFrame frame = new JFrame();
		InputPanel panel = new InputPanel(null);
		panel.setBackground(Color.PINK);
		frame.setSize(300, 200);
		frame.add(panel);
		frame.setVisible(true);
	}

}
