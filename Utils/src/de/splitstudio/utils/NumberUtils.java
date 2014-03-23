package de.splitstudio.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

public class NumberUtils {

	private static final double CENT_FACTOR = 100;
	private static final BigDecimal CENT_FACTOR_BD = new BigDecimal(CENT_FACTOR);

	private static NumberFormat currencyFormat;
	private static NumberFormat currencyIntegerFormat;
	private static NumberFormat decimalFormat;

	static {
		init();
	}

	static void init() {
		currencyFormat = NumberFormat.getCurrencyInstance();
		currencyIntegerFormat = NumberFormat.getCurrencyInstance();
		currencyIntegerFormat.setMaximumFractionDigits(0);
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

	public static String formatAsIntegerCurrency(int cents) {
		return currencyIntegerFormat.format(centToDouble(cents));
	}

	public static double centToDouble(int cents) {
		return cents / CENT_FACTOR;
	}

	public static int parseCent(String amount) throws ParseException {
		Number parsed = decimalFormat.parse(amount);
		BigDecimal currencyValue = new BigDecimal(String.valueOf(parsed));
		return currencyValue.multiply(CENT_FACTOR_BD).intValue();
	}

}
