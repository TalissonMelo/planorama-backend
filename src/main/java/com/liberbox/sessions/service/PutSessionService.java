package com.liberbox.sessions.service;

import com.liberbox.sessions.controller.request.PutSessionRequest;
import com.liberbox.sessions.domain.Session;
import com.liberbox.sessions.repository.SessionRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "session")
@RestController
@RequiredArgsConstructor
public class PutSessionService {

    private final SessionRepository sessionRepository;

    public void execute(String sessionId, PutSessionRequest request) {

        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("Session does not exist"));

        session.update(request.description());

        sessionRepository.save(session);

    }
}
