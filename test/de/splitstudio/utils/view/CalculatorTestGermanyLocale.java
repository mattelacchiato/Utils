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
import de.splitstudio.utils.R;

@RunWith(RobolectricTestRunner.class)
public class CalculatorTestGermanyLocale {

	private Calculator calculator;

	@Before
	public void setUp() {
		Activity activity = new Activity();
		activity.getResources().getConfiguration().locale = Locale.GERMANY;

		calculator = (Calculator) LayoutInflater.from(activity).inflate(R.layout.calculator, null);
		calculator.onAttachedToWindow();
	}

	@Test
	public void germanLocale_speratorIsColon() {
		Button deleteButton = (Button) calculator.findViewById(R.id.button_seperator);
		assertThat(deleteButton.getText().toString(), is(","));
	}

}
