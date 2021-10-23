package model;

public enum ECourse {

	COURSE_1("1"), COURSE_2("2"), COURSE_3("3");
	
	private final String name;
	
	private ECourse(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
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
