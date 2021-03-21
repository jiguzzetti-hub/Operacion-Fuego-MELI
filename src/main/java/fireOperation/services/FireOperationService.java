package fireOperation.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fireOperation.context.SatelliteNames;
import fireOperation.dtos.FireOperationRequest;
import fireOperation.dtos.FireOperationResponse;
import fireOperation.dtos.GetLocationRequest;
import fireOperation.dtos.Position;
import fireOperation.dtos.TopSecretSplitRequest;
import fireOperation.entities.Satellite;
import fireOperation.repositories.SatellitesRepository;
import fireOperation.repositories.SatellitesRepositoryImpl;

@Service
public class FireOperationService {

	@Autowired
	private SatellitesRepository satellitesRepository;
	@Autowired
	private SatellitesRepositoryImpl satellitesRepositoryImpl;

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
		GetLocationRequest locRequest = new GetLocationRequest(request.getSatellites().get(0).getDistance(),
				request.getSatellites().get(1).getDistance(), request.getSatellites().get(2).getDistance());
		response.setPosition(getLocation(locRequest));
		if (response.getPosition() == null) {
			return ResponseEntity.status(404).build();
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
			} else if (i < b.size() && !b.get(i).isEmpty()) {
				d.add(i, b.get(i));
			} else if (i < c.size() && !c.get(i).isEmpty()) {
				d.add(i, c.get(i));
			} else if (a.get(i).isEmpty() && b.get(i).isEmpty() && c.get(i).isEmpty()) {
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

	public Position getLocation(GetLocationRequest request) {
		Position satelliteA = new Position(-500, -200);
		Position satelliteB = new Position(100, -100);
		Position satelliteC = new Position(500, 100);
		float[] P1 = new float[2];
		float[] P2 = new float[2];
		float[] P3 = new float[2];
		double[] ex = new double[2];
		double[] ey = new double[2];
		double[] p3p1 = new double[2];
		double jval = 0;
		double temp = 0;
		double ival = 0;
		double p3p1i = 0;
		double triptx;
		double tripty;
		double xval;
		double yval;
		double t1;
		double t2;
		double t3;
		double t;
		double exx;
		double d;
		double eyy;

		// TRANSALTE POINTS TO VECTORS
		// POINT 1
		P1[0] = satelliteA.getX();
		P1[1] = satelliteA.getY();
		// POINT 2
		P2[0] = satelliteB.getX();
		P2[1] = satelliteB.getY();
		// POINT 3
		P3[0] = satelliteC.getX();
		P3[1] = satelliteC.getY();

		// TRANSFORM THE METERS VALUE FOR THE MAP UNIT
		// DISTANCE BETWEEN POINT 1 AND MY LOCATION
		// distanceA = (distanceA / 100000);
		// DISTANCE BETWEEN POINT 2 AND MY LOCATION
		// distanceB = (distanceB / 100000);
		// DISTANCE BETWEEN POINT 3 AND MY LOCATION
		// distanceC = (distanceC / 100000);

		for (int i = 0; i < P1.length; i++) {
			t1 = P2[i];
			t2 = P1[i];
			t = t1 - t2;
			temp += (t * t);
		}

		d = Math.sqrt(temp);

		for (int i = 0; i < P1.length; i++) {
			t1 = P2[i];
			t2 = P1[i];
			exx = (t1 - t2) / (Math.sqrt(temp));
			ex[i] = exx;
		}

		for (int i = 0; i < P3.length; i++) {
			t1 = P3[i];
			t2 = P1[i];
			t3 = t1 - t2;
			p3p1[i] = (float) t3;
		}

		for (int i = 0; i < ex.length; i++) {
			t1 = ex[i];
			t2 = p3p1[i];
			ival += (t1 * t2);
		}

		for (int i = 0; i < P3.length; i++) {
			t1 = P3[i];
			t2 = P1[i];
			t3 = ex[i] * ival;
			t = t1 - t2 - t3;
			p3p1i += (t * t);
		}

		for (int i = 0; i < P3.length; i++) {
			t1 = P3[i];
			t2 = P1[i];
			t3 = ex[i] * ival;
			eyy = (t1 - t2 - t3) / Math.sqrt(p3p1i);
			ey[i] = eyy;
		}

		for (int i = 0; i < ey.length; i++) {
			t1 = ey[i];
			t2 = p3p1[i];
			jval += (t1 * t2);
		}

		xval = (Math.pow(request.getDistanceA(), 2) - Math.pow(request.getDistanceB(), 2) + Math.pow(d, 2)) / (2 * d);
		yval = ((Math.pow(request.getDistanceA(), 2) - Math.pow(request.getDistanceC(), 2) + Math.pow(ival, 2)
				+ Math.pow(jval, 2)) / (2 * jval)) - ((ival / jval) * xval);

		t1 = satelliteA.getX();
		t2 = ex[0] * xval;
		t3 = ey[0] * yval;
		triptx = (t1 + t2 + t3);

		t1 = satelliteA.getX();
		t2 = ex[1] * xval;
		t3 = ey[1] * yval;
		tripty = (t1 + t2 + t3);

		return new Position(triptx, tripty);

	}

	public ResponseEntity<?> setMessageAndLocationOfEachSatellite(TopSecretSplitRequest request, String satelliteName) {
		// Satellite satellite = new Satellite(satelliteName, request.getDistance(),
		// request.getMessage());

		satellitesRepository
				.save(new fireOperation.entities.Satellite(request.getDistance(), request.getMessage(), satelliteName));

		return null;
	}

	public ResponseEntity<?> getMessageAndLocationSplit() {
		FireOperationResponse response = new FireOperationResponse();
		List<fireOperation.entities.Satellite> satellites = satellitesRepositoryImpl
				.findOrderedByInsertionDateLimitedTo(3);
		if (!ValidationService.checkMinimunOfThreeInsert(satellites)) {
			return ResponseEntity.badRequest().body("No se ha podido procesar la consulta por falta de información");
		}
		response.setPosition(getLocation(getDistancesOrdered(satellites)));
		response.setMessage(getMessage(satellites.get(0).getMessage(), satellites.get(1).getMessage(),
				satellites.get(2).getMessage()));
		return ResponseEntity.ok(response);
	}

	private GetLocationRequest getDistancesOrdered(List<Satellite> satellites) {
		GetLocationRequest distances = new GetLocationRequest();
		for (Satellite satellite : satellites) {
			if (satellite.getName().equals(SatelliteNames.KENOBI.toString())) {
				distances.setDistanceA(satellite.getDistance());
			} else if (satellite.getName().equals(SatelliteNames.SKYWALKER.toString())) {
				distances.setDistanceB(satellite.getDistance());
			} else if (satellite.getName().equals(SatelliteNames.SATO.toString())) {
				distances.setDistanceC(satellite.getDistance());
			}

		}
		return distances;
	}

}
