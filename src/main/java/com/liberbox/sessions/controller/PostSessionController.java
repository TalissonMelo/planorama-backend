package com.liberbox.sessions.controller;

import com.liberbox.sessions.controller.request.SessionRequest;
import com.liberbox.sessions.service.PostSessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "session")
@RestController
@RequiredArgsConstructor
public class PostSessionController {

    private final PostSessionService service;

    @PostMapping("/v1/sessions")
    public ResponseEntity<Void> execute(@Valid @RequestBody SessionRequest request) {

        service.execute(request);

        return ResponseEntity.status(201).body(null);

    }
}
