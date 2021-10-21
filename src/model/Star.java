package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controller.Navigator;
import controller.NavigatorImpl;

public class Star implements MapObject {
	
	private final Point position;
	private final Navigator navigator;
	private final JFrame gameGUI;
	private final static int STAR_WIDTH = 50;
	private final static int STAR_HEIGHT = 50;
	
	public Star(final Point position, final JFrame gameGUI) {
		this.position = position;
		this.navigator = new NavigatorImpl();
		this.gameGUI = gameGUI;
	}

	@Override
	public Point getPosition() {
		return this.position;
	}

	@Override
	public void draw(final Graphics g) {
		Toolkit t = Toolkit.getDefaultToolkit();
		Image i = t.getImage(this.navigator.getStarImage());
		g.drawImage(i, this.position.x, this.position.y, STAR_WIDTH, STAR_HEIGHT, this.gameGUI);
	}

	@Override
	public void applyConstraintTo(final Ball ball) {
	}

}
