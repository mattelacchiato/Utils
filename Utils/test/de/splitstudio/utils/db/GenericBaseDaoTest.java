package de.splitstudio.utils.db;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;

public class GenericBaseDaoTest {

	private GenericBaseDao<Entity> dao;
	private EmbeddedObjectContainer db;

	private Entity entity;

	@Before
	public void setUp() throws Exception {
		db = Db4oEmbedded.openFile(File.createTempFile(UUID.randomUUID().toString(), "").getAbsolutePath());
		Database.clear(db);
		dao = new GenericBaseDao<Entity>(db) {
		};
		entity = new Entity();
		db.store(entity);
	}

	@Test
	public void findByUuid_notFound_null() throws Exception {
		assertThat(dao.findByUuid("not existing"), is(nullValue()));
	}

	@Test
	public void findByUuid_found_returnsResult() throws Exception {
		assertThat((Entity) dao.findByUuid(entity.uuid), is(entity));
	}
}
