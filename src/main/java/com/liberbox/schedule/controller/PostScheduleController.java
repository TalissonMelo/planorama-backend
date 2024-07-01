package com.liberbox.schedule.controller;

import com.liberbox.schedule.controller.request.ScheduleRequest;
import com.liberbox.schedule.controller.response.ScheduleResponse;
import com.liberbox.schedule.service.PostScheduleService;
import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.service.PostUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "schedule")
@RestController
@RequiredArgsConstructor
public class PostScheduleController {

    private final PostScheduleService service;

    @PostMapping("/v1/schedule")
    public ResponseEntity<ScheduleResponse> execute(@Valid @RequestBody ScheduleRequest request) {

        ScheduleResponse response = service.execute(request);

        return ResponseEntity.status(201).body(response);

    }
}
