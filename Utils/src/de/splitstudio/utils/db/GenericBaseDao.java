package de.splitstudio.utils.db;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public abstract class GenericBaseDao<T> {
	protected final ObjectContainer db;

	public GenericBaseDao(ObjectContainer db) {
		this.db = db;
	}

	@SuppressWarnings({ "unchecked", "serial" })
	public <S extends UniqueEntity> S findByUuid(final String uuid) {
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
			throw new IllegalStateException("Found multple entities for uuid " + uuid);
		}
		return (S) result.next();
	}

	public void store(T entity) {
		db.store(entity);
	}

}
