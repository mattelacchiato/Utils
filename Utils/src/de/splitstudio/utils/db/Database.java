package de.splitstudio.utils.db;

import java.io.File;

import android.content.Context;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

public class Database {

	private static EmbeddedObjectContainer db;

	private Database() {}

	public static synchronized ObjectContainer getInstance(Context context) {
		if (db == null) {
			String databaseFileName = new File(context.getApplicationContext().getFilesDir(), "db").getAbsolutePath();
			db = Db4oEmbedded.openFile(createConfig(), databaseFileName);
		}
		return db;
	}

	/*
	 * Helper method for testing.
	 */
	public static ObjectContainer getClearedInstance(Context context) {
		getInstance(context);
		return clear();
	}

	public static ObjectContainer clear() {
		for (Object object : db.query().execute()) {
			db.delete(object);
		}
		return db;
	}

	private static EmbeddedConfiguration createConfig() {
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().objectClass(CascadeOnDelete.class).cascadeOnDelete(true);
		return config;
	}

}
