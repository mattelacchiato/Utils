package de.splitstudio.utils;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class ObjectListAdapter<T> extends BaseAdapter {

	protected final List<T> objects;

	private final LayoutInflater layoutInflater;

	private final int layoutId;

	public ObjectListAdapter(LayoutInflater layoutInflater, int resourceId, List<T> objects) {
		this.layoutInflater = layoutInflater;
		this.layoutId = resourceId;
		this.objects = objects;
	}

	@Override
	public int getCount() {
		return objects.size();
	}

	@Override
	public T getItem(int position) {
		return objects.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View result = convertView;
		if (result == null) {
			result = layoutInflater.inflate(layoutId, parent, false);
		}
		bindView(result, getItem(position));
		return result;
	}

	public abstract void bindView(View view, T object);

}
