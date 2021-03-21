package fireOperation.dtos;

public class GetLocationRequest {

	private float distanceA;
	private float distanceB;
	private float distanceC;

	public float getDistanceA() {
		return distanceA;
	}

	public void setDistanceA(float distanceA) {
		this.distanceA = distanceA;
	}

	public float getDistanceB() {
		return distanceB;
	}

	public void setDistanceB(float distanceB) {
		this.distanceB = distanceB;
	}

	public float getDistanceC() {
		return distanceC;
	}

	public void setDistanceC(float distanceC) {
		this.distanceC = distanceC;
	}

	public GetLocationRequest(float distanceA, float distanceB, float distanceC) {
		super();
		this.distanceA = distanceA;
		this.distanceB = distanceB;
		this.distanceC = distanceC;
	}

	public GetLocationRequest() {
	}

}
