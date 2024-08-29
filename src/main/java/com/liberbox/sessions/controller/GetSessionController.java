package com.liberbox.sessions.controller;


import com.liberbox.sessions.controller.response.SessionResponse;
import com.liberbox.sessions.service.GetSessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "session")
@RestController
@RequiredArgsConstructor
public class GetSessionController {

    private final GetSessionService service;

    @GetMapping("/v1/schedule/{scheduleId}/sessions")
    public ResponseEntity<List<SessionResponse>> execute(@PathVariable String scheduleId,
                                                         @RequestParam(required = true) int month,
                                                         @RequestParam(required = true) int year) {

        List<SessionResponse> response = service.execute(scheduleId, month, year);

        return ResponseEntity.status(200).body(response);

    }
}
