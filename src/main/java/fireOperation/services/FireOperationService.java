package fireOperation.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fireOperation.dtos.FireOperationRequest;
import fireOperation.dtos.FireOperationResponse;

@Service
public class FireOperationService {
	
	public ResponseEntity<?> getMessageAndLocation (FireOperationRequest request){
		FireOperationResponse response = new FireOperationResponse();
		
		return null;
	}

}
