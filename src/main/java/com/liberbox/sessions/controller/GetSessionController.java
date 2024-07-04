package com.liberbox.sessions.controller;


import com.liberbox.schedule.controller.response.ScheduleResponse;
import com.liberbox.schedule.service.GetScheduleService;
import com.liberbox.sessions.controller.response.SessionResponse;
import com.liberbox.sessions.service.GetSessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "session")
@RestController
@RequiredArgsConstructor
public class GetSessionController {

    private final GetSessionService service;

    @GetMapping("/v1/schedule/{scheduleId}/session")
    public ResponseEntity<List<SessionResponse>> execute(@PathVariable String scheduleId) {

        List<SessionResponse> response = service.execute(scheduleId);

        return ResponseEntity.status(200).body(response);

    }
}
