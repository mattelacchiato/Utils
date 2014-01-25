package de.splitstudio.utils.db;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

/**
 * Holds a singleton reference of an {@link EmbeddedObjectContainer}.
 */
public class Database {

	private static EmbeddedObjectContainer db;

	private Database() {}

	public static synchronized ObjectContainer getInstance(Context context) {
		if (db == null) {
			String databaseFileName = getFile(context).getAbsolutePath();
			db = Db4oEmbedded.openFile(createConfig(), databaseFileName);
		}
		return db;
	}

	public static File getFile(Context context) {
		return new File(context.getApplicationContext().getFilesDir(), "db");
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

	public static ObjectContainer clear(ObjectContainer db) {
		for (Object object : db.query().execute()) {
			db.delete(object);
		}
		return db;
	}

	public static EmbeddedConfiguration createConfig() {
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().updateDepth(Integer.MAX_VALUE);
//		config.common().activationDepth(Integer.MAX_VALUE);
//		config.common().add(new UuidSupport());
		config.common().objectClass(ArrayList.class).cascadeOnDelete(true);
		config.common().objectClass(UniqueEntity.class).cascadeOnDelete(true);
		config.common().objectClass(UniqueEntity.class).cascadeOnActivate(true);
		config.common().objectClass(UniqueEntity.class).cascadeOnUpdate(true);
		return config;
	}

}
