package com.liberbox.sessions.controller;

import com.liberbox.sessions.controller.request.PutSessionRequest;
import com.liberbox.sessions.controller.request.SessionRequest;
import com.liberbox.sessions.service.PostSessionService;
import com.liberbox.sessions.service.PutSessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "session")
@RestController
@RequiredArgsConstructor
public class PutSessionController {

    private final PutSessionService service;


    @PutMapping("/v1/sessions/{sessionId}")
    public ResponseEntity<Void> execute(@PathVariable String sessionId, @Valid @RequestBody PutSessionRequest request) {

        service.execute(sessionId, request);

        return ResponseEntity.status(201).body(null);

    }
}
