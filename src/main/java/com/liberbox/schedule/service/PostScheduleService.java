package com.liberbox.schedule.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.members.service.PostMemberCreatedScheduleService;
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
    private final PostMemberCreatedScheduleService postMemberCreatedScheduleService;

    public ScheduleResponse execute(ScheduleRequest request) {

        Schedule schedule = Schedule.to(request.name(), request.startTime(), request.endTime());

        scheduleRepository.save(schedule);

        postMemberCreatedScheduleService.execute(schedule.getId(), UserContext.getCurrentUser());

        return new ScheduleResponse(schedule.getId(),
                schedule.getName(),
                schedule.getStartTime(),
                schedule.getEndTime());

    }
}
