package com.liberbox.user.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liberbox.user.controller.request.UpdateUserRequest;
import com.liberbox.user.controller.response.UserResponse;
import com.liberbox.user.service.PutUserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "user")
@RestController
@RequiredArgsConstructor
public class PutUserController {

	private final PutUserService service;

	@PutMapping("/v1/users/{userId}")
	public ResponseEntity<UserResponse> execute(@PathVariable String userId,
			@Valid @RequestBody UpdateUserRequest request) {

		UserResponse response = service.execute(userId, request);

		return ResponseEntity.status(200).body(response);

	}
}
