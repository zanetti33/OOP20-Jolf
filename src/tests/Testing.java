package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Angle;
import model.ECourse;

public class Testing {

	@Test
	public void testingECourse() {
		ECourse course = ECourse.MAIN_COURSE;
		assertEquals(course, ECourse.get("Main"));
	}
	
	@Test
	public void testingAngles() {
		Angle a = Angle.ofDegree(90);
		assertEquals(a.inverse().inverse(), a);
	}

}
