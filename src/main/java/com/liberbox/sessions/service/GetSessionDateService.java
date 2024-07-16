package com.liberbox.sessions.service;

import com.liberbox.legend.controller.response.LegendResponse;
import com.liberbox.legend.service.GetLegendIdService;
import com.liberbox.sessions.controller.response.ColorResponse;
import com.liberbox.sessions.controller.response.SessionResponse;
import com.liberbox.sessions.domain.Session;
import com.liberbox.sessions.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GetSessionDateService {

    private final SessionRepository sessionRepository;
    private final GetLegendIdService getLegendIdService;

    public List<SessionResponse> execute(String scheduleId, LocalDate date) {

        List<Session> sessions = sessionRepository.findByScheduleIdAndDate(scheduleId, date);

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

    private ColorResponse toColor(String legendId, List<LegendResponse> legendResponses) {
        String color = legendResponses.stream().filter(legendResponse -> legendResponse.id().equals(legendId)).findFirst().map(legendResponse -> legendResponse.color()).orElse(null);
        return new ColorResponse(color, color);
    }
}