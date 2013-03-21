package de.splitstudio.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.view.View;

@RunWith(RobolectricTestRunner.class)
public class ObjectExpandableListAdapterTest {

	private List<String> list;
	private ObjectExpandableListAdapter<String> listAdapter;

	@Before
	public void setUp() {
		list = Arrays.asList("a", "b", "c");
		listAdapter = new ObjectExpandableListAdapter<String>(null, list, 0, 0) {
			@Override
			public void bindGroupView(View view, String object) {}

			@Override
			public void bindChildView(View view, String object) {}
		};
	}

	@Test
	public void getGroupCount_sizeOfList() {
		assertThat(listAdapter.getGroupCount(), is(3));
	}

	@Test
	public void getChildrenCount_always1() {
		assertThat(listAdapter.getChildrenCount(0), is(1));
	}

	@Test
	public void getGroup_objectAtPosition() {
		assertThat(listAdapter.getGroup(0), is("a"));
		assertThat(listAdapter.getGroup(1), is("b"));
		assertThat(listAdapter.getGroup(2), is("c"));
	}

	@Test
	public void getGroupId_position() {
		assertThat(listAdapter.getGroupId(0), is(0L));
		assertThat(listAdapter.getGroupId(200), is(200L));
	}

	@Test
	public void getChildId_always0() {
		assertThat(listAdapter.getChildId(123, 123), is(0L));
	}

	@Test
	public void hasStableIds_false() {
		assertThat(listAdapter.hasStableIds(), is(false));
	}
}
