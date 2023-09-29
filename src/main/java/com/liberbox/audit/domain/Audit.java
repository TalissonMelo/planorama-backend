package com.liberbox.audit.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.liberbox.audit.domain.enums.Action;

import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@ToString
public class Audit {

	@Id
	private String id;

	private String userId;

	@Enumerated(EnumType.STRING)
	private Action action;

	private LocalDateTime executedAt = LocalDateTime.now();

	private String entityName;

	private String entityId;

	@Column(length = 10000)
	private String objectRepresentationInJSON;

	public Audit() {
	}

	public Audit(String id, String userId, Action action, LocalDateTime executedAt, String entityId, String entityName,
			String objectRepresentationInJSON) {

		this.id = id;
		this.userId = userId;
		this.action = action;
		this.executedAt = executedAt;
		this.entityId = entityId;
		this.entityName = entityName;
		this.objectRepresentationInJSON = objectRepresentationInJSON;
	}
}