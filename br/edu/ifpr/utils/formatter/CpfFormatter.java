package br.edu.ifpr.utils.formatter;

public final class CpfFormatter {
	public static String formatCpf(String cpf) {	
		String format = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
		return format;
	}
}
