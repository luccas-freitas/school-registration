package br.edu.ifpr.utils.formatter;

public final class CepFormatter {
	public static String formatCep(final String cep) {
		String format = cep.replaceAll("(\\d{5})(\\d{3})", "$1-$2");
		
		return format;
	}
}
