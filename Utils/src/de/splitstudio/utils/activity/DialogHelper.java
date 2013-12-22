package de.splitstudio.utils.activity;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.DatePicker;

public class DialogHelper {

	private static final OnClickListener EMPTY_LISTENER = new OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {}
	};

	public static void createQuestion(Context context, int title, int question, int cancel, int ok,
			final Runnable yesAction) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage(question);
		builder.setPositiveButton(ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				yesAction.run();
			}
		});
		builder.setNegativeButton(cancel, EMPTY_LISTENER);
		builder.show();
	}

	public static void pickDate(Context context, final Calendar date, final Runnable callback) {
		OnDateSetListener dateSetListener = new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				date.set(year, monthOfYear, dayOfMonth);
				if (callback != null) {
					callback.run();
				}
			}
		};
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH);
		int dayOfMonth = date.get(Calendar.DAY_OF_MONTH);
		new DatePickerDialog(context, dateSetListener, year, month, dayOfMonth).show();

	}

}
