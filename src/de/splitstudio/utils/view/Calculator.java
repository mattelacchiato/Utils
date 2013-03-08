package de.splitstudio.utils.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import de.splitstudio.utils.NumberUtils;
import de.splitstudio.utils.R;

public class Calculator extends LinearLayout {

	private EditText amountEdit;

	private Locale locale;

	public Calculator(final Context context) {
		super(context);
	}

	public Calculator(final Context context, final AttributeSet attrs) {
		super(context, attrs);
	}

	public int parseAmountInCent() throws ParseException {
		String amountString = amountEdit.getText().toString();
		return NumberUtils.parseCent(amountString, locale);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		amountEdit = (EditText) findViewById(R.id.calculator_amount);
		locale = getResources().getConfiguration().locale;
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

		setCharInDecimalSeperatorButton(currencyFormat);
		registerButtons();
		amountEdit.setHint(currencyFormat.format(0));
	}

	private void setCharInDecimalSeperatorButton(final NumberFormat currencyFormat) {
		String dec = ((DecimalFormat) currencyFormat).getDecimalFormatSymbols().getDecimalSeparator() + "";
		((Button) findViewById(R.id.button_seperator)).setText(dec);
	}

	private void registerButtons() {
		TableLayout calculatorView = (TableLayout) findViewById(R.id.calculator_table);

		for (int rowId = 0; rowId < calculatorView.getChildCount(); rowId++) {
			TableRow row = (TableRow) calculatorView.getChildAt(rowId);
			for (int buttonId = 0; buttonId < row.getChildCount(); buttonId++) {
				View view = row.getChildAt(buttonId);
				if (view instanceof Button) {
					view.setOnClickListener(createCalcButtonListener());
				}
			}
		}
	}

	private OnClickListener createCalcButtonListener() {
		return new OnClickListener() {
			@Override
			public void onClick(final View v) {
				int length = amountEdit.getText().length();
				if (v.getId() == R.id.button_del) {
					if (length > 0) {
						amountEdit.setText(amountEdit.getText().delete(length - 1, length));
					}
				} else {
					amountEdit.append(((Button) v).getText());
				}
			}
		};
	}
}
