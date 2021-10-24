package model;
import java.util.List;

public class CourseImpl implements Course {

	private final List<Map> maps;
	
	private CourseImpl(List<Map> maps) {
		this.maps = maps;
	}
	
	public static CourseImpl getCourse(ECourse course) {
		switch(course) {
			case COURSE_1:
				return new CourseImpl(List.of(MapImpl.getMap("Hole1"), MapImpl.getMap("Hole2"), MapImpl.getMap("Hole3")));
			case COURSE_2:
				return new CourseImpl(List.of(MapImpl.getMap("Hole4"), MapImpl.getMap("Hole5"), MapImpl.getMap("Hole6")));
			default:
				return null;
		}
	}
	
	public static CourseImpl getCourse (String courseName) {
		return CourseImpl.getCourse(ECourse.get(courseName));
	}
	
	@Override
	public List<Map> getMaps() {
		return this.maps;
	}

}
