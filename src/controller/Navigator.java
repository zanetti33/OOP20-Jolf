package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Navigator {
	
	Map<String,Integer> getLeaderboard() throws IOException;
	
	void writeOnLeaderboard(String player, int score) throws IOException;
	
	String getStarImage();
	
	List<String> getMaps();
	
	List<String> getCourses();

}
