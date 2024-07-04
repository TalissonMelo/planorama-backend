package com.liberbox.sessions.service;

import com.liberbox.legend.controller.response.LegendResponse;
import com.liberbox.legend.service.GetLegendIdService;
import com.liberbox.sessions.controller.response.SessionResponse;
import com.liberbox.sessions.domain.Session;
import com.liberbox.sessions.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GetSessionService {

    private final SessionRepository sessionRepository;
    private final GetLegendIdService getLegendIdService;

    public List<SessionResponse> execute(String scheduleId) {

        List<Session> sessions = sessionRepository.findByScheduleId(scheduleId);

        List<LegendResponse> legendResponses = getLegendIdService.execute(sessions.stream().map(session -> session.getLegendId()).collect(Collectors.toList()));

        return sessions.stream().map(session -> toSessionResponse(session, legendResponses)).collect(Collectors.toList());

    }

    private SessionResponse toSessionResponse(Session session, List<LegendResponse> legendResponses) {
        return new SessionResponse(session.getId(),
                session.getScheduleId(),
                toColor(session.getLegendId(), legendResponses),
                session.getTitle(),
                session.getStartTime(),
                session.getEndTime(),
                session.getDescription());
    }

    private String toColor(String legendId, List<LegendResponse> legendResponses) {
        return legendResponses.stream().filter(legendResponse -> legendResponse.id().equals(legendId)).findFirst().map(legendResponse -> legendResponse.color()).orElse(null);
    }
}