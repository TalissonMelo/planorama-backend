package com.liberbox.schedule.service;

import com.liberbox.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteScheduleIdService {

    private final ScheduleRepository repository;

    public void execute(String scheduleId) {

        repository.deleteById(scheduleId);
    }

}
