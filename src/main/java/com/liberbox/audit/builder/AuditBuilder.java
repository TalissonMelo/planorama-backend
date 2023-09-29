package com.liberbox.audit.builder;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Id;

import com.liberbox.audit.domain.Audit;
import com.liberbox.audit.domain.enums.Action;

public final class AuditBuilder implements AuditableBuilder {

	@Id
	private final String id = UUID.randomUUID().toString();

	private String userId;

	private Action action;

	private final LocalDateTime executedAt = LocalDateTime.now();

	private String entityId;

	private String entityName;

	private String objectRepresentationInJSON;

	@Override
	public AuditBuilder userWhoExecutedTheActionId(String userId) {
		this.userId = userId;
		return this;
	}

	@Override
	public AuditBuilder action(Action action) {
		this.action = action;
		return this;
	}

	@Override
	public AuditBuilder entityId(String entityId) {
		this.entityId = entityId;
		return this;
	}

	@Override
	public AuditBuilder entityName(String entityName) {
		this.entityName = entityName;
		return this;
	}

	@Override
	public AuditBuilder objectRepresentationInJSON(String objectRepresentationInJSON) {
		this.objectRepresentationInJSON = objectRepresentationInJSON;
		return this;
	}

	public Audit build() {
		return new Audit(id, userId, action, executedAt, entityId, entityName, objectRepresentationInJSON);
	}

}
