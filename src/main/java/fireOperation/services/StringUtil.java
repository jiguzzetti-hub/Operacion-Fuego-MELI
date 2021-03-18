package fireOperation.services;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class StringUtil {
	// Valido que cada palabra del mensaje sea String, considero letras y números
	// solamente porque no aclara ningún caracter especial a considerar
	private static final String STRING_REGEX = "[a-zA-Z0-9]*$";

	public static boolean validateString(ArrayList<String> messageList) {
		for (String word : messageList) {
			if (!containsOnlyStrings(word)) {
				System.out.println(word);
				return false;
			}
			
		}
		return true;
	}

	private static boolean containsOnlyStrings(String word) {
		return Pattern.matches(STRING_REGEX, word);

	}

}
