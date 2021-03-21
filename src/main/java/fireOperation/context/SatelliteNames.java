package fireOperation.context;

public enum SatelliteNames {
	
	KENOBI("1"),
	SKYWALKER("2"),
	SATO("3");
	
	private String satelliteNames;

	
 
	SatelliteNames(String order) {
		this.satelliteNames = order;
	}

	public String getSatelliteNames() {
		return satelliteNames;
	}

	
	
	

}
