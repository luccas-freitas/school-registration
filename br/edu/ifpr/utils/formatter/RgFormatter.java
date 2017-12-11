package br.edu.ifpr.utils.formatter;

public final class RgFormatter {
	public static String formatRg(String rg) {	
		String format = rg.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{1})", "$1.$2.$3-$4");
		return format;
	}
}
