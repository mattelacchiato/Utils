package de.splitstudio.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;
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
		assertThat(dateToTest().get(Calendar.YEAR), is(now.get(Calendar.YEAR)));
	}

	@Test
	public void createFirstDayOfMonth_monthIsSet() {
		assertThat(dateToTest().get(Calendar.MONTH), is(now.get(Calendar.MONTH)));
	}

	@Test
	public void createFirstDayOfMonth_dayIsOne() {
		assertThat(dateToTest().get(Calendar.DAY_OF_MONTH), is(1));
	}

	@Test
	public void createFirstDayOfMonth_OthersAreZero() {
		assertThat(dateToTest().get(Calendar.HOUR_OF_DAY), is(0));
		assertThat(dateToTest().get(Calendar.MINUTE), is(0));
		assertThat(dateToTest().get(Calendar.SECOND), is(0));
		assertThat(dateToTest().get(Calendar.MILLISECOND), is(0));
	}

	private Calendar dateToTest() {
		Date date = DateUtils.createFirstDayOfMonth(LOCALE);
		Calendar actual = Calendar.getInstance(LOCALE);
		actual.setTime(date);
		return actual;
	}
}
