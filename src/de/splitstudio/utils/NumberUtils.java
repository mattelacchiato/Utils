package de.splitstudio.utils;

import java.text.NumberFormat;
import java.text.ParseException;

public class NumberUtils {

	private static final double CENT_FACTOR = 100;

	private static NumberFormat currencyFormat;
	private static NumberFormat decimalFormat;

	static {
		init();
	}

	static void init() {
		currencyFormat = NumberFormat.getCurrencyInstance();
		decimalFormat = NumberFormat.getNumberInstance();
		decimalFormat.setMinimumFractionDigits(2);
		decimalFormat.setMaximumFractionDigits(2);
	}

	public static String formatAsDecimal(int cents) {
		return decimalFormat.format(centToDouble(cents));
	}

	public static String formatAsCurrency(int cents) {
		return currencyFormat.format(centToDouble(cents));
	}

	public static double centToDouble(int cents) {
		return cents / CENT_FACTOR;
	}

	public static int parseCent(String amount) throws ParseException {
		return (int) (decimalFormat.parse(amount).doubleValue() * CENT_FACTOR);
	}

}
