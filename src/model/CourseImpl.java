package model;
import java.util.List;

public class CourseImpl implements Course {

	private final List<Map> maps;
	
	private CourseImpl(List<Map> maps) {
		this.maps = maps;
	}
	
	public static CourseImpl getCourse(ECourse course) {
		switch(course) {
			case MAIN_COURSE:
				return new CourseImpl(List.of(MapImpl.getMap("Pippo")));
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
