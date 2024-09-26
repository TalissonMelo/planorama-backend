package com.liberbox.user.controller;

import com.liberbox.user.controller.request.UserRequest;
import com.liberbox.user.controller.request.UserTokenRequest;
import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.service.PostUserService;
import com.liberbox.user.service.PutUserTokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "user")
@RestController
@RequiredArgsConstructor
public class PostUserTokenController {

    private final PutUserTokenService service;

    @PutMapping("/v1/users/token")
    public ResponseEntity<Void> execute(@Valid @RequestBody UserTokenRequest request) {

        service.execute(request);

        return ResponseEntity.status(201).body(null);

    }
}
