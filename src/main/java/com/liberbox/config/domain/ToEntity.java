package com.liberbox.config.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.liberbox.audit.repository.AuditListeners;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EntityListeners({ AuditingEntityListener.class, AuditListeners.class })
public abstract class ToEntity implements UserId {

	@CreatedDate
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "user_id")
	String userId;

	ToEntity(String userId) {
		this.userId = userId;
	}

	@PrePersist
	public void prePersist() {
		this.userId = UserContext.getCurrentUser();
	}
}
