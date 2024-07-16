package com.liberbox.schedule.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.schedule.controller.response.ScheduleDailyResponse;
import com.liberbox.schedule.controller.response.ScheduleResponse;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.schedule.repository.ScheduleRepository;
import com.liberbox.sessions.domain.Session;
import com.liberbox.sessions.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetScheduleMemberService {

    private final ScheduleRepository scheduleRepository;
    private final SessionRepository sessionRepository;

    public List<ScheduleDailyResponse> execute(LocalDate date) {

        List<Schedule> schedules = scheduleRepository.findByUserId(UserContext.getCurrentUser());

        List<Session> sessions = sessionRepository.findByScheduleIdsAndDate(schedules.stream().map(schedule -> schedule.getId()).collect(Collectors.toList()), date);

        return schedules.stream().map(schedule -> new ScheduleDailyResponse(schedule.getId(),
                        schedule.getName(),
                        schedule.getStartTime(),
                        schedule.getEndTime(),
                        toSessions(schedule.getId(), sessions)))
                .filter(scheduleDailyResponse -> scheduleDailyResponse.sessions().size() > 0)
                .sorted(Comparator.comparing(ScheduleDailyResponse::startTime))
                .collect(Collectors.toList());
    }

    private List<Session> toSessions(String scheduleId, List<Session> sessions) {
        return sessions.stream().filter(session -> session.getScheduleId().equals(scheduleId)).collect(Collectors.toList());
    }
}
