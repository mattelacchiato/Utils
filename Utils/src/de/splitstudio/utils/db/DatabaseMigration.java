package de.splitstudio.utils.db;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

import com.db4o.ObjectContainer;

public abstract class DatabaseMigration {

	private static final String VERSION_FILENAME = "version";
	private final Context context;
	private final File file;

	public DatabaseMigration(Context context) {
		this.context = context;
		this.file = new File(context.getFilesDir(), VERSION_FILENAME);
	}

	public ObjectContainer run(ObjectContainer db) {
		int currentVersion = getCurrentVersion();
		int lastVersion = getLastVersion();
		if (currentVersion != lastVersion) {
			migrate(lastVersion, currentVersion, db);
			writeCurrentVersionToFile(currentVersion);
		}

		return db;
	}

	private void writeCurrentVersionToFile(int currentVersion) {
		try {
			FileUtils.write(file, String.valueOf(currentVersion));
		} catch (IOException e) {
			throw new IllegalStateException("Could not write current version", e);
		}
	}

	protected abstract void migrate(int lastVersion, int currentVersion, ObjectContainer db);

	protected int getCurrentVersion() {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			throw new IllegalStateException("Could not read current version", e);
		}
	}

	protected int getLastVersion() {
		if (!file.exists()) {
			return 0;
		}
		try {
			return Integer.valueOf(FileUtils.readFileToString(file));
		} catch (IOException e) {
			throw new IllegalStateException("Could not last version from file", e);
		}
	}

	public Context getContext() {
		return context;
	}

}
