package fireOperation.dtos;

import java.util.ArrayList;

public class FireOperationRequest {

	private ArrayList<Satellite> satellites;

	public ArrayList<Satellite> getSatellites() {
		return satellites;
	}

	public void setSatellites(ArrayList<Satellite> satellites) {
		this.satellites = satellites;
	}

	public FireOperationRequest(ArrayList<Satellite> satellites) {
		super();
		this.satellites = satellites;
	}

	public FireOperationRequest() {

	}

}
