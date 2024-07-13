package com.liberbox.schedule.service;

import com.liberbox.config.domain.UserContext;
import com.liberbox.schedule.controller.response.ScheduleResponse;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetScheduleService {

    private final ScheduleRepository scheduleRepository;

    public List<ScheduleResponse> execute() {

        List<Schedule> schedules = scheduleRepository.findByUserId(UserContext.getCurrentUser());


        return schedules.stream().map(schedule -> new ScheduleResponse(schedule.getId(),
                schedule.getName(),
                schedule.getUserId(),
                schedule.getStartTime(),
                schedule.getEndTime())).collect(Collectors.toList());

    }
}
