package de.splitstudio.utils.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

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

}
