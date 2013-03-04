package de.splitstudio.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberUtils {

	private static final double CENT_FACTOR = 100;

	public static String formatAsCurrency(int cents, Locale locale) {
		return NumberFormat.getCurrencyInstance(locale).format(centToDouble(cents));
	}

	private static double centToDouble(int cents) {
		return cents / CENT_FACTOR;
	}

	public static int parseCent(String amount, Locale locale) throws ParseException {
		return (int) (NumberFormat.getNumberInstance(locale).parse(amount).doubleValue() * CENT_FACTOR);
	}

}
