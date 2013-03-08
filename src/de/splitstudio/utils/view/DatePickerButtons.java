package de.splitstudio.utils.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import de.splitstudio.utils.R;

public class DatePickerButtons extends LinearLayout {
	private Calendar date;

	private Calendar minDate;

	private final Locale locale;

	private final DateFormat dateFormat;

	private final OnClickListener dateListener;

	private final DatePickerDialog.OnDateSetListener dateSetListener;

	private final OnClickListener datePrevNextListener;

	public DatePickerButtons(Context context) {
		this(context, null);
	}

	public DatePickerButtons(Context context, AttributeSet attrs) {
		super(context, attrs);
		locale = getResources().getConfiguration().locale;
		dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, locale);
		date = Calendar.getInstance(locale);
		dateListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				startDatePicker(getContext(), dateSetListener, date);
			}
		};
		dateSetListener = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				Calendar tempDate = (Calendar) date.clone();
				date.set(year, monthOfYear, dayOfMonth);
				if (!checkDateAndAlert(date)) {
					date = tempDate;
				} else {
					updateDateText();
				}
			}
		};
		datePrevNextListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				datePrevNextAction(v.getId() == R.id.date_minus ? -1 : 1);
			}
		};
	}

	public Calendar getDate() {
		return date;
	}

	public void setAndUpdateDate(final Calendar date) {
		this.date = date;
		updateDateText();
	}

	public void setMinDate(final Calendar minDate) {
		this.minDate = minDate;
	}

	@Override
	public boolean isInEditMode() {
		return false;
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		((Button) findViewById(R.id.date_field)).setOnClickListener(dateListener);
		((Button) findViewById(R.id.date_plus)).setOnClickListener(datePrevNextListener);
		((Button) findViewById(R.id.date_minus)).setOnClickListener(datePrevNextListener);
		updateDateText();
	}

	private boolean checkDateAndAlert(Calendar cal) {
		boolean isValid = minDate == null || cal.after(minDate);

		if (!isValid) {
			DateFormat shortDateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, locale);
			String currentTime = shortDateFormat.format(date.getTime());
			String minTime = shortDateFormat.format(minDate.getTime());
			String msg = getContext().getString(R.string.error_date_before_category_created, currentTime, minTime);
			createAlert(msg);
		}
		return isValid;
	}

	public void createAlert(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle(R.string.error);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage(msg).setPositiveButton(R.string.ok, null);
		builder.show();
	}

	private void startDatePicker(final Context context, final OnDateSetListener dateSetListener, final Calendar date) {
		new DatePickerDialog(context, dateSetListener, date.get(Calendar.YEAR), date.get(Calendar.MONTH),
				date.get(Calendar.DAY_OF_MONTH)).show();
	}

	private void updateDateText() {
		String dateText = dateFormat.format(date.getTime());
		((Button) findViewById(R.id.date_field)).setText(dateText);
	}

	private void datePrevNextAction(final int value) {
		date.add(Calendar.DAY_OF_MONTH, value);
		if (!checkDateAndAlert(date)) {
			date.add(Calendar.DAY_OF_MONTH, -value); //revert changes
		} else {
			updateDateText();
		}
	}

}
