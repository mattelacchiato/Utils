package de.splitstudio.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class NumberUtilsTestFormatAsCurrency {

	private static Locale locale;

	public static class WithGermanLocale {
		@Before
		public void setUp() {
			locale = Locale.GERMANY;
		}

		@Test
		public void _0() throws Exception {
			assertThat(NumberUtils.formatAsCurrency(0, locale), is("0,00 €"));
		}

		@Test
		public void _minus1234() throws Exception {
			assertThat(NumberUtils.formatAsCurrency(-1234, locale), is("-12,34 €"));
		}
	}

	public static class WihtUsLocale {
		@Before
		public void setUp() {
			locale = Locale.US;
		}

		@Test
		public void _0() throws Exception {
			assertThat(NumberUtils.formatAsCurrency(0, locale), is("$0.00"));
		}

		@Test
		public void _1() throws Exception {
			assertThat(NumberUtils.formatAsCurrency(1, locale), is("$0.01"));
		}

		@Test
		public void _minus1() throws Exception {
			assertThat(NumberUtils.formatAsCurrency(-1, locale), is("($0.01)"));
		}

		@Test
		public void _minus123() throws Exception {
			assertThat(NumberUtils.formatAsCurrency(-123, locale), is("($1.23)"));
		}
	}

}
