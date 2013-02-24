package de.splitstudio.utils.view;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import de.splitstudio.utils.R;

@RunWith(RobolectricTestRunner.class)
public class CalculatorTest {

	private Calculator calculator;

	private EditText amountEdit;

	@Before
	public void setUp() {
		Activity activity = new Activity();
		activity.getResources().getConfiguration().locale = Locale.US;

		calculator = (Calculator) LayoutInflater.from(activity).inflate(R.layout.calculator, null);
		amountEdit = (EditText) calculator.findViewById(R.id.calculator_amount);
		calculator.onAttachedToWindow();
	}

	@Test
	public void eachNumberButtonAppendsToAmount() {
		assertButtonClickAppends(R.id.button_0, "0");
		assertButtonClickAppends(R.id.button_1, "01");
		assertButtonClickAppends(R.id.button_2, "012");
		assertButtonClickAppends(R.id.button_3, "0123");
		assertButtonClickAppends(R.id.button_4, "01234");
		assertButtonClickAppends(R.id.button_5, "012345");
		assertButtonClickAppends(R.id.button_6, "0123456");
		assertButtonClickAppends(R.id.button_7, "01234567");
		assertButtonClickAppends(R.id.button_8, "012345678");
		assertButtonClickAppends(R.id.button_9, "0123456789");
		assertButtonClickAppends(R.id.button_minus, "0123456789-");
		assertButtonClickAppends(R.id.button_seperator, "0123456789-.");
	}

	@Test
	public void speratorIsDot() {
		assertThat(((Button) calculator.findViewById(R.id.button_seperator)).getText().toString(), is("."));
	}

	@Test
	public void amountIsEmpty() {
		assertThat(amountEdit.getText().toString(), is(""));
	}

	@Test
	public void twoCharsAdded_deleteButtonDeletesLastChar() {
		((Button) calculator.findViewById(R.id.button_2)).performClick();
		((Button) calculator.findViewById(R.id.button_0)).performClick();

		((Button) calculator.findViewById(R.id.button_del)).performClick();
		assertThat(amountEdit.getText().toString(), is("2"));
	}

	@Test
	public void noChars_deleteButtonDoesNothing() {
		((Button) calculator.findViewById(R.id.button_del)).performClick();
		assertThat(amountEdit.getText().toString(), is(""));
	}

	private void assertButtonClickAppends(int buttonId, String expected) {
		((Button) calculator.findViewById(buttonId)).performClick();
		assertThat(amountEdit.getText().toString(), is(expected));
	}

}
