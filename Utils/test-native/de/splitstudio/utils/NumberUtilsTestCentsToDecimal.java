package de.splitstudio.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class NumberUtilsTestCentsToDecimal {

	@Test
	public void _1ctIs0_01() throws Exception {
		assertThat(NumberUtils.centsToDecimal(1), is(0.01f));
	}

	@Test
	public void _2ctIs0_02() throws Exception {
		assertThat(NumberUtils.centsToDecimal(2), is(0.02f));
	}

	@Test
	public void _99ctIs0_99() throws Exception {
		assertThat(NumberUtils.centsToDecimal(99), is(0.99f));
	}

	@Test
	public void _100ctIs1_00() throws Exception {
		assertThat(NumberUtils.centsToDecimal(100), is(1.00f));
	}

	@Test
	public void _101ctIs1_01() throws Exception {
		assertThat(NumberUtils.centsToDecimal(101), is(1.01f));
	}

	@Test
	public void _minus1ctIsMinus0_01() throws Exception {
		assertThat(NumberUtils.centsToDecimal(-1), is(-0.01f));
	}

	@Test
	public void _0ctIs0_00() throws Exception {
		assertThat(NumberUtils.centsToDecimal(0), is(0.0f));
	}
}
