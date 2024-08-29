package com.liberbox.schedule.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.members.service.PostMemberCreatedScheduleService;
import com.liberbox.schedule.controller.request.ScheduleRequest;
import com.liberbox.schedule.controller.response.ScheduleResponse;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.schedule.repository.ScheduleRepository;
import com.liberbox.user.domain.User;
import com.liberbox.user.domain.enums.Profile;
import com.liberbox.user.repository.UserRepository;
import com.liberbox.user.service.valid.ValidProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PostScheduleService {

    private final ValidProfileService validProfileService;
    private final ScheduleRepository scheduleRepository;
    private final PostMemberCreatedScheduleService postMemberCreatedScheduleService;

    public ScheduleResponse execute(ScheduleRequest request) {

        validProfileService.execute();

        Schedule schedule = Schedule.to(request.name(), request.startTime(), request.endTime());

        scheduleRepository.save(schedule);

        postMemberCreatedScheduleService.execute(schedule.getId(), UserContext.getCurrentUser());

        return new ScheduleResponse(schedule.getId(),
                schedule.getName(),
                UserContext.getCurrentUser(),
                schedule.getStartTime(),
                schedule.getEndTime());

    }
}
