package fireOperation.services;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fireOperation.dtos.FireOperationRequest;
import fireOperation.dtos.FireOperationResponse;
import fireOperation.dtos.Position;

@Service
public class FireOperationService {

	public ResponseEntity<?> getMessageAndLocation(FireOperationRequest request) {
		FireOperationResponse response = new FireOperationResponse();
		if ((!ValidationService.validateMessages(request.getSatellites())
				|| (!ValidationService.validateDistances(request.getSatellites())))) {
			return ResponseEntity.status(404).build();
		}
		response.setMessage(getMessage(request.getSatellites().get(0).getMessage(),
				request.getSatellites().get(1).getMessage(), request.getSatellites().get(2).getMessage()));
		if (response.getMessage() == null) {
			return ResponseEntity.status(404).build();
		}
		response.setPosition(getLocation(request.getSatellites().get(0).getDistance(),
				request.getSatellites().get(1).getDistance(), request.getSatellites().get(2).getDistance()));
		if (response.getPosition() == null) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.ok(response);
	}

	public String getMessage(ArrayList<String> a, ArrayList<String> b, ArrayList<String> c) {
		String message = null;

		return message;
	}

	public Position getLocation(float distanceA, float distanceB, float distanceC) {
		Position position = null;

		return position;

	}
}
