package com.liberbox.sessions.service;

import com.liberbox.sessions.controller.request.SessionRequest;
import com.liberbox.sessions.controller.response.SessionResponse;
import com.liberbox.sessions.domain.Session;
import com.liberbox.sessions.repository.SessionRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "session")
@RestController
@RequiredArgsConstructor
public class PostSessionService {

    private final SessionRepository sessionRepository;

    public SessionResponse execute(SessionRequest request) {
        Session session = Session.to(request.title(), request.startTime(), request.endTime(), request.description(), request.legendId(), request.scheduleId());

        sessionRepository.save(session);

        return toSessionResponse(session);
    }

    private SessionResponse toSessionResponse(Session session) {
        return new SessionResponse(session.getId(),
                session.getScheduleId(),
                session.getLegendId(),
                session.getTitle(),
                session.getStartTime(),
                session.getEndTime(),
                session.getDescription());
    }
}
