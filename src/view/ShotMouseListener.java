package view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class ShotMouseListener implements MouseListener, MouseMotionListener {

	private Point startingPoint = null;
	private Point currentPoint = null;
	private final JComponent father;
	private boolean enabled = true;
	
	public ShotMouseListener(JComponent c) {
		 this.father = c;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.currentPoint = e.getPoint();
		if (this.enabled) {
			this.father.repaint();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.startingPoint = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println(this.startingPoint);
		System.out.println(this.currentPoint);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
