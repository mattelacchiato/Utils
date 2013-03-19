package de.splitstudio.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static Date createFirstDayOfMonth(Locale locale) {
		Calendar cal = Calendar.getInstance(locale);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
