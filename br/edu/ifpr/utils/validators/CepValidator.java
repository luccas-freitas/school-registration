package br.edu.ifpr.utils.validators;

import java.util.regex.Pattern;

public final class CepValidator {
	public static boolean validateCep(final String cep) {
		if(Pattern.matches("(\\d{8})", cep))
			return true;
		
		return false;
	}
}
