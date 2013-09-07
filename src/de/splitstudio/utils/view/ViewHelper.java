package de.splitstudio.utils.view;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;

public class ViewHelper {

	public static List<View> getViewsById(ViewGroup root, int id) {
		List<View> views = new ArrayList<View>();
		for (int i = 0; i < root.getChildCount(); i++) {
			View child = root.getChildAt(i);
			if (child instanceof ViewGroup) {
				views.addAll(getViewsById((ViewGroup) child, id));
			}

			if (child.getId() == id) {
				views.add(child);
			}
		}
		return views;
	}
}
