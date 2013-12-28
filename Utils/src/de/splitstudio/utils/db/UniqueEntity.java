package de.splitstudio.utils.db;

import java.util.UUID;

public abstract class UniqueEntity {

	public String uuid;

	protected UniqueEntity() {
		this.uuid = UUID.randomUUID().toString();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof UniqueEntity) {
			return uuid.equals(((UniqueEntity) other).uuid);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();
	}

}
