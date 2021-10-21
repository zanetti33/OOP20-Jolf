package model;
public enum EMap {

	TEST("Test"), HOLE_1("Hole1"), HOLE_2("Hole2");

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