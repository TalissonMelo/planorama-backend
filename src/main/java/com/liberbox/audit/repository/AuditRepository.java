package com.liberbox.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liberbox.audit.domain.Audit;

public interface AuditRepository extends JpaRepository<Audit, String> {

}
