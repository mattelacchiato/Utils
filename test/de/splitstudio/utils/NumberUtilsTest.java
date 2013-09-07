package de.splitstudio.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class NumberUtilsTest {

	//TODO (07.09.2013): rm Locales from parameters and set them via Locale.setDefault()

	@Test
	public void formatAsCurrency_formats1Dollar() {
		assertThat(NumberUtils.formatAsCurrency(100, Locale.US), is("$1.00"));
	}

	@Test
	public void formatAsCurrency_formatsMinus1Dollar() {
		assertThat(NumberUtils.formatAsCurrency(-100, Locale.US), is("($1.00)"));
	}

	@Test
	public void formatAsCurrency_formats1Euro() {
		assertThat(NumberUtils.formatAsCurrency(100, Locale.GERMANY), is("1,00 €"));
	}

	@Test
	public void formatAsCurrency_formatsMinus1Euro() {
		assertThat(NumberUtils.formatAsCurrency(-100, Locale.GERMANY), is("-1,00 €"));
	}

	@Test
	public void parseCent_1dollar() throws Exception {
		assertThat(NumberUtils.parseCent("1.00", Locale.US), is(100));
	}

	@Test
	public void parseCent_minus1dollar() throws Exception {
		assertThat(NumberUtils.parseCent("-1.00", Locale.US), is(-100));
	}

	@Test
	public void parseCent_minus1cent() throws Exception {
		assertThat(NumberUtils.parseCent("-0.01", Locale.US), is(-1));
	}

	@Test
	public void parseCent_1000Euro() throws Exception {
		assertThat(NumberUtils.parseCent("1000", Locale.GERMAN), is(100000));
	}

	@Test
	public void parseCent_minus123_456Euro() throws Exception {
		assertThat(NumberUtils.parseCent("-123,456€", Locale.GERMAN), is(-12345));
	}

	@Test
	public void formatAsDecimal_100_1dot00() {
		Locale.setDefault(Locale.US);
		assertThat(NumberUtils.formatAsDecimal(100), is("1.00"));
	}
}
