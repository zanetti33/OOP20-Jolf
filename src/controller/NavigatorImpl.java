package controller;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import model.Ball;
import model.ECourse;
import model.EMap;

public class NavigatorImpl implements Navigator {
	
	private static final String SEP = File.separator;
	private static final String FILE_LEADERBOARD = System.getProperty("user.dir") + SEP + "leaderboard.txt";
	private static final String FILE_STAR = System.getProperty("user.dir") + SEP + "star.png";
	private static final String[] BALL_COLORS = {"Black", "White", "Blue", "Orange", "Red", "Yellow", "Gray", "Green"};

	@Override
	public Map<String,Integer> getLeaderboard() throws IOException {
		Map<String,Integer> leaderboard = new HashMap<>();
		
		try (final BufferedReader reader = new BufferedReader(new FileReader(FILE_LEADERBOARD))){
			Optional<String> s = Optional.ofNullable(reader.readLine());
			while (s.isPresent() && !s.get().isBlank()) {
				String[] line = s.get().split("=");
				String name = line[0];
				int score = Integer.parseInt(line[1]);
				if (leaderboard.containsKey(name)) {
					if (score < leaderboard.get(name)) {
						leaderboard.put(name, score);
					}
				} else {
					leaderboard.put(name, score);
				}
				s = Optional.ofNullable(reader.readLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return sortByValue(leaderboard);
	}
	
	@Override
	public void writeOnLeaderboard(String player, int score) throws IOException {
		try (final BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_LEADERBOARD, true))) {
			String line = player + "=" + score;
			writer.append(line);
			writer.newLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void resetLeaderboard() throws IOException {
		try (final BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_LEADERBOARD))) {
			writer.newLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void changeBallColor(String color) {
		switch(color) {
			case "Black":
				Ball.setColor(Color.BLACK);
				break;
			case "White":
				Ball.setColor(Color.WHITE);
				break;
			case "Blue":
				Ball.setColor(Color.BLUE);
				break;
			case "Orange":
				Ball.setColor(Color.ORANGE);
				break;
			case "Red":
				Ball.setColor(Color.RED);
				break;
			case "Yellow":
				Ball.setColor(Color.YELLOW);
				break;
			case "Gray":
				Ball.setColor(Color.GRAY);
				break;
			case "Green":
				Ball.setColor(Color.GREEN);
				break;
			default:
				Ball.setColor(Color.WHITE);
		}		
	}
	
	@Override
	public String getStarImage() {
		return FILE_STAR;
	}

	@Override
	public List<String> getMaps() {
		final List<String> names = new ArrayList<String>();
		for (EMap map : EMap.values()) {
			names.add(map.getName());
		}
		return names;
	}

	@Override
	public List<String> getCourses() {
		final List<String> names = new ArrayList<String>();
		for (ECourse course : ECourse.values()) {
			names.add(course.getName());
		}
		return names;
	}
	
	public String[] getBallColors() {
		return BALL_COLORS;
	}
	
	private <K, V extends Comparable<? super V>> Map<K,V> sortByValue(Map<K,V> map) {
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
		return result;
	}

}
