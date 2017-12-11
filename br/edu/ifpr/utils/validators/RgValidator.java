package br.edu.ifpr.utils.validators;

import java.util.regex.Pattern;

public final class RgValidator {
	public static boolean validateRg(final String rg) {
		if(Pattern.matches("(\\d{9})", rg))
			return true;
		
		return false;
	}
}
