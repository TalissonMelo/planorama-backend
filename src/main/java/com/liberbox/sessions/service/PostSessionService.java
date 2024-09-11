package com.liberbox.sessions.service;

import com.liberbox.sessions.controller.request.SessionRequest;
import com.liberbox.sessions.domain.Session;
import com.liberbox.sessions.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostSessionService {

    private final SessionRepository sessionRepository;

    public void execute(SessionRequest request) {

        if (!request.startTime().toLocalDate().equals(request.endTime().toLocalDate())) {
            if (request.daysOfWeeks().isEmpty()) {
                throw new IllegalArgumentException("Does not created sessions");
            }
        }

        if (request.daysOfWeeks().size() > 0) {
            List<Session> sessions = new ArrayList<>();
            for (LocalDate data = request.startTime().toLocalDate(); !data.isAfter(request.endTime().toLocalDate()); data = data.plusDays(1)) {
                if (request.daysOfWeeks().contains(data.getDayOfWeek())) {
                    LocalDateTime start = data.atTime(request.startTime().toLocalTime());
                    LocalDateTime finish = data.atTime(request.endTime().toLocalTime());
                    sessions.add(Session.to(request.title(), start, finish, request.description(), request.legendId(), request.scheduleId()));
                }
            }

            sessionRepository.saveAll(sessions);
        } else {

            Session session = Session.to(request.title(), request.startTime(), request.endTime(), request.description(), request.legendId(), request.scheduleId());

            sessionRepository.save(session);
        }


    }
}
