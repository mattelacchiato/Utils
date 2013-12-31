package de.splitstudio.utils.db;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.db4o.config.annotations.Indexed;

public abstract class UniqueEntity implements CascadeOnDelete {

	@Indexed
	public String uuid2;

	public UniqueEntity() {
		this.uuid2 = UUID.randomUUID().toString();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof UniqueEntity) {
			return uuid2.equals(((UniqueEntity) other).uuid2);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return uuid2.hashCode();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
