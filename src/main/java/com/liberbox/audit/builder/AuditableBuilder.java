package com.liberbox.audit.builder;

import com.liberbox.audit.domain.enums.Action;

public interface AuditableBuilder {

	AuditBuilder userWhoExecutedTheActionId(String userId);

	AuditBuilder action(Action action);

	AuditBuilder entityId(String entityId);

	AuditBuilder entityName(String entityName);

	AuditBuilder objectRepresentationInJSON(String objectRepresentationInJSON);
}
