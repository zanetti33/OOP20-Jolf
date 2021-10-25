package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import controller.Navigator;
import controller.NavigatorImpl;
import util.Point2D;

/**
 * The magical star object in the game Jolf which became a hole after 2 of them are hit
 * @author loren
 *
 */
public class Star implements MapObject {
	
	private final static int STAR_EDGE = 30;
	private final static int HOLE_DIAMETER = 24;
	private final static int STAR_HOLE_DISTANCE = (STAR_EDGE - HOLE_DIAMETER) / 2;
	private final static Color HOLE_COLOR = Color.WHITE;
	
	private final Point2D position;
	private final Navigator navigator;
	
	private Shape hitbox;
	private boolean isHole;
	private boolean isVisible;
	private boolean isGameOver;
	
	/**
	 * @param position
	 */
	public Star(final Point2D position) {
		this.position = position;
		this.navigator = new NavigatorImpl();
		this.isHole = false;
		this.isVisible = true;
		this.isGameOver = false;
		this.hitbox = new Rectangle2D.Double(position.getX(), position.getY(), STAR_EDGE, STAR_EDGE);
	}

	@Override
	public Point2D getPosition() {
		return this.position;
	}
	
	@Override
	public String toString() {
		return "Star [isHole=" + isHole + ", isVisible=" + isVisible + ", isGameOver=" + isGameOver +
				", hitbox: " + this.hitbox.getBounds().getCenterX() + ", " + this.hitbox.getBounds().getCenterY() + "]";
	}

	/**
	 * not used
	 */
	@Override
	public void draw(Graphics g) {
	}
	
	/**
	 * draws the star in the Graphics G and JPanel gameGUI
	 * @param g
	 * @param gameGUI
	 */
	public void draw(final Graphics g, final JPanel gameGUI) {
		if (this.isVisible) {
			if (this.isHole) {
				g.setColor(HOLE_COLOR);
				g.fillOval(this.position.getIntX() + STAR_HOLE_DISTANCE,
						this.position.getIntY() + STAR_HOLE_DISTANCE,
						HOLE_DIAMETER,
						HOLE_DIAMETER);
			} else {
				Toolkit t = Toolkit.getDefaultToolkit();
				Image i = t.getImage(this.navigator.getStarImage());
				g.drawImage(i, this.position.getIntX(), this.position.getIntY(), STAR_EDGE, STAR_EDGE, gameGUI);
			}
		}
	}
	
	/**
	 * @return false if the star is hit by a ball, therefore it became invisible
	 */
	public boolean isVisible() {
		return this.isVisible;
	}

	/**
	 * @return true if the star is became a hole and gets hit by the ball, therefore the game ends
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * the star magically become an hole
	 */
	public void becomeHole() {
		this.isHole = true;
		this.hitbox = new Ellipse2D.Double(this.getPosition().getX() + STAR_HOLE_DISTANCE, 
				this.getPosition().getY() + STAR_HOLE_DISTANCE, 
				HOLE_DIAMETER, 
				HOLE_DIAMETER);
	}
	
	@Override
	public void applyConstraintTo(final Ball ball) {
		if (this.isVisible) {
			if (this.isHole) {
				if (this.hitbox.contains(ball.getPosition().getX(), ball.getPosition().getY())) {
					this.isGameOver = true;
				}
			} else {
				if (this.hitbox.contains(ball.getPosition().getX(), ball.getPosition().getY())) {
					this.isVisible = false;
				}
			}
		}
	}

}
