package model;

public class Angle {

	private static final double RADIANS_FULL_LOOP = Math.PI*2;
	private static final double DEGREES_FULL_LOOP = 360.0;
	
	private double degrees;
	private double radians;
	
	public double getRadians() {
		return radians;
	}

	public double getDegrees() {
		return degrees;
	}
	
	public static Angle ofDegree(final double degrees) {
		double newDegrees = degrees % DEGREES_FULL_LOOP;
		return new Angle(newDegrees, Math.toRadians(newDegrees));
	}
	
	public static Angle ofRadians(final double radians) {
		double newRadians = radians % RADIANS_FULL_LOOP;
		return new Angle(Math.toDegrees(newRadians), newRadians);
	}
	
	private Angle(final double degrees, final double radians) {
		this.degrees = degrees;
		this.radians = radians;
	}
	
	public Angle inverse() {
		return Angle.ofDegree(this.degrees + 180);
	}
	
}
