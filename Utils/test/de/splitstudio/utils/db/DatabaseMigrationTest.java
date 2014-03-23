package de.splitstudio.utils.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

import com.db4o.ObjectContainer;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class DatabaseMigrationTest {

	private static final Context ANY_CONTEXT = new Activity();
	private ObjectContainer db;
	private File versionFile;
	private static Boolean shouldMigrationBeCalled;

	private TestMigration migration;

	@Before
	public void setUp() {
		migration = new TestMigration(ANY_CONTEXT);
		versionFile = new File(ANY_CONTEXT.getFilesDir(), "version");
		versionFile.delete();
		shouldMigrationBeCalled = null;
	}

	@Test
	public void getLastVersion_fileDoesNotExist_zero() {
		assertThat(migration.getLastVersion()).isEqualTo(0);
	}

	@Test
	public void getLastVersion_fileDoesExist_numberFromFile() throws IOException {
		FileUtils.write(versionFile, "42");

		assertThat(migration.getLastVersion()).isEqualTo(42);
	}

	@Test(expected = NumberFormatException.class)
	public void getLastVersion_fileContainsCrap_exception() throws IOException {
		FileUtils.write(versionFile, "crap");

		migration.getLastVersion();
	}

	@Test
	public void getCurrentVersion_versionFromPackageManager() throws Exception {
		setCurrentVersionTo(42);

		assertThat(migration.getCurrentVersion()).isEqualTo(42);
	}

	@Test
	public void run_currentVersionIsLastVersion_doesNothing() throws Exception {
		assertThat(migration.getCurrentVersion()).isEqualTo(migration.getLastVersion());

		shouldMigrationBeCalled = false;
		migration.run(db);
	}

	@Test
	public void run_currentVersionIsDifferntToLastVersion_runMigration() throws Exception {
		setCurrentVersionTo(1);
		assertThat(migration.getLastVersion()).isNotEqualTo(migration.getCurrentVersion());

		shouldMigrationBeCalled = true;
		migration.run(db);
	}

	@Test
	public void run_currentVersionIsDifferntToLastVersion_getLastVersionIsCurrentVersion() throws Exception {
		setCurrentVersionTo(1);
		assertThat(migration.getLastVersion()).isNotEqualTo(migration.getCurrentVersion());

		migration.run(db);
		assertThat(migration.getLastVersion()).isEqualTo(migration.getCurrentVersion());
	}

	private void setCurrentVersionTo(int currentVersion) throws NameNotFoundException {
		ANY_CONTEXT.getPackageManager().getPackageInfo(ANY_CONTEXT.getPackageName(), 0).versionCode = currentVersion;
	}

	private static class TestMigration extends DatabaseMigration {

		public TestMigration(Context context) {
			super(context);
		}

		@Override
		protected void migrate(int lastVersion, int currentVersion, ObjectContainer db) {
			if (shouldMigrationBeCalled != null && !shouldMigrationBeCalled) {
				fail("migrate should not be called!");
			}
		}
	}

}
