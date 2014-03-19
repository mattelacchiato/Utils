package de.splitstudio.utils.db;

import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public abstract class GenericBaseDao<T> {
	protected final ObjectContainer db;

	public GenericBaseDao(ObjectContainer db) {
		this.db = db;
	}

	public List<T> findAll(Class<T> klaas) {
		return new ArrayList<T>(db.query(klaas));
	}

	@SuppressWarnings({ "unchecked", "serial" })
	public T findByUuid(final String uuid) {
		ObjectSet<UniqueEntity> result = db.query(new Predicate<UniqueEntity>() {
			@Override
			public boolean match(UniqueEntity entity) {
				return uuid.equals(entity.uuid);
			}
		});
		if (result.isEmpty()) {
			return null;
		}
		if (result.size() > 1) {
			throw new IllegalStateException("Found multple entities for uuid " + uuid + ": " + result.toArray());
		}
		return (T) result.next();
	}

	public void store(T entity) {
		db.store(entity);
		db.commit();
	}

	public void delete(T entity) {
		db.delete(entity);
		db.commit();
	}
}
