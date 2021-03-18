package fireOperation.services;

import java.util.ArrayList;

import fireOperation.dtos.Satellite;

public class ValidationService {
	
	//Recorro satélites y paso lista de palabras en cada mensaje a validar si son String
	public static boolean validateMessages(ArrayList<Satellite> satellites) {
		for (Satellite satellite : satellites) {

			if (!StringUtil.validateString(satellite.getMessage())) {
				return false;

			}
		}
		return true;
	}
	
	public static boolean validateDistances (ArrayList<Satellite> satellites) {
		for (Satellite satellite : satellites) {
			if (!Float.isFinite(satellite.getDistance())) {
				System.out.println(satellite.getDistance());
				return false;
			}
		}
		return true;
	}
	

}
