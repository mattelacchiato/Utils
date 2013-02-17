package de.splitstudio.utils.stubs;

import android.app.Activity;
import android.view.ViewGroup;

public class ViewGroupStub extends ViewGroup {

	public ViewGroupStub() {
		super(new Activity());
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {}

}
