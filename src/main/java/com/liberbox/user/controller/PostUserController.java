package com.liberbox.user.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liberbox.user.controller.request.UserRequest;
import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.service.PostUserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "user")
@RestController
@RequiredArgsConstructor
public class PostUserController {

	private final PostUserService service;

	@PostMapping("/v1/users")
	public ResponseEntity<UserResponse> execute(@Valid @RequestBody UserRequest request) {

		UserResponse response = service.execute(request);

		return ResponseEntity.status(201).body(response);

	}
}
