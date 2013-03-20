package de.splitstudio.utils;

import static de.splitstudio.utils.DateUtils.createFirstDayOfMonth;
import static de.splitstudio.utils.DateUtils.createFirstDayOfYear;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class DateUtilsTest {

	private static final Locale LOCALE = Locale.GERMANY;
	private Calendar now;

	@Before
	public void setUp() {
		now = Calendar.getInstance(LOCALE);
	}

	@Test
	public void createFirstDayOfMonth_yearIsSet() {
		assertThat(createFirstDayOfMonth(LOCALE).get(YEAR), is(now.get(YEAR)));
	}

	@Test
	public void createFirstDayOfMonth_monthIsSet() {
		assertThat(createFirstDayOfMonth(LOCALE).get(MONTH), is(now.get(MONTH)));
	}

	@Test
	public void createFirstDayOfMonth_dayIsOne() {
		assertThat(createFirstDayOfMonth(LOCALE).get(DAY_OF_MONTH), is(1));
	}

	@Test
	public void createFirstDayOfMonth_OthersAreZero() {
		assertThat(createFirstDayOfMonth(LOCALE).get(HOUR_OF_DAY), is(0));
		assertThat(createFirstDayOfMonth(LOCALE).get(MINUTE), is(0));
		assertThat(createFirstDayOfMonth(LOCALE).get(SECOND), is(0));
		assertThat(createFirstDayOfMonth(LOCALE).get(MILLISECOND), is(0));
	}

	@Test
	public void createFirstDayOfYear_yearIsSet() {
		assertThat(DateUtils.createFirstDayOfYear(LOCALE).get(YEAR), is(now.get(YEAR)));
	}

	@Test
	public void createFirstDayOfYear_OthersAreZero() {
		assertThat(createFirstDayOfYear(LOCALE).get(MONTH), is(0));
		assertThat(createFirstDayOfYear(LOCALE).get(DAY_OF_MONTH), is(1));
		assertThat(createFirstDayOfYear(LOCALE).get(HOUR_OF_DAY), is(0));
		assertThat(createFirstDayOfYear(LOCALE).get(MINUTE), is(0));
		assertThat(createFirstDayOfYear(LOCALE).get(SECOND), is(0));
		assertThat(createFirstDayOfYear(LOCALE).get(MILLISECOND), is(0));
	}

}
