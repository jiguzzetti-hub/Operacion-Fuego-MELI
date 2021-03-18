package fireOperation.dtos;

import java.util.ArrayList;

public class Satellite {

	private String name;
	private float distance;
	private ArrayList<String> message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Satellite(String name, float distance, ArrayList<String> message) {
		super();
		this.name = name;
		this.distance = distance;
		this.message = message;
	}

	public Satellite() {

	}

}
