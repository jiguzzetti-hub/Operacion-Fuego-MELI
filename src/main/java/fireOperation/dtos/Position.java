package fireOperation.dtos;

public class Position {

	private float x;
	private float y;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Position(double triptx, double tripty) {
		super();
		this.x = (float) triptx;
		this.y = (float) tripty;
	}

	public Position() {
		super();
	}

}
