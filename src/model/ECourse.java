package model;

import java.util.List;

public enum ECourse implements Course {

	MAIN_COURSE("Main",
			List.of(EMap.PIPPO,
					EMap.STARTING_GROUND));
	
	private final String name;
	private final List<Map> maps;
	
	private ECourse(String name, List<Map> maps) {
		this.name = name;
		this.maps = maps;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public List<Map> getMaps() {
		return this.maps;
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
