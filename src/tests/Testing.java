package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.ECourse;
import model.Edge;
import util.Angle;
import util.Point2D;
import util.Vector2D;

public class Testing {

	@Test
	public void testingECourse() {
		ECourse course = ECourse.COURSE_1;
		assertEquals(course, ECourse.get("1"));
	}
	
	@Test
	public void testingAngles() {
		Angle a = Angle.ofDegree(90);
		assertEquals(a.inverse().inverse(), a);
	}
	
	@Test
	public void testVectorsAndEdges() {
		Vector2D v = new Vector2D(10, 20);
		Point2D p = new Point2D(40, 30);
		Edge e = new Edge(0, 0, 20, 20);
		e = e.traslate(v);
		assertTrue(e.getP1().getX() == 10);
		assertTrue(e.getP1().getY() == 20);
		assertTrue(e.getP2().getX() == 30);
		assertTrue(e.getP2().getY() == 40);
		p = p.traslate(v);
		assertTrue(p.getX() == 50);
		assertTrue(p.getY() == 50);
	}

}
