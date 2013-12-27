package de.splitstudio.utils.db;

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
	private ObjectExpandableListAdapter<String> adapter;

	@Before
	public void setUp() {
		list = Arrays.asList("a", "b", "c");
		adapter = new ObjectExpandableListAdapter<String>(null, list, 0, 0) {
			@Override
			public void bindGroupView(View view, String object) {}

			@Override
			public void bindChildView(View view, String object) {}
		};
	}

	@Test
	public void getGroupCount_sizeOfList() {
		assertThat(adapter.getGroupCount(), is(3));
	}

	@Test
	public void getChildrenCount_always1() {
		assertThat(adapter.getChildrenCount(0), is(1));
	}

	@Test
	public void getGroup_objectAtPosition() {
		assertThat(adapter.getGroup(0), is("a"));
		assertThat(adapter.getGroup(1), is("b"));
		assertThat(adapter.getGroup(2), is("c"));
	}

	@Test
	public void getGroupId_position() {
		assertThat(adapter.getGroupId(0), is(0L));
		assertThat(adapter.getGroupId(200), is(200L));
	}

	@Test
	public void getChildId_always0() {
		assertThat(adapter.getChildId(123, 123), is(0L));
	}

	@Test
	public void hasStableIds_false() {
		assertThat(adapter.hasStableIds(), is(false));
	}
}
