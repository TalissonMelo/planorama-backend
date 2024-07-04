package com.liberbox.sessions.service;

import com.liberbox.sessions.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSessionService {

    private final SessionRepository repository;

    public void execute(String sessionId) {

        repository.deleteById(sessionId);
    }
}
