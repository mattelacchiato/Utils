package de.splitstudio.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NumberUtilsTest {

	private Locale systemDefault;

	@Before
	public void setUp() {
		systemDefault = Locale.getDefault();
		Locale.setDefault(Locale.US);
		NumberUtils.init();
	}

	@After
	public void tearDown() {
		Locale.setDefault(systemDefault);
		NumberUtils.init();
	}

	@Test
	public void formatAsCurrency_formats1Dollar() {
		assertThat(NumberUtils.formatAsCurrency(100), is("$1.00"));
	}

	@Test
	public void formatAsCurrency_formatsMinus1Dollar() {
		assertThat(NumberUtils.formatAsCurrency(-100), is("($1.00)"));
	}

	@Test
	public void formatAsCurrency_formats1Euro() {
		setGermanLocale();
		assertThat(NumberUtils.formatAsCurrency(100), is("1,00 €"));
	}

	@Test
	public void formatAsCurrency_formatsMinus1Euro() {
		setGermanLocale();
		assertThat(NumberUtils.formatAsCurrency(-100), is("-1,00 €"));
	}

	@Test
	public void parseCent_1dollar() throws Exception {
		assertThat(NumberUtils.parseCent("1.00"), is(100));
	}

	@Test
	public void parseCent_1dot14_114() throws Exception {
		assertThat(NumberUtils.parseCent("1.14"), is(114));
	}

	@Test
	public void parseCent_minus1dollar() throws Exception {
		assertThat(NumberUtils.parseCent("-1.00"), is(-100));
	}

	@Test
	public void parseCent_minus1cent() throws Exception {
		assertThat(NumberUtils.parseCent("-0.01"), is(-1));
	}

	@Test
	public void parseCent_1000Euro() throws Exception {
		setGermanLocale();
		assertThat(NumberUtils.parseCent("1000"), is(100000));
	}

	@Test
	public void parseCent_minus123_456Euro() throws Exception {
		setGermanLocale();
		assertThat(NumberUtils.parseCent("-123,456€"), is(-12345));
	}

	@Test
	public void formatAsDecimal_100_1dot00() {
		assertThat(NumberUtils.formatAsDecimal(100), is("1.00"));
	}

	@Test
	public void formatAsDecimal_100toGermany_1comma00() {
		setGermanLocale();
		assertThat(NumberUtils.formatAsDecimal(100), is("1,00"));
	}

	@Test
	public void formatAsIntegerCurrency_100_1Dollar() throws Exception {
		assertThat(NumberUtils.formatAsIntegerCurrency(100), is("$1"));
	}

	@Test
	public void formatAsIntegerCurrency_199_2Dollar() throws Exception {
		assertThat(NumberUtils.formatAsIntegerCurrency(199), is("$2"));
	}

	@Test
	public void formatAsIntegerCurrency_150_2Dollar() throws Exception {
		assertThat(NumberUtils.formatAsIntegerCurrency(199), is("$2"));
	}

	private void setGermanLocale() {
		Locale.setDefault(Locale.GERMANY);
		NumberUtils.init();
	}
}
