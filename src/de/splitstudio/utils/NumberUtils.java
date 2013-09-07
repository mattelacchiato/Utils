package de.splitstudio.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberUtils {

	private static final double CENT_FACTOR = 100;

	private static final NumberFormat decimalFormat = NumberFormat.getNumberInstance();
	static {
		decimalFormat.setMinimumFractionDigits(2);
		decimalFormat.setMaximumFractionDigits(2);
	}

	//TODO (07.09.2013): put numberinstance as static field

	public static String formatAsDecimal(int cents) {
		return decimalFormat.format(centToDouble(cents));
	}

	public static String formatAsCurrency(int cents, Locale locale) {
		return NumberFormat.getCurrencyInstance(locale).format(centToDouble(cents));
	}

	public static String formatAsCurrency(int cents) {
		return formatAsCurrency(cents, Locale.getDefault());
	}

	public static double centToDouble(int cents) {
		return cents / CENT_FACTOR;
	}

	public static int parseCent(String amount) throws ParseException {
		return parseCent(amount, Locale.getDefault());
	}

	//TODO 02.06.2013: use qualifiers to test locale specific stuff!
	//@Test @Values(qualifiers = "fr-land")

	public static int parseCent(String amount, Locale locale) throws ParseException {
		return (int) (NumberFormat.getNumberInstance(locale).parse(amount).doubleValue() * CENT_FACTOR);
	}

}
