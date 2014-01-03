package de.splitstudio.utils.db;

import java.io.File;
import java.util.TreeSet;

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
		return clear(db);
	}

	public static ObjectContainer clear(EmbeddedObjectContainer db) {
		for (Object object : db.query().execute()) {
			db.delete(object);
		}
		return db;
	}

	public static EmbeddedConfiguration createConfig() {
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
//		config.common().activationDepth(Integer.MAX_VALUE);
		config.common().updateDepth(Integer.MAX_VALUE);
		config.common().objectClass(TreeSet.class).cascadeOnDelete(true);
//		config.common().objectClass(Cascadable.class).cascadeOnDelete(true);
//		config.common().objectClass(Cascadable.class).cascadeOnActivate(true);
//		config.common().objectClass(Cascadable.class).cascadeOnUpdate(true);
//		config.common().add(new UuidSupport());
		return config;
	}

}
