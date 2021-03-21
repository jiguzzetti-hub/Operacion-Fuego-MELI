package fireOperation.services;

import java.util.ArrayList;
import java.util.List;

import fireOperation.dtos.Satellite;

public class ValidationService {

	// Recorro satélites y paso lista de palabras en cada mensaje a validar si son
	// String
	public static boolean validateMessages(ArrayList<Satellite> satellites) {
		for (Satellite satellite : satellites) {

			if (!StringUtil.validateString(satellite.getMessage())) {
				return false;

			}

		}
		return true;
	}

	public static boolean validateDistances(ArrayList<Satellite> satellites) {
		for (Satellite satellite : satellites) {
			if (!Float.isFinite(satellite.getDistance())) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkMinimunOfThreeInsert(List<fireOperation.entities.Satellite> satellites) {
		if (satellites.size() < 3) {
			return false;
		}

		return checkSatellitesAreNotTheSame(satellites);
	}

	private static boolean checkSatellitesAreNotTheSame(List<fireOperation.entities.Satellite> satellites) {
		if ((satellites.get(0).getName().equals(satellites.get(1).getName()))
				|| (satellites.get(1).getName().equals(satellites.get(2).getName()))) {
			return false;
		}
		return true;
	}

}
