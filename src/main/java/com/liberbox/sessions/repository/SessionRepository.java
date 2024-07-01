package com.liberbox.sessions.repository;

import com.liberbox.sessions.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, String> {
}
