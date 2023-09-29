package com.liberbox.audit.repository;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.liberbox.audit.builder.AuditBuilder;
import com.liberbox.audit.domain.Audit;
import com.liberbox.audit.service.AuditService;
import com.liberbox.config.domain.UserContext;

@Component
public class AuditListeners {

	private final ObjectMapper objectMapper = new ObjectMapper();
	@Lazy
	@Autowired
	private AuditService service;

	{
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.registerModule(new JavaTimeModule());
	}

	public AuditListeners() {

	}

	@PostPersist
	private void afterSave(Object savedObject) throws JsonProcessingException {

		if (!(savedObject instanceof Auditable auditableSavedObject)) {
			return;
		}

		AuditBuilder builder = new AuditBuilder();

		Audit saveAudit = builder.userWhoExecutedTheActionId(UserContext.getCurrentUser())
				.action(com.liberbox.audit.domain.enums.Action.CREATE).entityId(auditableSavedObject.getEntityId())
				.entityName(auditableSavedObject.getEntityName())
				.objectRepresentationInJSON(objectMapper.writeValueAsString(savedObject)).build();

		service.execute(saveAudit);
	}

	@PreRemove
	private void afterRemove(Object deletedObject) throws JsonProcessingException {

		if (!(deletedObject instanceof Auditable auditableDeletedObject)) {
			return;
		}

		AuditBuilder builder = new AuditBuilder();

		Audit deletedAudit = builder.userWhoExecutedTheActionId(UserContext.getCurrentUser())
				.action(com.liberbox.audit.domain.enums.Action.DELETE).entityId(auditableDeletedObject.getEntityId())
				.entityName(auditableDeletedObject.getEntityName())
				.objectRepresentationInJSON(objectMapper.writeValueAsString(deletedObject)).build();

		service.execute(deletedAudit);
	}

	@PostUpdate
	private void postUpdate(Object updateObject) throws JsonProcessingException {

		if (!(updateObject instanceof Auditable auditableUpdatedObject)) {
			return;
		}

		AuditBuilder builder = new AuditBuilder();

		Audit deletedAudit = builder.userWhoExecutedTheActionId(UserContext.getCurrentUser())
				.action(com.liberbox.audit.domain.enums.Action.UPDATE).entityId(auditableUpdatedObject.getEntityId())
				.entityName(auditableUpdatedObject.getEntityName())
				.objectRepresentationInJSON(objectMapper.writeValueAsString(auditableUpdatedObject)).build();

		service.execute(deletedAudit);
	}
}
