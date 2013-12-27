package de.splitstudio.utils.db;

import java.util.UUID;

public abstract class UniqueEntity {

	public String uuid;

	protected UniqueEntity() {
		this.uuid = UUID.randomUUID().toString();
	}

}
