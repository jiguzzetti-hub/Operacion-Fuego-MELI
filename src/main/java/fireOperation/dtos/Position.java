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

	public Position(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Position() {
		super();
	}

}
