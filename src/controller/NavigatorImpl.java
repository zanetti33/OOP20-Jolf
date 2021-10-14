package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavigatorImpl implements Navigator {
	
	public static final String SEP = File.separator;
	public static final String FILE_NAME = System.getProperty("user.home") + SEP + "Desktop"
			+ SEP + "workspace" + SEP + "Jolf" + SEP + "leaderboard.txt";

	@Override
	public Map<String,Integer> getLeaderboard() throws IOException {
		Map<String,Integer> leaderboard = new HashMap<>();
		String name = "";
		int score;
		try (final BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
			String s = reader.readLine();
			//TODO: find a better way to work with strings
			while (!s.isEmpty()) {
				String[] line = s.split(" ");
				int n = line.length;
				score = Integer.parseInt(line[n]);
				for (int i=0; i<n-1; i++) {
					name = name + line[i] + " ";
				}
				name = name.substring(0, name.length() - 1);
				leaderboard.put(name, score);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return leaderboard;
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
