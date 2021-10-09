package controller;

import java.io.IOException;
import java.util.List;

public interface Navigator {
	
	List<String> getLeaderboard() throws IOException;
	
	List<String> getMaps();
	
	List<String> getCourses();

}
