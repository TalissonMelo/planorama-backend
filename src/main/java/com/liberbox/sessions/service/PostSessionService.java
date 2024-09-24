package com.liberbox.sessions.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.members.domain.Member;
import com.liberbox.members.repository.MemberRepository;
import com.liberbox.notifications.service.PostSessionNotificationService;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.schedule.repository.ScheduleRepository;
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

    private final MemberRepository memberRepository;
    private final SessionRepository sessionRepository;
    private final ScheduleRepository scheduleRepository;
    private final PostSessionNotificationService postSessionNotificationService;


    public void execute(SessionRequest request) {

        Schedule schedule = scheduleRepository.findById(request.scheduleId()).orElseThrow(() -> new IllegalArgumentException("Schedule does not exist"));

        List<Member> members = memberRepository.listMemberToScheduleIdAndNotMember(request.scheduleId(), UserContext.getCurrentUser());

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
                    Session session = Session.to(request.title(), start, finish, request.description(), request.legendId(), request.scheduleId());
                    sessions.add(session);

                    if (members.size() > 0) {
                        postSessionNotificationService.execute(members, schedule, session);
                    }
                }
            }

            sessionRepository.saveAll(sessions);
        } else {

            Session session = Session.to(request.title(), request.startTime(), request.endTime(), request.description(), request.legendId(), request.scheduleId());

            sessionRepository.save(session);

            if (members.size() > 0) {
                postSessionNotificationService.execute(members, schedule, session);
            }
        }
    }
}
