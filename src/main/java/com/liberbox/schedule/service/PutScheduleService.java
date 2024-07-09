package com.liberbox.schedule.service;

import com.liberbox.schedule.controller.request.ScheduleRequest;
import com.liberbox.schedule.controller.response.ScheduleResponse;
import com.liberbox.schedule.domain.Schedule;
import com.liberbox.schedule.repository.ScheduleRepository;
import com.liberbox.user.controller.request.UpdateUserRequest;
import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.domain.User;
import com.liberbox.user.domain.mapper.UserMapper;
import com.liberbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PutScheduleService {

	private final ScheduleRepository repository;

	public ScheduleResponse execute(String scheduleId, ScheduleRequest request) {
		Schedule schedule = repository.findById(scheduleId)
				.orElseThrow(() -> new IllegalArgumentException("Schedule with ID: " + scheduleId + " does not exist"));

		schedule.toUpdated(request.name(), request.startTime(), request.endTime());

		repository.save(schedule);

		return new ScheduleResponse(schedule.getId(),
				schedule.getName(),
				schedule.getUserId(),
				schedule.getStartTime(),
				schedule.getEndTime());
	}

}
