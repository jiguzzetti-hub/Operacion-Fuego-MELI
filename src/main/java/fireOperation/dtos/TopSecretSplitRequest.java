package fireOperation.dtos;

import java.util.ArrayList;

public class TopSecretSplitRequest {

	private float distance;
	private ArrayList<String> message;

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public ArrayList<String> getMessage() {
		return message;
	}

	public void setMessage(ArrayList<String> message) {
		this.message = message;
	}

	public TopSecretSplitRequest(float distance, ArrayList<String> message) {
		super();
		this.distance = distance;
		this.message = message;
	}

	public TopSecretSplitRequest() {
		super();
	}

}
