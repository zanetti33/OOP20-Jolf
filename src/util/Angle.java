package util;

public class Angle {
	
	private static final double RADIANS_FULL_LOOP = Math.PI * 2;
	private static final double DEGREES_FULL_LOOP = 360.0;
	
	private double degrees;
	private double radians;
	
	public static Angle ofDegree(final double degrees) {
		double newDegrees = degrees % DEGREES_FULL_LOOP;
		return new Angle(newDegrees, Math.toRadians(newDegrees));
	}
	
	public static Angle ofRadians(final double radians) {
		double newRadians = radians % RADIANS_FULL_LOOP;
		return new Angle(Math.toDegrees(newRadians), newRadians);
	}
	
	public static Angle ofLine(double x1, double y1, double x2, double y2) {
		return Angle.ofRadians(Math.atan2(y2 - y1, x2 - x1));
	}
	
	public double getRadians() {
		return radians;
	}

	public double getDegrees() {
		return degrees;
	}

	private Angle(final double degrees, final double radians) {
		this.degrees = degrees;
		this.radians = radians;
	}
	
	public Angle inverse() {
		return Angle.ofDegree(this.degrees + 180);
	}
	
	@Override
	public String toString() {
		return "Angle [degrees=" + degrees + ", radians=" + radians + " rads]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(degrees);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(radians);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Angle other = (Angle) obj;
		if (Double.doubleToLongBits(degrees) != Double.doubleToLongBits(other.degrees)) {
			return false;
		}
		if (Double.doubleToLongBits(radians) != Double.doubleToLongBits(other.radians)) {
			return false;
		}
		return true;
	}
	
}
