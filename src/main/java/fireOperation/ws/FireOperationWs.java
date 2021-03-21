package fireOperation.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fireOperation.dtos.FireOperationRequest;
import fireOperation.dtos.FireOperationResponse;
import fireOperation.dtos.TopSecretSplitRequest;
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

	public ResponseEntity<?> topSecret(@RequestBody final FireOperationRequest request) {

		return fireOperationService.getMessageAndLocation(request);
	}

	@PostMapping(path = "/topsecret_split/{satelliteName}")
	@ApiOperation(value = "Recepción del mensaje y la posición de cada satélite")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = FireOperationResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Error en petición") })

	public ResponseEntity<?> postTopSecretSplit(@RequestBody final TopSecretSplitRequest request,
			@PathVariable String satelliteName) {

		return fireOperationService.setMessageAndLocationOfEachSatellite(request, satelliteName);
	}

	@GetMapping(path = "/topsecret_split")
	@ApiOperation(value = "Decodificación del mensaje enviado y localización de la fuente emisora")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = FireOperationResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Error en petición") })

	public ResponseEntity<?> getTopSecretSplit() throws NumberFormatException, Exception {

		return fireOperationService.getMessageAndLocationSplit();
	}

}
