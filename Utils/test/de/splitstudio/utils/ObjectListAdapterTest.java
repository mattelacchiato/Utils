package de.splitstudio.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.robolectric.Robolectric.buildActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@RunWith(RobolectricTestRunner.class)
public class ObjectListAdapterTest {

	private ObjectListAdapter<String> adapter;

	private static final ViewGroup ANY_PARENT = new ViewGroup(buildActivity(Activity.class).create().get()) {
		@Override
		protected void onLayout(boolean changed, int l, int t, int r, int b) {}
	};

	private LayoutInflater layoutInflater;

	private static final int ANY_RESOURCE_ID = 123;

	public View boundView;
	public String boundObject;

	@Before
	public void setUp() {
		layoutInflater = Mockito.mock(LayoutInflater.class);
		adapter = new ObjectListAdapter<String>(layoutInflater, ANY_RESOURCE_ID, Arrays.asList("a", "b", "c")) {

			@Override
			public void bindView(View view, String object) {
				boundView = view;
				boundObject = object;
			}

			@Override
			public void update(List<String> objects) {}
		};
	}

	@Test
	public void emptyAdapter_countIsZero() throws Exception {
		adapter = new ObjectListAdapter<String>(layoutInflater, ANY_RESOURCE_ID, Collections.<String> emptyList()) {
			@Override
			public void bindView(View view, String object) {}

			@Override
			public void update(List<String> objects) {}
		};
		assertThat(adapter.getCount(), is(0));
	}

	@Test
	public void AdapterWith3Items_countIsThree() throws Exception {
		assertThat(adapter.getCount(), is(3));
	}

	@Test
	public void AdapterWith3Items_getFirstItem_a() throws Exception {
		assertThat(adapter.getItem(0), is("a"));
	}

	@Test
	public void AdapterWith3Items_getItemId0_0() throws Exception {
		assertThat(adapter.getItemId(0), is(0L));
	}

	@Test
	public void AdapterWith3Items_getItemId123456_123456() throws Exception {
		assertThat(adapter.getItemId(123456), is(123456L));
	}

	@Test
	public void AdapterWith3Items_getView_convertViewIsNotNull_convertView() throws Exception {
		View convertView = new View(new Activity());
		assertThat(adapter.getView(0, convertView, ANY_PARENT), is(convertView));
	}

	@Test
	public void AdapterWith3Items_getView_convertViewIsNotNull_isBound() throws Exception {
		View convertView = new View(new Activity());

		adapter.getView(0, convertView, ANY_PARENT);

		assertThat(boundView, is(convertView));
		assertThat(boundObject, is("a"));

	}

	@Test
	public void AdapterWith3Items_getView_convertViewIsNull_inflatedView() throws Exception {
		View convertView = null;
		View inflatedView = new View(new Activity());

		when(layoutInflater.inflate(ANY_RESOURCE_ID, ANY_PARENT, false)).thenReturn(inflatedView);
		adapter.getView(0, convertView, ANY_PARENT);
	}

	@Test
	public void AdapterWith3Items_getView_convertViewIsNull_isBound() throws Exception {
		View convertView = null;
		View inflatedView = new View(new Activity());
		when(layoutInflater.inflate(ANY_RESOURCE_ID, ANY_PARENT, false)).thenReturn(inflatedView);

		adapter.getView(0, convertView, ANY_PARENT);

		assertThat(boundView, is(inflatedView));
		assertThat(boundObject, is("a"));
	}
}
