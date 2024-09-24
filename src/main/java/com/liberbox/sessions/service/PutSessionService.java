package com.liberbox.sessions.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.members.controller.response.MemberResponse;
import com.liberbox.members.service.GetMemberService;
import com.liberbox.notifications.service.PutSessionNotificationService;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.schedule.repository.ScheduleRepository;
import com.liberbox.sessions.controller.request.PutSessionRequest;
import com.liberbox.sessions.domain.Session;
import com.liberbox.sessions.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PutSessionService {

    private final PutSessionNotificationService putSessionNotificationService;
    private final ScheduleRepository scheduleRepository;
    private final SessionRepository sessionRepository;
    private final GetMemberService getMemberService;

    public void execute(String sessionId, PutSessionRequest request) {

        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("Session does not exist"));

        session.update(request.description());

        sessionRepository.save(session);

        if (session.getEndTime().isAfter(LocalDateTime.now())) {
            Schedule schedule = scheduleRepository.findById(session.getScheduleId()).orElseThrow(() -> new IllegalArgumentException("Schedule does not exist"));

            List<MemberResponse> memberResponses = getMemberService.execute(session.getScheduleId());

            putSessionNotificationService.execute(UserContext.getCurrentUser(), schedule, session, memberResponses);

        }
    }
}
