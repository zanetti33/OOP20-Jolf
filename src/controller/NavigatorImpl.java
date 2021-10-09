package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class NavigatorImpl implements Navigator {
	
	public static final String SEP = File.separator;
	public static final String FILE_NAME = System.getProperty("user.home") + SEP + "Desktop"
			+ SEP + "workspace" + SEP + "Jolf" + SEP + "leaderboard.txt";

	@Override
	public List<String> getLeaderboard() throws IOException {
		try (final BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
			System.out.println(reader.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return List.of();
	}

	@Override
	public List<String> getMaps() {
		return List.of();
	}

	@Override
	public List<String> getCourses() {
		return List.of();
	}

}
