package fireOperation.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fireOperation.dtos.FireOperationRequest;
import fireOperation.dtos.FireOperationResponse;
import fireOperation.services.FireOperationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/fireOperation/")
public class FireOperationWs {
	
	@Autowired
	private FireOperationService fireOperationService;
	
	
	@PostMapping(path = "/topsecret")
	@ApiOperation(value = "Decodificación del mensaje enviado y localización de la fuente emisora")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = FireOperationResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Error en petición") })
	
	public ResponseEntity<?> registerNewCardUser(@RequestBody final FireOperationRequest request) throws NumberFormatException, Exception {
		
		return fireOperationService.getMessageAndLocation(request);
	}
	
	

}
