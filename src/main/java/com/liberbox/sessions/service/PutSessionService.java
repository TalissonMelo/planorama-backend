package com.liberbox.sessions.service;

import com.liberbox.members.controller.response.MemberResponse;
import com.liberbox.members.service.GetMemberService;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.schedule.repository.ScheduleRepository;
import com.liberbox.sessions.controller.request.PutSessionRequest;
import com.liberbox.sessions.domain.Session;
import com.liberbox.sessions.repository.SessionRepository;
import com.liberbox.sms.UpdateSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PutSessionService {

    private final UpdateSessionService updateSessionService;
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

            memberResponses.forEach(memberResponse -> {
                updateSessionService.sendVerificationCode(schedule.getName(), memberResponse.member().nickname(), session.getStartTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), session.getTitle() + ": " + request.description(), memberResponse.member().phone());
            });
        }
    }
}
