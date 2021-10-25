package model;

/**
 * an enumeration of the courses in Jolf
 * @author loren
 *
 */
public enum ECourse {

	COURSE_1("1"), COURSE_2("2");
	
	private final String name;
	
	/**
	 * @param name
	 */
	private ECourse(String name) {
		this.name = name;
	}
	
	/**
	 * @return the course name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @param courseName
	 * @return the corresponding course in the enumeration
	 */
	public static ECourse get(String courseName) {
		ECourse result = null;
		for (ECourse course : ECourse.values()) {
			if (course.getName().equals(courseName)) {
				result = course;
			}
		}
		return result;
	}

}
