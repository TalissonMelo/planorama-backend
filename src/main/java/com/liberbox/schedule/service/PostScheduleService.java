package com.liberbox.schedule.service;

import com.liberbox.schedule.controller.request.ScheduleRequest;
import com.liberbox.schedule.controller.response.ScheduleResponse;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponse execute(ScheduleRequest request) {

        Schedule schedule = Schedule.to(request.name(), request.startTime(), request.endTime());

        scheduleRepository.save(schedule);

        return new ScheduleResponse(schedule.getId(),
                schedule.getName(),
                schedule.getStartTime(),
                schedule.getEndTime());

    }
}
