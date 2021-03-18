package fireOperation.services;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class StringUtil {
	// Valido que cada palabra del mensaje sea String, considero letras y números
	// solamente porque no aclara ningún caracter especial a considerar
	private static final String GENDER_REGEX = "^[a-zA-Z]*$";

	public static boolean validateString(ArrayList<String> messageList) {
		for (String word : messageList) {
			if (!containsOnlyStrings(word)) {
				return false;
			}
			

		}
		return true;
	}

	private static boolean containsOnlyStrings(String gender) {
		return Pattern.matches(GENDER_REGEX, gender);

	}

}
