package com.liberbox.schedule.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.schedule.controller.response.ScheduleDailyResponse;
import com.liberbox.schedule.controller.response.ScheduleFreeTimeResponse;
import com.liberbox.schedule.controller.response.ScheduleTimeResponse;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.schedule.repository.ScheduleRepository;
import com.liberbox.sessions.domain.Session;
import com.liberbox.sessions.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetScheduleMemberTimesService {

    private final ScheduleRepository scheduleRepository;
    private final SessionRepository sessionRepository;

    public List<ScheduleFreeTimeResponse> execute(LocalDate date, Integer minutes) {

        if (LocalDate.now().isAfter(date)) {
            return new ArrayList<>();
        }

        List<Schedule> schedules = scheduleRepository.findByUserId(UserContext.getCurrentUser());
        List<Session> sessions = sessionRepository.findByScheduleIdsAndDate(
                schedules.stream().map(Schedule::getId).collect(Collectors.toList()),
                date
        );

        return schedules.stream()
                .map(schedule -> new ScheduleDailyResponse(
                        schedule.getId(),
                        schedule.getName(),
                        schedule.getStartTime(),
                        schedule.getEndTime(),
                        toSessions(schedule.getId(), sessions)
                ))
                .map(scheduleDailyResponse -> getScheduleWithFreeTimes(scheduleDailyResponse, minutes))
                .filter(scheduleFreeTimeResponse -> scheduleFreeTimeResponse.times().size() > 0)
                .collect(Collectors.toList());
    }


    private ScheduleFreeTimeResponse getScheduleWithFreeTimes(ScheduleDailyResponse scheduleDailyResponse, Integer minutes) {
        String id = scheduleDailyResponse.id();
        String title = scheduleDailyResponse.name();
        List<Session> sessions = scheduleDailyResponse.sessions();
        LocalTime startTime = scheduleDailyResponse.startTime();
        LocalTime endTime = scheduleDailyResponse.endTime().plusSeconds(2);

        List<LocalTime> allPossibleTimes = new ArrayList<>();
        LocalTime current = startTime;
        while (current.isBefore(endTime)) {
            allPossibleTimes.add(current);
            current = current.plusMinutes(minutes);
        }

        List<LocalTime> freeTimes = allPossibleTimes.stream()
                .filter(time -> sessions.stream()
                        .noneMatch(session -> isTimeWithinSession(time, session, minutes)))
                .collect(Collectors.toList());

        List<ScheduleTimeResponse> freeTimeResponses = freeTimes.stream()
                .map(time -> new ScheduleTimeResponse(time, time.plusMinutes(minutes)))
                .filter(scheduleTimeResponse -> scheduleTimeResponse.endTime().isBefore(endTime))
                .collect(Collectors.toList());

        return new ScheduleFreeTimeResponse(id, title, freeTimeResponses);
    }


    private boolean isTimeWithinSession(LocalTime time, Session session, int durationInMinutes) {
        LocalTime sessionStartTime = session.getStartTime().toLocalTime();
        LocalTime sessionEndTime = session.getEndTime().toLocalTime();
        LocalTime endTime = time.plusMinutes(durationInMinutes);
        Boolean result = !(endTime.isAfter(sessionStartTime) && time.isBefore(sessionEndTime));
        return !result;
    }



    private List<Session> toSessions(String scheduleId, List<Session> sessions) {
        return sessions.stream()
                .filter(session -> session.getScheduleId().equals(scheduleId))
                .collect(Collectors.toList());
    }
}
