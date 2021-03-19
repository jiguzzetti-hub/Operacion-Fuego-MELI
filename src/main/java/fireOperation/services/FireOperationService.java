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
			return ResponseEntity.status(403).build();
		}
		response.setMessage(getMessage(request.getSatellites().get(0).getMessage(),
				request.getSatellites().get(1).getMessage(), request.getSatellites().get(2).getMessage()));
		if (response.getMessage() == null) {
			return ResponseEntity.status(405).build();
		}
		response.setPosition(getLocation(request.getSatellites().get(0).getDistance(),
				request.getSatellites().get(1).getDistance(), request.getSatellites().get(2).getDistance()));
		if (response.getPosition() == null) {
			return ResponseEntity.status(406).build();
		}

		return ResponseEntity.ok(response);
	}

	public String getMessage(ArrayList<String> a, ArrayList<String> b, ArrayList<String> c) {
		String message = null;
		ArrayList<String> d = new ArrayList<String>();
		int maxSize = getMaxSizeOfArrays(a, b, c);
		int i = 0;
		while (i < maxSize) {
			if (i < a.size() && !a.get(i).isEmpty()) {
				d.add(a.get(i));
			} 
			else if (i < b.size() && !b.get(i).isEmpty()) {
					d.add(i, b.get(i));
				}
			else if (i < c.size() && !c.get(i).isEmpty()) {
				d.add(i, c.get(i));
			}
			else if (a.get(i).isEmpty() && b.get(i).isEmpty() && c.get(i).isEmpty()){
				return null;
			}
			i++;
			message = String.join(" ", d);
			
		}
		if (d.size() == maxSize) {
			return message;
			
		}
		return null;
		
	}

	private int getMaxSizeOfArrays(ArrayList<String> a, ArrayList<String> b, ArrayList<String> c) {
		int maxSize = a.size();
		if (maxSize < b.size()) {
			maxSize = b.size();

		}
		if (maxSize < c.size()) {
			maxSize = c.size();
		}
		
		return maxSize;
	}

	public Position getLocation(float distanceA, float distanceB, float distanceC) {
		Position position = new Position(1, 2);

		return position;

	}
}
