package de.splitstudio.utils;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

public abstract class ObjectExpandableListAdapter<T> extends BaseExpandableListAdapter {

	private final List<T> objects;
	private final int childView;
	private final int groupView;
	private final LayoutInflater layoutInflater;

	public ObjectExpandableListAdapter(LayoutInflater layoutInflater, List<T> objects, int groupView, int childView) {
		this.layoutInflater = layoutInflater;
		this.objects = objects;
		this.groupView = groupView;
		this.childView = childView;
	}

	@Override
	public int getGroupCount() {
		return objects.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public T getGroup(int groupPosition) {
		return objects.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		View result = convertView;
		if (result == null) {
			result = layoutInflater.inflate(groupView, parent, false);
		}
		bindGroupView(result, getGroup(groupPosition));
		return result;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		View result = convertView;
		if (result == null) {
			result = layoutInflater.inflate(childView, parent, false);
		}
		bindChildView(result, getGroup(groupPosition));
		return result;
	}

	public abstract void bindGroupView(View view, T object);

	public abstract void bindChildView(View view, T object);

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
