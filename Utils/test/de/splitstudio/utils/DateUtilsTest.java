package de.splitstudio.utils;

import static de.splitstudio.utils.DateUtils.createFirstDayOfMonth;
import static de.splitstudio.utils.DateUtils.createFirstDayOfYear;
import static de.splitstudio.utils.DateUtils.createLastDayOfMonth;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DateUtilsTest {

	private Calendar now;

	@Before
	public void setUp() {
		now = Calendar.getInstance();
	}

	@Test
	public void createFirstDayOfMonth_yearIsSet() {
		assertThat(createFirstDayOfMonth().get(YEAR), is(now.get(YEAR)));
	}

	@Test
	public void createFirstDayOfMonth_monthIsSet() {
		assertThat(createFirstDayOfMonth().get(MONTH), is(now.get(MONTH)));
	}

	@Test
	public void createFirstDayOfMonth_dayIsOne() {
		assertThat(createFirstDayOfMonth().get(DAY_OF_MONTH), is(1));
	}

	@Test
	public void createFirstDayOfMonth_OthersAreZero() {
		assertThat(createFirstDayOfMonth().get(HOUR_OF_DAY), is(0));
		assertThat(createFirstDayOfMonth().get(MINUTE), is(0));
		assertThat(createFirstDayOfMonth().get(SECOND), is(0));
		assertThat(createFirstDayOfMonth().get(MILLISECOND), is(0));
	}

	@Test
	public void createFirstDayOfYear_yearIsSet() {
		assertThat(DateUtils.createFirstDayOfYear().get(YEAR), is(now.get(YEAR)));
	}

	@Test
	public void createFirstDayOfYear_OthersAreZero() {
		assertThat(createFirstDayOfYear().get(MONTH), is(0));
		assertThat(createFirstDayOfYear().get(DAY_OF_MONTH), is(1));
		assertThat(createFirstDayOfYear().get(HOUR_OF_DAY), is(0));
		assertThat(createFirstDayOfYear().get(MINUTE), is(0));
		assertThat(createFirstDayOfYear().get(SECOND), is(0));
		assertThat(createFirstDayOfYear().get(MILLISECOND), is(0));
	}

	@Test
	public void isBetween_isBetween_true() {
		Calendar cal = Calendar.getInstance();
		Date start = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date now = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date end = cal.getTime();
		assertThat(DateUtils.isBetween(start, now, end), is(true));
	}

	@Test
	public void isBetween_startNull_true() {
		Calendar cal = Calendar.getInstance();
		Date start = null;
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date now = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date end = cal.getTime();
		assertThat(DateUtils.isBetween(start, now, end), is(true));
	}

	@Test
	public void isBetween_endNull_true() {
		Calendar cal = Calendar.getInstance();
		Date start = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date now = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date end = null;
		assertThat(DateUtils.isBetween(start, now, end), is(true));
	}

	@Test
	public void isBetween_StartAndEndNull_true() {
		Calendar cal = Calendar.getInstance();
		Date start = null;
		Date now = cal.getTime();
		Date end = null;
		assertThat(DateUtils.isBetween(start, now, end), is(true));
	}

	@Test
	public void isBetween_nowEqualsStart_true() {
		Calendar cal = Calendar.getInstance();
		Date start = cal.getTime();
		Date now = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date end = cal.getTime();
		assertThat(DateUtils.isBetween(start, now, end), is(true));
	}

	@Test
	public void isBetween_nowEqualsEnd_true() {
		Calendar cal = Calendar.getInstance();
		Date start = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date now = cal.getTime();
		Date end = cal.getTime();
		assertThat(DateUtils.isBetween(start, now, end), is(true));
	}

	@Test
	public void isBetween_nowEqualsStartAndEnd_true() {
		Calendar cal = Calendar.getInstance();
		Date start = cal.getTime();
		Date now = cal.getTime();
		Date end = cal.getTime();
		assertThat(DateUtils.isBetween(start, now, end), is(true));
	}

	@Test
	public void isBetween_nowBeforeStart_false() {
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date start = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date end = cal.getTime();
		assertThat(DateUtils.isBetween(start, now, end), is(false));
	}

	@Test
	public void isBetween_nowAfterEnd_false() {
		Calendar cal = Calendar.getInstance();
		Date start = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date end = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date now = cal.getTime();
		assertThat(DateUtils.isBetween(start, now, end), is(false));
	}

	@Test
	public void createLastDayOfMonth_plusOneMilli_equalsFirstDayOfNextMonth() throws Exception {
		Calendar lastDay = createLastDayOfMonth();
		lastDay.add(Calendar.MILLISECOND, 1);
		Calendar firstDay = createFirstDayOfMonth();
		firstDay.add(MONTH, 1);
		assertThat(lastDay.getTime(), equalTo(firstDay.getTime()));
	}

}
