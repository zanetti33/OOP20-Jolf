package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;

import controller.Navigator;
import controller.NavigatorImpl;

public class Star implements MapObject {
	
	private final Point position;
	private final Navigator navigator;
	private final static int STAR_HEDGE = 30;
	
	public Star(final Point position) {
		this.position = position;
		this.navigator = new NavigatorImpl();
	}

	@Override
	public Point getPosition() {
		return this.position;
	}
	
	@Override
	public void draw(Graphics g) {
	}
	
	public void draw(final Graphics g, final JPanel gameGUI) {
		Toolkit t = Toolkit.getDefaultToolkit();
		Image i = t.getImage(this.navigator.getStarImage());
		g.drawImage(i, this.position.x, this.position.y, STAR_HEDGE, STAR_HEDGE, gameGUI);
	}

	@Override
	public void applyConstraintTo(final Ball ball) {
	}

}
