package model;

public enum ECourse {

	MAIN_COURSE("Main");
	
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
