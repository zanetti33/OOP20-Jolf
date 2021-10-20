package model;
public enum EMap {

	STARTING_GROUND("Starting Ground"),
	PIPPO("Pippo");

	private final String name;
	
	private EMap(String name) {
		this.name = name;
	}
	
	public static EMap get(String mapName) {
		EMap result = null;
		for (EMap map : EMap.values()) {
			if (map.getName().equals(mapName)) {
				result = map;
			}
		}
		return result;
	}

	public String getName() {
		return this.name;
	}

}