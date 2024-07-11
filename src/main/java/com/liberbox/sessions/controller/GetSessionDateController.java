package com.liberbox.sessions.controller;


import com.liberbox.sessions.controller.response.SessionResponse;
import com.liberbox.sessions.service.GetSessionDateService;
import com.liberbox.sessions.service.GetSessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "session")
@RestController
@RequiredArgsConstructor
public class GetSessionDateController {

    private final GetSessionDateService service;

    @GetMapping("/v1/schedule/{scheduleId}/session")
    public ResponseEntity<List<SessionResponse>> execute(@PathVariable String scheduleId,
                                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<SessionResponse> response = service.execute(scheduleId, date);

        return ResponseEntity.status(200).body(response);

    }
}
