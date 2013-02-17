package de.splitstudio.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtils {

	public static float centsToDecimal(int cents) {
		return cents / 100f;
	}

	public static String formatAsCurrency(int cents, Locale locale) {
		return NumberFormat.getCurrencyInstance(locale).format(centsToDecimal(cents));
	}

}
