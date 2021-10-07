package model;

import java.util.List;

public enum ECourse implements Course {

	MAIN_COURSE(List.of(EMap.PIPPO,
			EMap.STARTING_GROUND));
	
	private final List<Map> maps;
	
	private ECourse(List<Map> maps) {
		this.maps = maps;
	}

	@Override
	public List<Map> getMaps() {
		return this.maps;
	}
}
