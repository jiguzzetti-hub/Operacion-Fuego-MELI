package fireOperation.dtos;

public class FireOperationResponse {

	private Position position;
	private String message;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FireOperationResponse(Position position, String message) {
		super();
		this.position = position;
		this.message = message;
	}

	public FireOperationResponse() {

	}

}
