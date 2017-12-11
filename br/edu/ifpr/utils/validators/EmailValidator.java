package br.edu.ifpr.utils.validators;

import java.util.regex.Pattern;

public final class EmailValidator {
	public static boolean validateEmail(final String email) {
		if(Pattern.matches("[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,4}", email))
			return true;
		
		return false;
	}
}
