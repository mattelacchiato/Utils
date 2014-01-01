package de.splitstudio.utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static boolean isBetween(Date start, Date now, Date end) {
		return (start == null || !start.after(now)) && (end == null || !end.before(now));
	}

	public static Calendar createFirstDayOfYear() {
		Calendar cal = createFirstDayOfMonth();
		cal.set(Calendar.MONTH, 0);
		return cal;
	}

	public static Calendar createNullifiedCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 0);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Calendar createFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Calendar createLastDayOfMonth() {
		Calendar cal = createFirstDayOfMonth();
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.MILLISECOND, -1);
		return cal;
	}

	public static String formatAsShortDate(Date date) {
		return DateFormat.getDateInstance(DateFormat.SHORT).format(date);
	}

	public static String formatAsLongDate(Date date) {
		return DateFormat.getDateInstance(DateFormat.LONG).format(date);
	}

}
