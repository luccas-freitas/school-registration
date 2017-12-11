package br.edu.ifpr.utils.validators;

import java.util.regex.Pattern;

public final class TelefoneValidator {
	public static boolean validateDdd(final String ddd) {
		if(ddd.length() == 2)
			return true;
		
		return false;
	}
	
	public static boolean validateNumero(final String numero) {
		if(numero != null && (numero.length() == 8 || numero.length() == 9) && Pattern.matches("([0-9])?([0-9]{8})", numero))
			return true;
		
		return false;
	}
	
	public static boolean validate(final String ddd, final String numero) {
		return validateDdd(ddd) && validateNumero(numero);
	}
}
