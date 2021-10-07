package tests;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

import view.InputPanel;
import view.ShotMouseListener;

public class TestClickAndDrag {

	public static void main(final String[] args) {
		JFrame frame = new JFrame();
		InputPanel panel = new InputPanel();
		panel.setBackground(Color.PINK);
		frame.setSize(300, 200);
		frame.add(panel);
		frame.setVisible(true);
	}

}
