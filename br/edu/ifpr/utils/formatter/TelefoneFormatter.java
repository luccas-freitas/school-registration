package br.edu.ifpr.utils.formatter;

public final class TelefoneFormatter {
	public static String formatDdd(final String ddd) {
		return formatDdd(ddd, Boolean.FALSE);
	}
	
	public static String formatDdd(final String ddd, final Boolean appendZero) {
		if(appendZero)
			return "0" + ddd;
		return ddd;
	}
	
	public static String formatNumero(final String numero) {
		int p = (numero.length() == 8)? 4:5;
		StringBuffer sb = new StringBuffer();
		sb.append(numero.substring(0, p)).append('-').append(numero.substring(p, numero.length()));
		
		if(p == 5)
			sb.insert(1, ' ');
		
		return sb.toString();
	}
	
	public static String formatTelefone(final String ddd, final String numeroFone) {
		return formatTelefone(ddd, Boolean.FALSE, numeroFone);
	}
	
	public static String formatTelefone(final String ddd, final Boolean appendZero, final String numeroFone) {
		StringBuilder sb = new StringBuilder();
		sb.append(ddd).append(' ').append(numeroFone);

		if(appendZero)
			sb.insert(0,'0');
		
		return sb.toString();
	}
}
