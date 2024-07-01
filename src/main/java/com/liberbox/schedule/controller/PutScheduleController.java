package com.liberbox.schedule.controller;

import com.liberbox.schedule.controller.request.ScheduleRequest;
import com.liberbox.schedule.controller.response.ScheduleResponse;
import com.liberbox.schedule.service.PutScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "schedule")
@RestController
@RequiredArgsConstructor
public class PutScheduleController {

    private final PutScheduleService service;

    @PutMapping("/v1/schedule/{scheduleId}")
    public ResponseEntity<ScheduleResponse> execute(@PathVariable String scheduleId,
                                                    @Valid @RequestBody ScheduleRequest request) {

        ScheduleResponse response = service.execute(scheduleId, request);

        return ResponseEntity.status(200).body(response);

    }
}
