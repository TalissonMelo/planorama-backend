package com.liberbox.audit.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liberbox.audit.domain.Audit;
import com.liberbox.audit.repository.AuditRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AuditService {

	private final AuditRepository repository;

	public void execute(Audit audit) {

		repository.save(audit);
	}
}
