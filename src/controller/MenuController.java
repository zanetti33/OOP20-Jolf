package controller;

import java.util.List;
import java.util.Map;

import model.Course;

public interface MenuController {

	List<String> getLeaderboard();
	
	void updateLeaderboard(String name, int score);
	
	List<Map> getMaps();
	
	List<Course> getCourses();
	
}
