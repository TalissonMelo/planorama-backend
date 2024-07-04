package com.liberbox.sessions.controller;


import com.liberbox.schedule.service.DeleteScheduleIdService;
import com.liberbox.sessions.service.DeleteSessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "session")
@RestController
@RequiredArgsConstructor
public class DeleteSessionController {

    private final DeleteSessionService service;

    @DeleteMapping("/v1/sessions/{sessionId}")
    public ResponseEntity<Void> execute(@PathVariable String sessionId) {

        service.execute(sessionId);

        return ResponseEntity.noContent().build();

    }
}
