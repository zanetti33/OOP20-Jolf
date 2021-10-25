package model;
import java.util.List;

/**
 * a generic instance of a Jolf course
 * @author loren
 *
 */
public class CourseImpl implements Course {

	private final List<Map> maps;
	
	/**
	 * @param maps
	 */
	private CourseImpl(List<Map> maps) {
		this.maps = maps;
	}
	
	/**
	 * @param course
	 * @return an instance of this course
	 */
	public static CourseImpl getCourse(ECourse course) {
		switch(course) {
			case COURSE_1:
				return new CourseImpl(List.of(MapImpl.getMap("Hole 1"), MapImpl.getMap("Hole 2"), MapImpl.getMap("Hole 3")));
			case COURSE_2:
				return new CourseImpl(List.of(MapImpl.getMap("Hole 4"), MapImpl.getMap("Hole 5"), MapImpl.getMap("Hole 6")));
			default:
				return null;
		}
	}
	
	/**
	 * @param courseName
	 * @return an instance of the course with the name courseName
	 */
	public static CourseImpl getCourse (String courseName) {
		return CourseImpl.getCourse(ECourse.get(courseName));
	}
	
	@Override
	public List<Map> getMaps() {
		return this.maps;
	}

}
