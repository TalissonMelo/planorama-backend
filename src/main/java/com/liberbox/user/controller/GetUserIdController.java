package com.liberbox.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.service.GetUserIdService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "user")
@RestController
@RequiredArgsConstructor
public class GetUserIdController {

	private final GetUserIdService service;

	@GetMapping("/v1/users/{userId}")
	public ResponseEntity<UserResponse> execute(@PathVariable String userId) {

		UserResponse response = service.execute(userId);

		return ResponseEntity.status(200).body(response);

	}
}
